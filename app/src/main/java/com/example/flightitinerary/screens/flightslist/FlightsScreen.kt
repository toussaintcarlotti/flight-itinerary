package com.example.flightitinerary.screens.flightslist

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flightitinerary.data.models.Flight
import com.example.flightitinerary.utils.Utils.Companion.secondsToDate


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FlightsListScreen(
    navController: NavController,
    startDate: String,
    endDate: String,
    from: String?,
    to: String?
) {
    val viewModel = viewModel(modelClass = FlightsViewModel::class.java)
    val flights by viewModel.flights.collectAsState()


    LaunchedEffect(viewModel) {
        viewModel.fetchFlights(
            departureAirport = from,
            arrivalAirport = to,
            startDate = startDate,
            endDate = endDate
        )
    }

    Column {
        Log.d("FlightsListScreen", "from: $from, to: $to")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        ) {
            if (from !== null && to !== null) {
                Text(text = "Vol de $from à $to")
            } else if (from !== null) {
                Text(text = "Vol partant de $from")
            } else if (to !== null) {
                Text(text = "Vol vers $to")
            }
        }

        LazyColumn {
            items(flights) { flight ->
                val timeFlight = flight.lastSeen - flight.firstSeen
                FlightCard(
                    flightNumber = flight.icao24,
                    departureAirport = flight.estDepartureAirport,
                    arrivalAirport = flight.estArrivalAirport,
                    departureDate = secondsToDate(flight.firstSeen),
                    arrivalDate = secondsToDate(flight.lastSeen),
                    timeFlight = timeFlight,
                )
            }
        }
    }
}

@Composable
fun FlightItem(flight: Flight) {
    // Composant représentant un élément de la liste
    Text(text = "Departure: ${flight.estDepartureAirport}, Arrival: ${flight.estArrivalAirport}")
}


@Composable
fun FlightCard(
    flightNumber: String,
    departureAirport: String,
    arrivalAirport: String,
    departureDate: String,
    arrivalDate: String,
    timeFlight: Long,
) {

    Row(
        modifier = Modifier
            .padding(16.dp)
            .border(2.dp, Color.LightGray, RoundedCornerShape(10.dp))
            .fillMaxWidth(),
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
                        text = "${timeFlight / 3600}h ${timeFlight % 3600 / 60}m",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }

        }
    }
}