package com.example.flightitinerary.screens.home

import android.os.Build
import android.util.Range
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
fun HomeScreen(navController: NavController) {
    var isButtonEnabled by remember { mutableStateOf(false) }
    var startCity by remember { mutableStateOf("") }
    var endCity by remember { mutableStateOf("") }

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
            Box(modifier = Modifier.weight(1f)) {
                DropdownUi(
                    label = "Ville de départ",
                    listItems = arrayOf("Paris", "Lyon", "Marseille"),
                    onSelectItem = {
                        isButtonEnabled = true
                        startCity = it
                    }
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                // set endCity to the selected item
                DropdownUi(
                    label = "Ville d'arrivée",
                    listItems = arrayOf("Paris", "Lyon", "Marseille"),
                    onSelectItem = {
                        isButtonEnabled = true
                        endCity = it
                    }
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
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
                    Box(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.onSurface,
                                shape = RoundedCornerShape(20.dp)
                            ),
                    ) {
                        Text(
                            text = selectedDateRange.value.lower.format(
                                DateTimeFormatter.ofPattern(
                                    "dd/MM/yyyy"
                                )
                            ),
                            modifier = Modifier.padding(8.dp),
                        )
                    }

                    Icon(Icons.Filled.ChevronRight, contentDescription = "Arrow")

                    Box(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.onSurface,
                                shape = RoundedCornerShape(20.dp)
                            ),
                    ) {
                        Text(
                            text = selectedDateRange.value.upper.format(
                                DateTimeFormatter.ofPattern(
                                    "dd/MM/yyyy"
                                )
                            ),
                            modifier = Modifier.padding(8.dp),
                        )
                    }
                }

                Button(onClick = { calendarState.show() }) {
                    Text(text = "Modifier la date")
                }

                Button(
                    onClick = { navController.navigate("flightsList") },
                    enabled = isButtonEnabled,
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


