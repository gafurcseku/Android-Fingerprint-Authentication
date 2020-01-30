package com.gfaurcseku.biometricauthentication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    messageText.text = "Authentication error: $errString"

                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    messageText.text = "Authentication succeeded!"

                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    messageText.text = "Authentication failed"
                }
            })

        createAuthentication.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Sign in")
            .setNegativeButtonText("CANCEL")
            .build()


    }
}
