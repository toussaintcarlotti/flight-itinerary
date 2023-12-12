package com.example.flightitinerary.screens.flightslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightitinerary.data.models.Flight
import com.example.flightitinerary.data.repository.FlightRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FlightsViewModel : ViewModel() {
    private val repository = FlightRepo();


    private val _state = MutableStateFlow(emptyList<Flight>())
    val state: StateFlow<List<Flight>> get() = _state

    init {
        viewModelScope.launch {
            val flights = repository.getAllFlights()
            _state.value = flights
        }
    }
}