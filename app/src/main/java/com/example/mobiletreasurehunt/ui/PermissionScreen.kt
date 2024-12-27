/*
Assignment 5
David Li / lidav@oregonstate.edu
CS 492 / Oregon State University (OSU)
 */

package com.example.mobiletreasurehunt.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.mobiletreasurehunt.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionScreen(
    requestCoarsePermission: () -> Unit,
    requestFinePermission: () -> Unit,
    nextScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        BasicAlertDialog(
            onDismissRequest = { nextScreen() },
            modifier = Modifier.clip(RoundedCornerShape(20.dp))
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color(1f, 1f, 1f))
            ) {
                Text(
                    text = stringResource(R.string.permission_title),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    lineHeight = 40.sp,
                    modifier = Modifier.padding(30.dp)
                )
                Text(
                    text = stringResource(R.string.permission_desc),
                    fontSize = 15.sp,
                    lineHeight = 30.sp,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = {
                        requestFinePermission()
                    },
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .height(60.dp)
                ) {
                    Text(
                        text = stringResource(R.string.fine_loc_button_label),
                        fontSize = 20.sp
                    )
                }
                Button(
                    onClick = {
                        requestCoarsePermission()
                    },
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .height(60.dp)
                ) {
                    Text(
                        text = stringResource(R.string.coarse_loc_button_label),
                        fontSize = 20.sp
                    )
                }
                Button(
                    onClick = {
                        nextScreen()
                    },
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .height(60.dp)
                ) {
                    Text(
                        text = stringResource(R.string.accept_button_label),
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground=true)
@Composable
fun PreviewPermissionScreen() {
    PermissionScreen(
        requestCoarsePermission = {},
        requestFinePermission = {},
        nextScreen = {}
    )
}