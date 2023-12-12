package com.example.flightitinerary.screens.flightslist

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.flightitinerary.data.repository.AirportRepo
import com.example.flightitinerary.data.repository.FlightRepo
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@SuppressLint("SimpleDateFormat")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FlightsListScreen(
    navController: NavController,
    viewModel: FlightsViewModel,
    from: String,
    to: String,
    startDate: String,
    endDate: String
) {
    val flights by viewModel.state.collectAsState()

    val airportRepo = AirportRepo()
    val flightRepo = FlightRepo()

    // val departureAirport = airportRepo.getAirportByCity(from)
    // val arrivalAirport = airportRepo.getAirportByCity(to)
    val format = SimpleDateFormat("yyyy-MM-dd")


}

@RequiresApi(Build.VERSION_CODES.O)
fun getSecondsSinceEpochFromStringDate(dateString: String): Long {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val localDateTime = LocalDateTime.parse(dateString, formatter)
    val instant = localDateTime.toInstant(ZoneOffset.UTC)

    return instant.epochSecond
}