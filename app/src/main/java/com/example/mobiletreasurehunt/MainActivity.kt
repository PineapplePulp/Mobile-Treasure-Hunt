/*
Assignment 5
David Li / lidav@oregonstate.edu
CS 492 / Oregon State University (OSU)
 */

package com.example.mobiletreasurehunt

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mobiletreasurehunt.ui.theme.MobileTreasureHuntTheme
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        setContent {
            MobileTreasureHuntTheme {
                MobileTreasureHuntApp(
                    hasCoarsePermission = { hasCoarseLocationPermission() },
                    hasFinePermission = { hasFineLocationPermission() },
                    requestCoarsePermission = { requestCoarseLocationPermission() },
                    requestFinePermission = { requestFineLocationPermission() },
                    fusedLocationClient = fusedLocationClient
                )
            }
        }
    }

    private fun hasCoarseLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun hasFineLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }


    private val requestCoarseLocationLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(
                this,
                R.string.received_coarse_loc,
                Toast.LENGTH_SHORT).show()
        }
    }

    private val requestFineLocationLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(
                this,
                R.string.received_fine_loc,
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestCoarseLocationPermission() {
        when {
            hasCoarseLocationPermission() -> {
                Toast.makeText(
                    this,
                    R.string.already_received_coarse_loc,
                    Toast.LENGTH_SHORT).show()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) -> {
            }
            else -> {
                // You can directly ask for the permission.
                requestCoarseLocationLauncher.launch(
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            }
        }
    }

    private fun requestFineLocationPermission() {
        when {
            hasFineLocationPermission() -> {
                Toast.makeText(
                    this,
                    R.string.already_received_fine_loc,
                    Toast.LENGTH_SHORT).show()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this, Manifest.permission.ACCESS_FINE_LOCATION) -> {
            }
            else -> {
                // You can directly ask for the permission.
                requestFineLocationLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            }
        }
    }
}