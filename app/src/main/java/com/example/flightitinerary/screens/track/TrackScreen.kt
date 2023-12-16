package com.example.flightitinerary.screens.track

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mapbox.geojson.LineString
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.layers.generated.lineLayer
import com.mapbox.maps.extension.style.layers.properties.generated.LineCap
import com.mapbox.maps.extension.style.layers.properties.generated.LineJoin
import com.mapbox.maps.extension.style.sources.generated.geoJsonSource
import com.mapbox.maps.extension.style.style

@OptIn(MapboxExperimental::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TrackScreen(icao24: String, time: Long) {
    val viewModel = viewModel(modelClass = TrackViewModel::class.java)
    val track by viewModel.track.collectAsState()


    LaunchedEffect(viewModel) {
        viewModel.fetchTrack(icao24, time)
    }

    if (track.path.isNotEmpty()) {
        val lon = track.path.first()[2] as Double
        val lat = track.path.first()[1] as Double
        AndroidView(
            factory = { context ->
                MapView(context).also {
                    it.mapboxMap.setCamera(
                        CameraOptions.Builder()
                            .center(Point.fromLngLat(lon, lat))
                            .zoom(5.0)
                            .build()
                    )
                    it.mapboxMap.loadStyle(
                        style(style = Style.STANDARD) {
                            +geoJsonSource("line") {
                                geometry(LineString.fromLngLats(track.path.map {
                                    val lon = it[2] as Double
                                    val lat = it[1] as Double
                                    Point.fromLngLat(lon, lat)
                                }))
                            }
                            +lineLayer("linelayer", "line") {
                                lineCap(LineCap.ROUND)
                                lineJoin(LineJoin.ROUND)
                                lineOpacity(1.0)
                                lineWidth(8.0)
                                lineColor(Color.Red.hashCode())
                            }
                        }
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        ) {

        }
    }
}
