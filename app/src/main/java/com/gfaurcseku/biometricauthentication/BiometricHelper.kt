package com.gfaurcseku.biometricauthentication

import android.content.Context
import android.os.Build
import androidx.biometric.BiometricManager


class BiometricHelper(context:Context) {
     private val context:Context

    init {
        this.context = context
    }

    fun isBiometricAvailable():CheckBiometric {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                val biometricManager = BiometricManager.from(context)
                if(biometricManager != null){
                    when(biometricManager.canAuthenticate()){
                        BiometricManager.BIOMETRIC_SUCCESS -> CheckBiometric(true, "App can authenticate using biometrics.")
                        BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> CheckBiometric(false, "No biometric features available on this device.")
                        BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> CheckBiometric(false, "Biometric features are currently unavailable.")
                        BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> CheckBiometric(false, "The user hasn't associated any biometric credentials with their account.")
                        else -> CheckBiometric(false,"")
                    }
                }else{
                    CheckBiometric(false,"")
                }

            }
            else -> {
                CheckBiometric(false,"")
            }
        }
    }

}

data class CheckBiometric(val status:Boolean , val message:String)