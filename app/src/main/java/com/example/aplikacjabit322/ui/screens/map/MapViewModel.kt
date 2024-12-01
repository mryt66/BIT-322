package com.example.aplikacjabit322.ui.screens.map

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MapViewModel(application: Application) : AndroidViewModel(application) {

    private val appContext = getApplication<Application>().applicationContext
    private val defaultPoint = GeoPoint(50.2945, 18.6714)
    private var mapView: MapView? = null
    private var currentMarker: Marker? = null

    fun initializeMapView(mapView: MapView) {
        this.mapView = mapView
        Configuration.getInstance().apply {
            load(appContext, appContext.getSharedPreferences("osmdroid", 0))
            setUserAgentValue(appContext.packageName)
        }
        mapView.apply {
            controller.setZoom(17.0)
            setMinZoomLevel(15.0)
            setMaxZoomLevel(19.0)
            controller.setCenter(defaultPoint)
        }
    }

    fun getMapView(): MapView {
        return mapView ?: throw IllegalStateException("MapView is not initialized")
    }

    fun centerMap(mapView: MapView, geoPoint: GeoPoint, zoomLevel: Double = 15.0) {
        mapView.controller.apply {
            setCenter(geoPoint)
            setZoom(zoomLevel)
        }
    }

    fun addMarker(mapView: MapView, point: GeoPoint, title: String) {
        currentMarker?.let {
            mapView.overlays.remove(it)
        }
        val marker = Marker(mapView).apply {
            position = point
            this.title = title
        }
        mapView.overlays.add(marker)
        currentMarker = marker
        mapView.invalidate()
    }

    fun getCurrentLocation(
        onLocationAvailable: (GeoPoint) -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val fusedLocationProviderClient =
                    LocationServices.getFusedLocationProviderClient(appContext)
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        val geoPoint = GeoPoint(location.latitude, location.longitude)
                        onLocationAvailable(geoPoint)
                    } else {
                        onError()
                    }
                }.addOnFailureListener {
                    onError()
                }
            } catch (e: SecurityException) {
                Toast.makeText(appContext, "Brak dostępu do lokalizacji", Toast.LENGTH_SHORT).show()
                onError()
            }
        }
    }
}
