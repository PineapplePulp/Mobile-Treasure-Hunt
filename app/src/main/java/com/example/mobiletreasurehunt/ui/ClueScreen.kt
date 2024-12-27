/*
Assignment 5
David Li / lidav@oregonstate.edu
CS 492 / Oregon State University (OSU)
 */

package com.example.mobiletreasurehunt.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobiletreasurehunt.R
import com.example.mobiletreasurehunt.data.Datasource
import com.example.mobiletreasurehunt.model.AppUiState
import com.example.mobiletreasurehunt.model.AppViewModel

@Composable
fun ClueScreen(
    appViewModel: AppViewModel,
    appUiState: AppUiState,
    nextScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            TimerPanel(
                appViewModel = appViewModel,
                subtext = null
            )
            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = stringResource(R.string.clue_title, appUiState.clueNumber + 1),
                fontSize = 38.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = stringResource(Datasource.loadClue(appUiState.clueNumber)),
                fontSize = 24.sp,
                lineHeight = 60.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            if (appUiState.revealHint) {
                Text(
                    text = stringResource(Datasource.loadHint(appUiState.clueNumber)),
                    fontSize = 24.sp,
                    lineHeight = 60.sp,
                    modifier = Modifier
                        .clickable(onClick = { appViewModel.hideHint() })
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                )
            } else {
                Text(
                    text = stringResource(R.string.hint_label),
                    fontSize = 20.sp,
                    modifier = Modifier.clickable(onClick = { appViewModel.revealHint() })
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Button (
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
                Button(
                    onClick = nextScreen,
                    modifier = Modifier
                        .height(70.dp)
                        .width(200.dp)
                ) {
                    Text(
                        text = stringResource(R.string.found_label),
                        fontSize = 20.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}