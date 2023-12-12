package com.example.flightitinerary.screens.flightslist

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.flightitinerary.data.models.Flight
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
    from: String,
    to: String,
    startDate: String,
    endDate: String
) {
    val context = LocalContext.current
    val airportRepo = AirportRepo()
    val flightRepo = FlightRepo()

    // val departureAirport = airportRepo.getAirportByCity(from)
    // val arrivalAirport = airportRepo.getAirportByCity(to)
    val format = SimpleDateFormat("yyyy-MM-dd")


    var flights by remember { mutableStateOf<List<Flight>>(emptyList()) }



    Column {
        // Text(text = "From: ${departureAirport?.city}")
        Text(text = "To: $to")
        Text(text = "Date: $startDate")
        Text(text = "Date: $endDate")
        flights.forEach {
            Text(text = it.toString())
        }
    }


}

@RequiresApi(Build.VERSION_CODES.O)
fun getSecondsSinceEpochFromStringDate(dateString: String): Long {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val localDateTime = LocalDateTime.parse(dateString, formatter)
    val instant = localDateTime.toInstant(ZoneOffset.UTC)

    return instant.epochSecond
}