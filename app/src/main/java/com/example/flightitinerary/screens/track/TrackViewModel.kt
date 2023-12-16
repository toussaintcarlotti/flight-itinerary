package com.example.flightitinerary.screens.track

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightitinerary.data.models.Track
import com.example.flightitinerary.data.repository.TrackRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class TrackViewModel : ViewModel() {
    private val repository = TrackRepo();

    private val _track = MutableStateFlow(Track("", "", 0, 0, emptyArray()))
    val track: StateFlow<Track> = _track

    fun fetchTrack(icao24: String, time: Long) {
        viewModelScope.launch {
            _track.value = repository.getTrackByAircraft(icao24, time)
        }
    }
}