package com.bangkitcapstone.safedisaster.ui.screen.maps

import android.Manifest
import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.whenStarted
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapsScreen() {
    val context = LocalContext.current
    val locationPermissionState = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)

    val lifecycleOwner = LocalLifecycleOwner.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val location = remember { mutableStateOf<LatLng?>(null) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 10f) // Initial position
    }

    val shouldCenterOnUserLocation = remember { mutableStateOf(false) }

    // State for the marker
    val markerState = remember { mutableStateOf<MarkerState?>(null) }

    LaunchedEffect(key1 = true) {
        lifecycleOwner.lifecycle.whenStarted {
            locationPermissionState.launchPermissionRequest()
            if (locationPermissionState.hasPermission) {
                try {
                    fusedLocationClient.lastLocation.addOnSuccessListener { loc: Location? ->
                        loc?.let {
                            val currentLocation = LatLng(it.latitude, it.longitude)
                            location.value = currentLocation
                            cameraPositionState.position = CameraPosition.fromLatLngZoom(currentLocation, 10f)

                            // Update marker state
                            markerState.value = MarkerState(position = currentLocation)
                        }
                    }
                } catch (e: SecurityException) {
                    // Handle the exception
                }
            }
        }
    }

    LaunchedEffect(shouldCenterOnUserLocation.value) {
        if (shouldCenterOnUserLocation.value && location.value != null) {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(location.value!!, 10f)
            shouldCenterOnUserLocation.value = false
        }
    }

    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val (map, overlay, button) = createRefs()

        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(map) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            cameraPositionState = cameraPositionState
        ){
            // Add marker on the map
            markerState.value?.let { marker ->
                Marker(
                    state = marker,
                    title = "My Location",
                    snippet = "You are here"
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .constrainAs(overlay) {
                    top.linkTo(parent.top)
                }
                .background(
                    color = Color(android.graphics.Color.parseColor("#31304D")),
                    shape = RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)
                )
                .padding(17.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Peta Jalur evakuasi",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Ikuti jalur evakuasi bencana",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        }

//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(100.dp)
//                .constrainAs(overlay) {
//                    bottom.linkTo(parent.bottom)
//                }
//                .background(
//                    color = Color(android.graphics.Color.parseColor("#31304D")),
//                    shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
//                )
//                .padding(17.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text(
//                text = "Peta Jalur evakuasi",
//                color = Color.White,
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold
//            )
//            Text(
//                text = "Ikuti jalur evakuasi bencana",
//                color = Color.White,
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Normal
//            )
//        }

        Button(
            onClick = {
//                shouldCenterOnUserLocation.value = true
                      },
            modifier = Modifier
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                }
        ) {
            Text("Go to My Location")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapsScreenPreview() {
    MapsScreen()
}
