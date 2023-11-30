package com.example.flightitinerary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.flightitinerary.screens.FlightItineraryApp
import com.example.flightitinerary.ui.theme.FlightItineraryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlightItineraryTheme {
                FlightItineraryApp()
            }
        }
    }
}
