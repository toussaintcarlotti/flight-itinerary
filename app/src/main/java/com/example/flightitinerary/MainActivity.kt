package com.example.flightitinerary

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.flightitinerary.screens.FlightItineraryApp
import com.example.flightitinerary.ui.theme.FlightItineraryTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            FlightItineraryTheme {
                FlightItineraryApp()
            }
        }
    }
}
