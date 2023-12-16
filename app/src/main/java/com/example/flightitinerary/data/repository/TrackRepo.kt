package com.example.flightitinerary.data.repository

import com.example.flightitinerary.data.api.ApiClient
import com.example.flightitinerary.data.models.Track

class TrackRepo {
    private val apiService = ApiClient.apiService

    suspend fun getTrackByAircraft(icao24: String, time: Long): Track {
        return apiService.getTrackByAircraft(icao24, time).data
    }
}