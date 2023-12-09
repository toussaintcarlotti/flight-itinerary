package com.example.flightitinerary.data.repository

import com.example.flightitinerary.data.api.ApiClient
import com.example.flightitinerary.data.models.Flight
import java.util.Date

class FlightRepo {
    private val apiService = ApiClient.apiService

    fun getAllFlights(begin: Date, end: Date): List<Flight> {
        return apiService.getAllFlights(begin, end)
    }
}