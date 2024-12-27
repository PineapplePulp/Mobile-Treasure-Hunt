/*
Assignment 4
David Li / lidav@oregonstate.edu
CS 492 / Oregon State University (OSU)
 */

package com.example.mobiletreasurehunt.model

data class AppUiState (
    val clueNumber: Int = 0,
    val revealHint: Boolean = false,
    val currentLocation: Geo = Geo(0.0, 0.0),
    val shouldCriticizeUser: Boolean = false,
    val shouldShowQuitDialog: Boolean = false,
    val shouldShowSolved: Boolean = false
)