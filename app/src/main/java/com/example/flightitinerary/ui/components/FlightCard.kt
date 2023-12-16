package com.example.flightitinerary.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.AirplaneTicket
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.FlightTakeoff
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FlightCard(
    navController: NavController,
    flightNumber: String,
    departureAirport: String,
    arrivalAirport: String,
    departureDate: String,
    arrivalDate: String,
    timeFlight: Long,
    flightDuration: Long
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .border(2.dp, Color.LightGray, RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .clickable {
                navController.navigate("track?icao24=$flightNumber&time=${timeFlight}")
            },
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            tint = Color.Gray,
                            imageVector = Icons.Default.LocationCity,
                            contentDescription = "Flight takeoff",
                        )
                        Text(
                            text = departureAirport,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            tint = Color.Gray,
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = "Flight takeoff",
                        )
                        Text(
                            text = departureDate,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }

                Icon(
                    tint = Color.Gray,
                    imageVector = Icons.Default.FlightTakeoff,
                    contentDescription = "Flight takeoff",
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            tint = Color.Gray,
                            imageVector = Icons.Default.LocationCity,
                            contentDescription = "Flight takeoff",
                        )
                        Text(
                            text = arrivalAirport,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            tint = Color.Gray,
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = "Flight takeoff",
                        )
                        Text(
                            text = arrivalDate,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(3.dp)
                    .background(Color.LightGray, RoundedCornerShape(4.dp))
                    .align(Alignment.CenterHorizontally)
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                )
                {
                    Icon(
                        tint = Color.Gray,
                        imageVector = Icons.AutoMirrored.Filled.AirplaneTicket,
                        contentDescription = "Flight number",
                    )
                    Text(
                        text = flightNumber,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        tint = Color.Gray,
                        imageVector = Icons.Default.Timer,
                        contentDescription = "Timer",
                    )
                    Text(
                        text = "${flightDuration / 3600}h ${flightDuration % 3600 / 60}m",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

        }
    }
}