package com.example.flightitinerary.screens.flightslist

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun FlightsListScreen(navController: NavController, from: String, to: String, date: String) {
    Column {
        Text(text = "From: $from")
        Text(text = "To: $to")
        Text(text = "Date: $date")
    }
}