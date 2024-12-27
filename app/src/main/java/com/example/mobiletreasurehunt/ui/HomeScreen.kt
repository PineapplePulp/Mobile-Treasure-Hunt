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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobiletreasurehunt.data.Datasource

@Composable
fun HomeScreen(nextScreen: () -> Unit, modifier: Modifier = Modifier) {
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
            Text(
                text = "Mobile Treasure Hunt",
                fontSize = 38.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "RULES OF THE GAME",
                fontWeight = FontWeight.Bold,
                fontSize = 23.sp
            )
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn(modifier = Modifier.height(400.dp)) {
                items(Datasource.loadRules()) { rule ->
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text(
                            stringResource(rule[0]),
                            fontSize = 15.sp,
                            lineHeight = 30.sp,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                        Text(
                            stringResource(rule[1]),
                            fontSize = 15.sp,
                            lineHeight = 30.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = nextScreen,
                modifier = Modifier
                    .height(60.dp)
                    .width(140.dp)
            ) {
                Text(
                    text = "Continue",
                    fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Preview(showBackground=true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        nextScreen = { }
    )
}