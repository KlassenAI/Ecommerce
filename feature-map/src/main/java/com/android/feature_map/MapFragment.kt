package com.android.feature_map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.android.core.extensions.showToast
import com.android.feature_map.databinding.FragmentMapBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import java.util.*

class MapFragment : Fragment(R.layout.fragment_map) {

    companion object {
        private const val PERMISSION_CODE = 100
        private const val PERMISSION_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
        private const val PERMISSION_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
    }

    private val binding: FragmentMapBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addSeveralRandomPoints()

        binding.btnFind.setOnClickListener {
            setUserLocation()
        }
    }

    private fun getLocationManager(): LocationManager {
        return requireActivity().getSystemService(LOCATION_SERVICE) as LocationManager
    }

    private fun setUserLocation() {
        if (checkLocationPermissions()) {
            if (isLocationEnabled()) {
                val location = getLastKnownLocation()
                if (location != null) {
                    val point = Point(location.latitude, location.longitude)
                    binding.mapView.map.move(
                        CameraPosition(point, 14.0f, 0.0f, 0.0f),
                        Animation(Animation.Type.SMOOTH, 1f),
                        null
                    )
                    binding.mapView.map.mapObjects.addPlacemark(point)
                } else {
                    showToast("Не удалось получить текущее местоположение")
                }
            } else {
                showToast("Пожалуйста, включите интернет или gps для определения местоположения")
            }
        } else {
            requestLocationPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation(): Location? {
        val locationManager = getLocationManager()
        val providers: List<String> = locationManager.getProviders(true)
        var bestLocation: Location? = null
        for (provider in providers) {
            val l: Location = locationManager.getLastKnownLocation(provider) ?: continue
            if (bestLocation == null || l.accuracy < bestLocation.accuracy) {
                bestLocation = l
            }
        }
        return bestLocation
    }

    private fun checkLocationPermissions(): Boolean {
        return checkSelfPermission(PERMISSION_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(PERMISSION_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkSelfPermission(permission: String): Int {
        return ActivityCompat.checkSelfPermission(requireContext(), permission)
    }

    private fun requestLocationPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(PERMISSION_FINE_LOCATION, PERMISSION_COARSE_LOCATION),
            PERMISSION_CODE
        )
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getLocationManager()
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun addSeveralRandomPoints() {
        repeat(10) { binding.mapView.map.mapObjects.addPlacemark(getRandomPoint()) }
    }

    private fun getRandomPoint(): Point {
        return Point(getRandomDouble(-85.0, 85.0), getRandomDouble(-180.0, 180.0))
    }

    private fun getRandomDouble(min: Double, max: Double): Double {
        val random = Random()
        return min + (max - min) * random.nextDouble()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapView.onStart()
    }

    override fun onStop() {
        binding.mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}