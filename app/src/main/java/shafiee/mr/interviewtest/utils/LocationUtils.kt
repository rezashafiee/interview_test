package shafiee.mr.interviewtest.utils

import android.Manifest
import android.content.Context
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task

///////////////////////////////////
// Created by Mohammadreza Shafiee
///////////////////////////////////

class LocationUtils(
    private val context: Context,
    private val locationSupportView: LocationSupportView
) {

    private val fusedLocationProviderClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    // must be called first
    fun createLocationRequest() {
        locationRequest = LocationRequest.create().apply {
            interval = 30_000 // ms
            fastestInterval = 5_000 // ms
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                onLocationChanged(locationResult?.lastLocation)
            }
        }

        val builder =
            LocationSettingsRequest.Builder().apply { addLocationRequest(locationRequest) }

        val client: SettingsClient = LocationServices.getSettingsClient(context)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

        handleLocationSettingsDialogResponse(task)
    }

    fun getLocation() {

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
            fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                if (it != null)
                    onLocationChanged(it)
                else
                    fusedLocationProviderClient.requestLocationUpdates(
                        locationRequest,
                        locationCallback,
                        null
                    )
            }
        else
            locationSupportView.showPermissionRequestDialog()
    }

    private fun handleLocationSettingsDialogResponse(task: Task<LocationSettingsResponse>) {
        task.addOnSuccessListener { locationSettingsResponse ->
            // All location settings are satisfied. The client can initialize
            // location requests here.
            // ...
            if (locationSettingsResponse.locationSettingsStates.isGpsUsable)
                getLocation()
            else
                context.shortToast("GPS is not enabled")
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    locationSupportView.showLocationSettingDialog(exception)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    private fun onLocationChanged(lastLocation: Location?) {
        // New location received
        locationSupportView.onLocationProvided(lastLocation)
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }
}