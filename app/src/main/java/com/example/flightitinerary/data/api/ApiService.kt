package com.example.flightitinerary.data.api

import com.example.flightitinerary.data.models.Airport
import com.example.flightitinerary.data.models.ApiResponse
import com.example.flightitinerary.data.models.Flight
import com.example.flightitinerary.data.models.Track
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("airports")
    suspend fun getAirports(): ApiResponse<List<Airport>>

    @GET("flights")
    suspend fun getFlights(): ApiResponse<List<Flight>>

    @GET("flights/departure-arrival/{departure}/{arrival}/{begin}/{end}")
    suspend fun getFlights(
        @Path("departure") departure: String,
        @Path("arrival") arrival: String,
        @Path("begin") begin: Long,
        @Path("end") end: Long
    ): ApiResponse<List<Flight>>

    @GET("flights/departure/{airport}/{begin}/{end}")
    suspend fun getFlightsDeparture(
        @Path("airport") airport: String,
        @Path("begin") begin: Long,
        @Path("end") end: Long
    ): ApiResponse<List<Flight>>

    @GET("flights/arrival/{airport}/{begin}/{end}")
    suspend fun getFlightsArrival(
        @Path("airport") airport: String,
        @Path("begin") begin: Long,
        @Path("end") end: Long
    ): ApiResponse<List<Flight>>

    @GET("tracks/{icao24}/{time}")
    suspend fun getTrackByAircraft(
        @Path("icao24") icao24: String,
        @Path("time") time: Long
    ): ApiResponse<Track>
}