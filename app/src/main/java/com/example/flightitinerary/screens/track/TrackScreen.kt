package com.example.flightitinerary.screens.track

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flightitinerary.ui.components.FlightCard
import com.example.flightitinerary.utils.Utils
import com.mapbox.geojson.LineString
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.layers.generated.lineLayer
import com.mapbox.maps.extension.style.layers.properties.generated.LineCap
import com.mapbox.maps.extension.style.layers.properties.generated.LineJoin
import com.mapbox.maps.extension.style.sources.generated.geoJsonSource
import com.mapbox.maps.extension.style.style
import java.time.LocalDate
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TrackScreen(
    navController: NavController,
    icao24: String,
    time: Long
) {
    val viewModel = viewModel(modelClass = TrackViewModel::class.java)
    val track by viewModel.track.collectAsState()
    val flights by viewModel.flights.collectAsState()
    val configuration = LocalConfiguration.current
    val isTablet = 600.dp < configuration.screenWidthDp.dp
    val fillMaxHeight = if (isTablet) 1f else 0.8f
    val fillMaxWidth = if (isTablet) 0.5f else 1f

    LaunchedEffect(viewModel) {
        viewModel.fetchTrack(icao24, time)
        viewModel.fetchFlights(icao24, LocalDate.now().minusDays(3).toString(), LocalDate.now().toString())
    }

    if (track.path.isNotEmpty()) {
        val lon = track.path.first()[2] as Double
        val lat = track.path.first()[1] as Double

        val distance = calculateDistance(lat, lon, track.path.last()[1] as Double, track.path.last()[2] as Double)
        val zoom = when {
            distance < 100 -> 7.0
            distance < 500 -> 6.0
            distance < 1000 -> 4.0
            distance < 2000 -> 3.0
            distance < 5000 -> 2.0
            distance < 10000 -> 1.5
            else -> 1.0
        }

        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)

            ) {
                Box(
                    modifier = Modifier.clip(RoundedCornerShape(15.dp))
                ) {
                    AndroidView(
                        factory = { context ->
                            MapView(context).also {
                                it.mapboxMap.setCamera(
                                    CameraOptions.Builder()
                                        .center(Point.fromLngLat(lon, lat))
                                        .zoom(zoom)
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
                        modifier = Modifier
                            .fillMaxHeight(fillMaxHeight)
                            .fillMaxWidth(fillMaxWidth)
                    )
                }

                if (isTablet) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
                    ) {
                        Column (
                            Modifier
                                .border(2.dp, Color.LightGray, RoundedCornerShape(10.dp))
                                .padding(10.dp)
                        ) {
                            Text(text = "Vitesse: ?? km/h")
                            Text(text = "Altitude: ?? m")
                            Text(text = "Direction: ??°")
                            Text(text = "Latitude: ??")
                            Text(text = "Longitude: ??")
                        }

                        Box(
                            modifier = Modifier
                                .border(2.dp, Color.LightGray, RoundedCornerShape(10.dp))
                                .fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = "Vol des 3 derniers jours",
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }

                            LazyColumn {
                                items(flights) { flight ->
                                    val flightDuration = flight.lastSeen - flight.firstSeen
                                    FlightCard(
                                        navController = navController,
                                        flightNumber = flight.icao24,
                                        departureAirport = flight.departureAirportCity ?: flight.estDepartureAirport,
                                        arrivalAirport = flight.arrivalAirportCity ?: flight.estArrivalAirport,
                                        departureDate = Utils.secondsToDate(flight.firstSeen),
                                        arrivalDate = Utils.secondsToDate(flight.lastSeen),
                                        timeFlight = flight.firstSeen + 1,
                                        flightDuration = flightDuration
                                    )
                                }
                            }

                        }
                    }
                }
            }

            if (!isTablet) {
                Button(onClick = {}) {
                    Text(text = "Voir les détails")
                }
            }
        }
    }
}

fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val earthRadius = 6371.0 // Rayon moyen de la Terre en kilomètres

    // Convertir les latitudes et longitudes de degrés à radians
    val lat1Rad = Math.toRadians(lat1)
    val lon1Rad = Math.toRadians(lon1)
    val lat2Rad = Math.toRadians(lat2)
    val lon2Rad = Math.toRadians(lon2)

    // Calculer les différences de latitudes et longitudes
    val dLat = lat2Rad - lat1Rad
    val dLon = lon2Rad - lon1Rad

    // Formule de l'haversine
    val a = sin(dLat / 2) * sin(dLat / 2) + cos(lat1Rad) * cos(lat2Rad) * sin(dLon / 2) * sin(dLon / 2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))

    // Distance en kilomètres
    val distance = earthRadius * c

    return distance
}