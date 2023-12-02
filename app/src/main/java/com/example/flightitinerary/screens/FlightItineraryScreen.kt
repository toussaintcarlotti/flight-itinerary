package com.example.flightitinerary.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flightitinerary.R
import com.example.flightitinerary.screens.flightslist.FlightsListScreen
import com.example.flightitinerary.screens.home.HomeScreen


/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@Composable
fun FlightItineraryAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FlightItineraryApp() {
    var canNavigateBack by remember { mutableStateOf(false) }
    Scaffold(
        /*
        topBar = {
            FlightItineraryAppBar(
                canNavigateBack = canNavigateBack,
                navigateUp = {
                    canNavigateBack = false
                }
            )
        },*/
        content = { innerPadding -> FlightItineraryNavHost(Modifier.padding(innerPadding)) },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primary,
                contentPadding = PaddingValues(horizontal = 20.dp),
                modifier = Modifier
                    .height(70.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 24.dp, topEnd = 24.dp
                        )
                    )
            ) {
                Text(text = "BottomBar", textAlign = androidx.compose.ui.text.style.TextAlign.Center)
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FlightItineraryNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "home"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home") { HomeScreen(navController) }
        composable("flightsList") { FlightsListScreen() }
    }
}
