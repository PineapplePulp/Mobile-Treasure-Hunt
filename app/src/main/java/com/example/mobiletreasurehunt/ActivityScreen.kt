/*
Assignment 5
David Li / lidav@oregonstate.edu
CS 492 / Oregon State University (OSU)
 */

package com.example.mobiletreasurehunt

import android.annotation.SuppressLint
import android.location.Location
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobiletreasurehunt.data.Datasource
import com.example.mobiletreasurehunt.model.AppViewModel
import com.example.mobiletreasurehunt.model.Geo
import com.example.mobiletreasurehunt.ui.ClueScreen
import com.example.mobiletreasurehunt.ui.CriticismDialog
import com.example.mobiletreasurehunt.ui.HomeScreen
import com.example.mobiletreasurehunt.ui.PermissionScreen
import com.example.mobiletreasurehunt.ui.QuitDialog
import com.example.mobiletreasurehunt.ui.SolvedScreen
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.Tasks.await
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@Composable
fun InitGeos(appViewModel: AppViewModel) {
    for ((i, location) in Datasource.loadLocations().withIndex()) {
        appViewModel.addGeo(
            i = i,
            lat = stringResource(location[0]).toDouble(),
            lon = stringResource(location[1]).toDouble()
        )
    }
}

@SuppressLint("MissingPermission")
@Composable
fun MobileTreasureHuntApp(
    hasCoarsePermission: () -> Boolean,
    hasFinePermission: () -> Boolean,
    requestCoarsePermission: () -> Unit,
    requestFinePermission: () -> Unit,
    fusedLocationClient: FusedLocationProviderClient,
    appViewModel: AppViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val appUiState by appViewModel.uiState.collectAsState()
    InitGeos(appViewModel)

    if (appUiState.shouldShowQuitDialog) {
        QuitDialog(appViewModel = appViewModel) {
            navController.navigate(AppScreens.HOME.name)
            appViewModel.hideQuitDialog()
        }
    }

    if (appUiState.shouldCriticizeUser) {
        CriticismDialog(appViewModel)
    }

    NavHost(
        navController = navController,
        startDestination = AppScreens.PERMISSION.name
    ) {
        composable(route = AppScreens.PERMISSION.name) {
            PermissionScreen(
                requestCoarsePermission = requestCoarsePermission,
                requestFinePermission = requestFinePermission,
                nextScreen = {
                    if (hasCoarsePermission() && hasFinePermission()) {
                        navController.navigate(AppScreens.HOME.name)
                    }
                }
            )
        }
        composable(route = AppScreens.HOME.name) {
            HomeScreen(
                nextScreen = {
                    navController.navigate(AppScreens.CLUE.name)
                    appViewModel.startTimer()
                }
            )
        }
        composable(route = AppScreens.CLUE.name) {
            ClueScreen(
                appViewModel = appViewModel,
                appUiState = appUiState,
                nextScreen = {
                    runBlocking {
                        val deferred: Deferred<Double> = async {
                            val requestBuilder = CurrentLocationRequest.Builder()
                                .setMaxUpdateAgeMillis(0) // for the purpose of the assignment
                                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                                .setGranularity(Granularity.GRANULARITY_FINE)
                            withContext(Dispatchers.Default) {
                                val location: Location = await(
                                    fusedLocationClient.getCurrentLocation(
                                        requestBuilder.build(), null
                                    )
                                )
                                val g = Geo(location.latitude, location.longitude)
                                g.printInfo()
                                return@withContext appViewModel.getGeo(appUiState.clueNumber)
                                    .haversine(g)
                            }
                        }
                        if (deferred.await() > 0.75) {
                            appViewModel.startCriticizing()
                        } else if (appUiState.clueNumber + 1 == Datasource.totalClues()) {
                            appViewModel.showSolved()
                            navController.navigate(AppScreens.END.name)
                            appViewModel.pauseTimer()
                        } else {
                            appViewModel.showSolved()
                            navController.navigate(AppScreens.SOLVED.name)
                            appViewModel.pauseTimer()
                        }
                    }
                }
            )
        }
        composable(route = AppScreens.SOLVED.name) {
            if (appUiState.shouldShowSolved) {
                SolvedScreen(
                    isLast = false,
                    appViewModel = appViewModel,
                    appUiState = appUiState,
                    nextScreen = {
                        appViewModel.hideSolved()
                        navController.navigate(AppScreens.CLUE.name)
                        appViewModel.setClueNumber(appUiState.clueNumber + 1)
                        appViewModel.startTimer()
                        appViewModel.hideHint()
                    }
                )
            }
        }
        composable(route = AppScreens.END.name) {
            SolvedScreen(
                isLast = true,
                appViewModel = appViewModel,
                appUiState = appUiState,
                nextScreen = {
                    appViewModel.hideSolved()
                    navController.navigate(AppScreens.HOME.name)
                    appViewModel.resetTimer()
                    appViewModel.hideHint()
                }
            )
        }
    }
}

enum class AppScreens {
    PERMISSION,
    HOME,
    CLUE,
    SOLVED,
    END
}