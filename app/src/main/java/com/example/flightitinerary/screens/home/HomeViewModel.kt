package com.example.flightitinerary.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightitinerary.data.models.Airport
import com.example.flightitinerary.data.repository.AirportRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = AirportRepo();


    private val _state = MutableStateFlow(emptyList<Airport>())
    val state: StateFlow<List<Airport>> get() = _state

    init {
        viewModelScope.launch {
            val airports = repository.getAllAirPorts()
            _state.value = airports
        }
    }
}