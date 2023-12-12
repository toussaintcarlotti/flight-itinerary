package com.example.flightitinerary.screens.home

import android.os.Build
import android.util.Range
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flightitinerary.data.models.Airport
import com.example.flightitinerary.ui.components.DropdownUi
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    val airports by viewModel.state.collectAsState()

    var isButtonEnabled by rememberSaveable { mutableStateOf(false) }
    var departureAirport by rememberSaveable { mutableStateOf<Airport?>(null) }
    var arrivalAirport by rememberSaveable { mutableStateOf<Airport?>(null) }

    val calendarState = rememberUseCaseState()
    val selectedDateRange = remember {
        val value = Range(LocalDate.now(), LocalDate.now().plusDays(2))
        mutableStateOf(value)
    }

    Column {
        Text(
            text = "Trouvez votre vol",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
        ) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                DropdownUi(
                    label = "Ville de départ",
                    listItems = airports.map { it },
                    onSelectItem = {
                        isButtonEnabled = true
                        departureAirport = it
                    },
                    saverState = Airport.saver()
                )
            }
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                DropdownUi(
                    label = "Ville d'arrivée",
                    listItems = airports.map { it },
                    onSelectItem = {
                        isButtonEnabled = true
                        arrivalAirport = it
                    },
                    saverState = Airport.saver()
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        8.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column (modifier = Modifier.padding(8.dp)) {
                        Text(text = "Du")
                        Text(
                            text = selectedDateRange.value.lower.format(
                                DateTimeFormatter.ofPattern(
                                    "dd/MM/yyyy"
                                )
                            ),
                        )
                    }

                    Icon(Icons.Filled.ChevronRight, contentDescription = "Arrow")

                    Column (modifier = Modifier.padding(8.dp)) {
                        Text(text = "Au")
                        Text(
                            text = selectedDateRange.value.upper.format(
                                DateTimeFormatter.ofPattern(
                                    "dd/MM/yyyy"
                                )
                            ),
                        )
                    }
                }

                Button(
                    onClick = { calendarState.show() },
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(text = "Modifier la date")
                }

                Button(
                    onClick = { navController.navigate("flightsList/${departureAirport?.icao}/${arrivalAirport?.icao}/${selectedDateRange.value.lower}/${selectedDateRange.value.upper}") },
                    enabled = isButtonEnabled,
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(text = "Trouver un vol")
                }
            }
        }
    }
    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            locale = Locale.FRANCE,
            style = CalendarStyle.MONTH,
        ),
        selection = CalendarSelection.Period(
            selectedRange = selectedDateRange.value
        ) { startDate, endDate ->
            selectedDateRange.value = Range(startDate, endDate)
        },
    )
}





