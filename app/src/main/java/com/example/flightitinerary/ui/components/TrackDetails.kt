package com.example.flightitinerary.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TrackDetails(speed: Double, altitude: Double, direction: Double, latitude: Double, longitude: Double) {
    Column(
        Modifier
            .border(2.dp, Color.LightGray, RoundedCornerShape(10.dp))
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Vitesse: $speed km/h")
        Text(text = "Altitude: $altitude m")
        Text(text = "Direction: $direction °")
        Text(text = "Latitude: $latitude")
        Text(text = "Longitude: $longitude")
    }
}