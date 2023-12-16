package com.example.flightitinerary.screens.track

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightitinerary.data.models.Flight
import com.example.flightitinerary.data.models.Track
import com.example.flightitinerary.data.repository.FlightRepo
import com.example.flightitinerary.data.repository.TrackRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class TrackViewModel : ViewModel() {
    private val repository = TrackRepo();
    private val flightRepository = FlightRepo();

    private val _track = MutableStateFlow(Track("", "", 0, 0, emptyArray()))
    val track: StateFlow<Track> = _track

    private val _flights = MutableStateFlow<List<Flight>>(emptyList())
    val flights: StateFlow<List<Flight>> = _flights

    fun fetchTrack(icao24: String, time: Long) {
        viewModelScope.launch {
            _track.value = repository.getTrackByAircraft(icao24, time)
        }
    }

    fun fetchFlights(aircraft: String, startDate: String, endDate: String) {
        viewModelScope.launch {
            _flights.value = flightRepository.getFlightsByAircraft(aircraft, startDate, endDate)
        }
    }
}