package com.example.flightitinerary.data.models

class Flight(
    val icao24: String,
    val firstSeen: Long,
    val estDepartureAirport: String,
    val lastSeen: Long,
    val estArrivalAirport: String,
    val callsign: String,
    val estDepartureAirportHorizDistance: Int,
    val estDepartureAirportVertDistance: Int,
    val estArrivalAirportHorizDistance: Int,
    val estArrivalAirportVertDistance: Int,
    val departureAirportCandidatesCount: Int,
    val arrivalAirportCandidatesCount: Int
) {}