package com.example.flightitinerary.data.repository

import com.example.flightitinerary.data.api.ApiClient
import com.example.flightitinerary.data.models.Airport

class AirportRepo() {
    private val apiService = ApiClient.apiService

    suspend fun getAllAirPorts(): List<Airport> {
        return apiService.getAirports().data;
    }
}