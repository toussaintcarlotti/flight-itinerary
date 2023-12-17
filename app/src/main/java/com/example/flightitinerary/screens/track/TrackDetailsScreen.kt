package com.example.flightitinerary.screens.track

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flightitinerary.ui.components.FlightCard
import com.example.flightitinerary.ui.components.TrackDetails
import com.example.flightitinerary.utils.Utils
import com.example.flightitinerary.utils.Utils.Companion.getTimestampFromStringDate
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TrackDetailsScreen(
    navController: NavController,
    icao24: String,
    time: Long
) {
    val viewModel = viewModel(modelClass = TrackViewModel::class.java)

    val track by viewModel.track.collectAsState()
    val flights by viewModel.flights.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.fetchTrack(icao24, time)
        viewModel.fetchFlights(
            icao24,
            getTimestampFromStringDate(LocalDate.now().minusDays(3).toString()).toString(),
            getTimestampFromStringDate(LocalDate.now().toString()).toString()
        )
    }

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        TrackDetails(
            speed = 0.0,
            altitude = 0.0,
            direction = 0.0,
            latitude = 0.0,
            longitude = 0.0
        )

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Vol des 3 derniers jours",
                style = MaterialTheme.typography.titleMedium,
            )
        }

        LazyColumn {
            items(flights) { flight ->
                val flightDuration = flight.lastSeen - flight.firstSeen
                FlightCard(
                    navController = navController,
                    flightNumber = flight.icao24,
                    departureAirport = flight.departureAirportCity
                        ?: flight.estDepartureAirport,
                    arrivalAirport = flight.arrivalAirportCity
                        ?: flight.estArrivalAirport,
                    departureDate = Utils.secondsToDate(flight.firstSeen),
                    arrivalDate = Utils.secondsToDate(flight.lastSeen),
                    timeFlight = flight.firstSeen + 1,
                    flightDuration = flightDuration
                )
            }
        }
    }
}