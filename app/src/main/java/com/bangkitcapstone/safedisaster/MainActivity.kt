package com.bangkitcapstone.safedisaster

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.bangkitcapstone.safedisaster.ui.theme.SafeDisasterTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        setContent {
            SafeDisasterTheme {
                val postNotificationPermission = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
                LaunchedEffect(key1=true){
                    if (postNotificationPermission.hasPermission){
                        postNotificationPermission.launchPermissionRequest()
                    }
                }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    // Setup the back pressed callback
                    val callback = object : OnBackPressedCallback(true) {
                        override fun handleOnBackPressed() {
                            if (!navController.navigateUp()) {
                                this.isEnabled = false
                                onBackPressed()
                            }
                        }
                    }
                    LaunchedEffect(Unit) {
                        onBackPressedDispatcher.addCallback(callback)
                    }

                    ComposeApp()
                }
            }
        }
    }

}
