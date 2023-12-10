package com.example.flightitinerary.ui.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun <T> DropdownUi(label: String, listItems: List<String>, onSelectItem: (String) -> Unit = {}, stateSaver: Saver<T, String>) {
    // state of the menu
    var expanded by remember {
        mutableStateOf(false)
    }

    // selected item
    var selectedItem by rememberSaveable {
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
            value = selectedItem.toString(),
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
                        onSelectItem(selectedOption)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}