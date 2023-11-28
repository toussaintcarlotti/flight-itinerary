package com.example.flightitinerary.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flightitinerary.R
import rememberFlightTakeoff

@Composable
fun HomeScreen(navController: NavController) {
    var isButtonEnabled by remember { mutableStateOf(false) }
    var startCity by remember { mutableStateOf("") }
    var endCity by remember { mutableStateOf("") }

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
                startCity = DropdownUi(
                    label = "Ville de départ",
                    listItems = arrayOf("Paris", "Lyon", "Marseille"),
                    onSelectItem = { isButtonEnabled = true }
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                endCity = DropdownUi(
                    label = "Ville d'arrivée",
                    listItems = arrayOf("Paris", "Lyon", "Marseille"),
                    onSelectItem = { isButtonEnabled = true }
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = { navController.navigate("flightsList") },
                enabled = isButtonEnabled,
            ) {
                Text(text = "Trouver un vol")
            }
        }

        // convert this date to unix time : 2023-11-28 12:00:00
        // result : 1672258800
        // end for the date : 2023-11-28 13:00:00
        // result : 1672262400
    }
}


@Composable
fun DropdownUi(label: String, listItems: Array<String>, onSelectItem: (Boolean) -> Unit = {}): String {
    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
    }

    // remember the selected item
    var selectedItem by remember {
        mutableStateOf("")
    }

    // box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        // text field
        TextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
        )

        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // this is a column scope
            // all the items are added vertically
            listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(
                    text = { Text(text = selectedOption) },
                    onClick = {
                        selectedItem = selectedOption
                        expanded = false
                        onSelectItem(true)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
    return selectedItem
}
