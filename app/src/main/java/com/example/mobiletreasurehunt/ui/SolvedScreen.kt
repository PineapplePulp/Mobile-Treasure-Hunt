/*
Assignment 5
David Li / lidav@oregonstate.edu
CS 492 / Oregon State University (OSU)
 */

package com.example.mobiletreasurehunt.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobiletreasurehunt.R
import com.example.mobiletreasurehunt.data.Datasource
import com.example.mobiletreasurehunt.model.AppUiState
import com.example.mobiletreasurehunt.model.AppViewModel

@Composable
fun SolvedScreen(
    isLast: Boolean,
    appViewModel: AppViewModel,
    appUiState: AppUiState,
    nextScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val title: Int = if (isLast) {
        R.string.end_title
    } else {
        R.string.solved_title
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            TimerPanel(
                appViewModel = appViewModel,
                subtext = R.string.timer_paused
            )
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                stringResource(title),
                fontSize = 38.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                stringResource(Datasource.loadName(appUiState.clueNumber)),
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = stringResource(Datasource.loadDesc(appUiState.clueNumber)),
                fontSize = 14.sp,
                lineHeight = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .height(220.dp)
                    .verticalScroll(rememberScrollState())
            )
        }
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(
                    R.string.progress_label,
                    appUiState.clueNumber + 1,
                    Datasource.totalClues()
                )
            )
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                if (isLast) {
                    Button(
                        onClick = nextScreen,
                        modifier = Modifier
                            .height(70.dp)
                            .width(200.dp)
                    ) {
                        Text(
                            stringResource(R.string.home_label),
                            fontSize = 20.sp
                        )
                    }
                } else {
                    Button(
                        onClick = { appViewModel.showQuitDialog() },
                        modifier = Modifier
                            .height(70.dp)
                            .width(90.dp)
                    ) {
                        Text(
                            stringResource(R.string.quit_label),
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    Button(
                        onClick = nextScreen,
                        modifier = Modifier
                            .height(70.dp)
                            .width(200.dp)
                    ) {
                        Text(
                            stringResource(R.string.next_label),
                            fontSize = 20.sp
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(70.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSolvedScreen(
    appViewModel: AppViewModel = viewModel()
) {
    val appUiState by appViewModel.uiState.collectAsState()
    SolvedScreen(
        isLast = false,
        appViewModel = appViewModel,
        appUiState = appUiState,
        nextScreen = { /*TODO*/ }
    )
}

@Preview(showBackground=true)
@Composable
fun PreviewClueScreen(
    appViewModel: AppViewModel = viewModel()
) {
    val appUiState by appViewModel.uiState.collectAsState()
    ClueScreen(
        appViewModel = appViewModel,
        appUiState = appUiState,
        nextScreen = {}
    )
}