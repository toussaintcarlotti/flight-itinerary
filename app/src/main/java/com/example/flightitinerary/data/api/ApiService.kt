package com.example.flightitinerary.data.api

import com.example.flightitinerary.data.models.Airport
import com.example.flightitinerary.data.models.Flight
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("airports")
    suspend fun getAirports(): List<Airport>

    @GET("flight/all/{begin}/{end}")
    suspend fun getAllFlights(@Path("begin") begin: Int, @Path("end") end: Int): List<Flight>

    @GET("flights/departure/{airport}/{begin}/{end}")
    suspend fun getFlightsByAirport(@Path("airport") airport: String, @Path("begin") begin: Int, @Path("end") end: Int): List<Flight>
}