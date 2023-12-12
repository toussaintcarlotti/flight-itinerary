package com.example.flightitinerary.data.repository

import com.example.flightitinerary.data.api.ApiClient
import com.example.flightitinerary.data.models.Flight

class FlightRepo {
    private val apiService = ApiClient.apiService

    suspend fun getAllFlights() : List<Flight> {
        return apiService.getFlights().data
    }

    suspend fun getFlightsByAirport(airport: String, begin: Int, end: Int): List<Flight> {
        return apiService.getFlightsByAirport(airport, begin, end).data
    }
}