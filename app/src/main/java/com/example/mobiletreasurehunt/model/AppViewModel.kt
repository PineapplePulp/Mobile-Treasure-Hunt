/*
Assignment 5
David Li / lidav@oregonstate.edu
CS 492 / Oregon State University (OSU)
 */

package com.example.mobiletreasurehunt.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    private val _timerState = MutableStateFlow(TimerUiState())
    val timerState: StateFlow<TimerUiState> = _timerState.asStateFlow()

    private var paused: Boolean = true

    private var geos: MutableList<Geo> = mutableListOf()

    fun addGeo(i: Int, lat: Double, lon: Double) {
        geos.add(i, Geo(lat, lon))
    }

    fun getGeo(i: Int): Geo {
        return geos[i]
    }

    fun startTimer() {
        paused = false
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                while (!paused) {
                    delay(1000)
                    _timerState.update { currentState ->
                        currentState.copy(seconds = currentState.seconds + 1)
                    }
                }
            }
        }
    }

    fun pauseTimer() {
        paused = true
    }

    fun resetTimer() {
        paused = true
        _timerState.update { currentState ->
            currentState.copy(seconds = 0)
        }
    }

    fun revealHint() {
        _uiState.update { currentState ->
            currentState.copy(revealHint = true)
        }
    }

    fun hideHint() {
        _uiState.update { currentState ->
            currentState.copy(revealHint = false)
        }
    }

    fun setClueNumber(n: Int) {
        _uiState.update { currentState ->
            currentState.copy(clueNumber = n)
        }
    }

    fun startCriticizing() {
        _uiState.update { currentState ->
            currentState.copy(shouldCriticizeUser = true)
        }
    }

    fun stopCriticizing() {
        _uiState.update { currentState ->
            currentState.copy(shouldCriticizeUser = false)
        }
    }

    fun showQuitDialog() {
        _uiState.update { currentState ->
            currentState.copy(shouldShowQuitDialog = true)
        }
    }

    fun hideQuitDialog() {
        _uiState.update { currentState ->
            currentState.copy(shouldShowQuitDialog = false)
        }
    }

    fun showSolved() {
        _uiState.update { currentState ->
            currentState.copy(shouldShowSolved = true)
        }
    }

    fun hideSolved() {
        _uiState.update { currentState ->
            currentState.copy(shouldShowSolved = false)
        }
    }
}