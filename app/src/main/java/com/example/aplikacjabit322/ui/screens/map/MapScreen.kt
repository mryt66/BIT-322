package com.example.aplikacjabit322.ui.screens.map

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aplikacjabit322.Bit322TopAppBar
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

object MapDestination {
    const val route = "map"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullMapScreen(
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = {Bit322TopAppBar(
            title = "Hobby Map",
            canNavigateBack = true,
            navigateUp = navigateBack

        )}
    ) {
        MapScreen(modifier = Modifier.padding(it))
    }
}

@Composable
fun MapScreen(mapViewModel: MapViewModel = viewModel(), modifier: Modifier) {
    val context = LocalContext.current
    var mapViewInitialized = false // Flaga, by nie wykonywać wielokrotnie efektu

    // Inicjalizacja i aktualizacja MapView
    val mapView = rememberMapView(mapViewModel)

    // Uruchom efekt po inicjalizacji mapy
    LaunchedEffect(mapViewInitialized) {
        if (!mapViewInitialized) {
            mapViewModel.addMarker(mapView, GeoPoint(50.2945, 18.6714), "Start Point")
            mapViewInitialized = true
        }
    }

    // Wyświetlenie MapView
    AndroidView(
        factory = { mapView },
        modifier = modifier.fillMaxSize()
    )

    // Przycisk lokalizacji
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        IconButton(
            onClick = {
                mapViewModel.getCurrentLocation(
                    onLocationAvailable = { geoPoint ->
                        mapViewModel.centerMap(mapView, geoPoint)
                    },
                    onError = {
                        Toast.makeText(
                            context,
                            "Nie udało się uzyskać lokalizacji",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            },
            modifier = Modifier.size(60.dp)
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location Button",
                tint = Color.Black
            )
        }
    }
}

// Funkcja pomocnicza do inicjalizacji MapView
@Composable
fun rememberMapView(mapViewModel: MapViewModel): MapView {
    val context = LocalContext.current
    return remember {
        MapView(context).apply {
            mapViewModel.initializeMapView(this)
        }
    }
}
