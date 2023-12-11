package com.example.flightitinerary.data.repository

import android.content.Context
import com.example.flightitinerary.data.models.Airport
import com.google.gson.Gson

class AirportRepo(private val context: Context) {
    fun getAllAirPorts(): List<Airport> {
        return try {
            val fileInString: String = context.assets.open("airports.json").bufferedReader().use { it.readText() }
            Gson().fromJson(fileInString, Array<Airport>::class.java).toList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun getAirportByCity(city: String): Airport? {
        return getAllAirPorts().find { it.city == city }
    }
}