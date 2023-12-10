package com.example.flightitinerary.data.models

import androidx.compose.runtime.saveable.Saver

data class Airport(
    val code: String,
    val lat: String,
    val lon: String,
    val name: String,
    val city: String,
    val state: String,
    val country: String,
    val woeid: String,
    val tz: String,
    val phone: String,
    val type: String,
    val email: String,
    val url: String,
    val runway_length: String,
    val elev: String,
    val icao: String,
    val direct_flights: String,
    val carriers: String
) {
    @Override
    override fun toString(): String {
        return city
    }

    fun getFormattedName(): String {
        return "$code $city ($country)"
    }

    companion object {
        fun saver() = Saver<Airport, String>(
            save = { "${it.code}|${it.lat}|${it.lon}|${it.name}|${it.city}|${it.state}|${it.country}|${it.woeid}|${it.tz}|${it.phone}|${it.type}|${it.email}|${it.url}|${it.runway_length}|${it.elev}|${it.icao}|${it.direct_flights}|${it.carriers}" },
            restore = {
                val split = it.split("|")
                Airport(
                    code = split[0],
                    lat = split[1],
                    lon = split[2],
                    name = split[3],
                    city = split[4],
                    state = split[5],
                    country = split[6],
                    woeid = split[7],
                    tz = split[8],
                    phone = split[9],
                    type = split[10],
                    email = split[11],
                    url = split[12],
                    runway_length = split[13],
                    elev = split[14],
                    icao = split[15],
                    direct_flights = split[16],
                    carriers = split[17]
                )
            }
        )
    }
}