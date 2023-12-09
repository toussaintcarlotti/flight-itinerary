package com.example.flightitinerary.data.api

import com.example.flightitinerary.data.models.Flight
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.Date

interface ApiService {
    @GET("flight/all/{begin}/{end}")
    fun getAllFlights(@Path("begin") begin: Date, @Path("end") end: Date): List<Flight>
}