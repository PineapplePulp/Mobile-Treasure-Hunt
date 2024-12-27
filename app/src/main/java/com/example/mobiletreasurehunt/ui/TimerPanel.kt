/*
Assignment 5
David Li / lidav@oregonstate.edu
CS 492 / Oregon State University (OSU)
 */

package com.example.mobiletreasurehunt.ui

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobiletreasurehunt.R
import com.example.mobiletreasurehunt.model.AppViewModel
import java.lang.Math.floorDiv

@SuppressLint("DefaultLocale")
@Composable
fun TimerPanel(
    appViewModel: AppViewModel,
    @StringRes subtext: Int?,
    modifier: Modifier = Modifier
) {
    val timerUiState by appViewModel.timerState.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(20.dp))
            .background(color = Color(0xF0, 0xF0, 0xF0))
            .height(100.dp)
            .width(270.dp)
    ) {
        Text(
            text = stringResource(
                R.string.timer,
                String.format("%02d", floorDiv(timerUiState.seconds, 3600)),
                String.format("%02d", floorDiv(timerUiState.seconds, 60)),
                String.format("%02d", timerUiState.seconds % 60)
            ),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        if (subtext != null) {
            Text(
                text = stringResource(subtext),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun PreviewTimer(appViewModel: AppViewModel = viewModel()) {
    TimerPanel(appViewModel = appViewModel, R.string.timer_paused)
}
