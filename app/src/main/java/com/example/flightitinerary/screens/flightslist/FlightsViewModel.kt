package com.example.flightitinerary.screens.flightslist

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightitinerary.data.models.Flight
import com.example.flightitinerary.data.repository.FlightRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class FlightsViewModel : ViewModel() {
    private val repository = FlightRepo();

    private val _flights = MutableStateFlow<List<Flight>>(emptyList())
    val flights: StateFlow<List<Flight>> = _flights

    fun fetchFlights(departureAirport: String?, arrivalAirport: String?, startDate: String, endDate: String) {
        viewModelScope.launch {
            try {
                if (departureAirport != null && arrivalAirport != null) {
                    val response = repository.getFlights(departureAirport, arrivalAirport, startDate, endDate)
                    _flights.value = response
                } else if(departureAirport != null) {
                    val response = repository.getFlightsDeparture(departureAirport, startDate, endDate)
                    _flights.value = response
                } else if(arrivalAirport != null) {
                    val response = repository.getFlightsArrival(arrivalAirport, startDate, endDate)
                    _flights.value = response
                } else {
                    val response = repository.getAllFlights()
                    _flights.value = response
                }

            } catch (e: Exception) {
                // Gérer les erreurs
            }
        }
    }
}