package org.akrck02.countless.system.biometric

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity


@RequiresApi(Build.VERSION_CODES.R)
fun authenticateWithBiometrics(
    context: Context,
    onError: () -> Unit,
    onSuccess: () -> Unit
) {


    val biometricManager = BiometricManager.from(context)
    val isBiometricAvailable = biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)


    when (isBiometricAvailable) {
        BiometricManager.BIOMETRIC_SUCCESS -> {
            Log.d("Countless", "App can authenticate using biometrics.")
        }

        BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
            Log.e("Countless", "No biometric features available on this device.")
            onError()
            return
        }

        BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
            Log.e("Countless", "Biometric features are currently unavailable.")
            onError()
            return
        }

        BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
            Log.e("Countless", "None enrolled")
            onError()
            return
        }

        BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
            Log.e("Countless", "None enrolled")
            onError()
            return
        }

        BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
            Log.e("Countless", "BIOMETRIC_ERROR_UNSUPPORTED")
            onError()
            return
        }

        BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
            Log.e("Countless", "BIOMETRIC_STATUS_UNKNOWN")
            onError()
            return
        }
    }

    val executor = ContextCompat.getMainExecutor(context)

    // Lets the user authenticate using either a Class 3 biometric or
    // their lock screen credential (PIN, pattern, or password).
    val promptInfo: PromptInfo = PromptInfo.Builder()
        .setTitle("Entering countless app.")
        .setSubtitle("Please enter your fingerprint to access.")
        .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
        .build()


    val biometricPrompt = BiometricPrompt(
        context as FragmentActivity,
        executor,
        object : BiometricPrompt.AuthenticationCallback() {

            @RequiresApi(Build.VERSION_CODES.R)
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                // handle authentication success here
                onSuccess()
            }

            override fun onAuthenticationFailed() {
                onError()
            }

        }
    )

    biometricPrompt.authenticate(promptInfo)
}

