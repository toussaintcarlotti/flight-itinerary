package com.example.flightitinerary.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.flightitinerary.data.api.ApiClient
import com.example.flightitinerary.data.models.Flight
import com.example.flightitinerary.utils.Utils.Companion.getTimestampFromStringDate

class FlightRepo {
    private val apiService = ApiClient.apiService

    suspend fun getAllFlights() : List<Flight> {
        return apiService.getFlights().data
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getFlights(departureAirport: String, arrivalAirport: String, begin: String, end: String): List<Flight> {
        return apiService.getFlights(departureAirport, arrivalAirport, getTimestampFromStringDate(begin), getTimestampFromStringDate(end)).data
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getFlightsDeparture(airport: String, begin: String, end: String): List<Flight> {
        return apiService.getFlightsDeparture(airport, getTimestampFromStringDate(begin), getTimestampFromStringDate(end)).data
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getFlightsArrival(airport: String, begin: String, end: String): List<Flight> {
        return apiService.getFlightsArrival(airport, getTimestampFromStringDate(begin), getTimestampFromStringDate(end)).data
    }
}