/*
Assignment 5
David Li / lidav@oregonstate.edu
CS 492 / Oregon State University (OSU)
 */

package com.example.mobiletreasurehunt.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobiletreasurehunt.R
import com.example.mobiletreasurehunt.model.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CriticismDialog(appViewModel: AppViewModel) {
    BasicAlertDialog(
        onDismissRequest = { appViewModel.stopCriticizing() },
        modifier = Modifier.clip(RoundedCornerShape(20.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color(1f, 1f, 1f))
        ) {
            Text(
                stringResource(R.string.wrong_label),
                textAlign = TextAlign.Center,
                fontSize = 35.sp,
                lineHeight = 40.sp,
                modifier = Modifier.padding(30.dp)
            )
            Button(
                onClick = { appViewModel.stopCriticizing() },
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .width(250.dp)
                    .height(60.dp)
            ) {
                Text(
                    stringResource(R.string.continue_label),
                    fontSize = 20.sp
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuitDialog(appViewModel: AppViewModel, onQuit: () -> Unit ) {
    BasicAlertDialog(
        onDismissRequest = { appViewModel.stopCriticizing() },
        modifier = Modifier.clip(RoundedCornerShape(20.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color(1f, 1f, 1f))
        ) {
            Text(
                stringResource(R.string.quit_confirm_label),
                fontSize = 25.sp,
                lineHeight = 40.sp,
                modifier = Modifier.padding(30.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            ) {
                Button(
                    onClick = { appViewModel.hideQuitDialog() },
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .width(80.dp)
                        .height(60.dp)
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = stringResource(R.string.cancel_label)
                    )
                }
                Button(
                    onClick = onQuit,
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .width(80.dp)
                        .height(60.dp)
                ) {
                    Icon(
                        Icons.Default.Done,
                        contentDescription = stringResource(R.string.proceed_label)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewQuitDialog(appViewModel: AppViewModel = viewModel()) {
    QuitDialog(appViewModel = appViewModel, onQuit = {})
}