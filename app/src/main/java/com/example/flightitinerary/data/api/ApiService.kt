package com.example.flightitinerary.data.api

import com.example.flightitinerary.data.models.Airport
import com.example.flightitinerary.data.models.ApiResponse
import com.example.flightitinerary.data.models.Flight
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("airports")
    suspend fun getAirports(): ApiResponse<List<Airport>>

    @GET("flights")
    suspend fun getFlights(): ApiResponse<List<Flight>>

    @GET("flight/all/{begin}/{end}")
    suspend fun getAllFlights(@Path("begin") begin: Int, @Path("end") end: Int): ApiResponse<List<Flight>>

    @GET("flights/departure/{airport}/{begin}/{end}")
    suspend fun getFlightsByAirport(@Path("airport") airport: String, @Path("begin") begin: Int, @Path("end") end: Int): ApiResponse<List<Flight>>
}