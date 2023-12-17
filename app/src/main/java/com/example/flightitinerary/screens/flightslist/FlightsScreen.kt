package com.example.flightitinerary.screens.flightslist

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        ) {
            if (flights.isNotEmpty()) {
                if (from !== null && to !== null) {
                    Text(text = "Vol de ${flights.first().departureAirportCity ?: from} à ${flights.first().arrivalAirportCity ?:to}")
                } else if (from !== null) {
                    Text(text = "Vol partant de ${flights.first().departureAirportCity ?: from}")
                } else if (to !== null) {
                    Text(text = "Vol vers ${flights.first().arrivalAirportCity ?: to}")
                }
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 350.dp),
        ) {
            items(flights) { flight ->
                val flightDuration = flight.lastSeen - flight.firstSeen
                FlightCard(
                    navController = navController,
                    flightNumber = flight.icao24,
                    departureAirport = flight.departureAirportCity ?: flight.estDepartureAirport,
                    arrivalAirport = flight.arrivalAirportCity ?: flight.estArrivalAirport,
                    departureDate = secondsToDate(flight.firstSeen),
                    arrivalDate = secondsToDate(flight.lastSeen),
                    timeFlight = flight.firstSeen + 1,
                    flightDuration = flightDuration
                )
            }
        }
    }
}

