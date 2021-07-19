package com.bilal.cactus_notes_project.ui

import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.bilal.cactus_notes_project.R
import com.bilal.cactus_notes_project.databinding.ActivitySignupBinding


class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val userNameRegex = "^[a-z0-9_]*$".toRegex()
    private val passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).+$".toRegex()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSignup.setOnClickListener {
            val email = binding.textInputEditTextEmail.text
            val userName = binding.textInputEditTextUserName.text
            val password = binding.textInputEditTextPassword.text
            if (checkEmailAddress(email.toString()) and checkUserName(userName.toString()) and checkPassword(
                    password.toString()
                )
            ) {
                // TODO: Go to LoginActivity
            }

        }

        binding.textViewHaveAnAccount.setOnClickListener {
            //TODO: Go to LoginActivity
        }
    }

    private fun checkPassword(password: String): Boolean {
        return if (password.isEmpty() || password.isBlank()) {
            binding.textInputLayoutPassword.error = getString(R.string.password_is_required)
            false
        } else if (password.length <= 7) {
            binding.textInputLayoutPassword.error = getString(R.string.password_is_too_short)
            false
        } else if (password.length >= 40) {
            binding.textInputLayoutPassword.error = getString(R.string.password_is_too_long)
            false
        } else if (!passwordRegex.matches(password)) {
            binding.textInputLayoutPassword.error = getString(R.string.password_restrictions)
            false
        } else {
            binding.textInputLayoutPassword.error = null
            true
        }
    }

    private fun checkUserName(userName: String): Boolean {
        return if (userName.isBlank() || userName.isEmpty()) {
            binding.textInputLayoutUserName.error = getString(R.string.username_is_required)
            false
        } else if (!userNameRegex.matches(userName)) {
            binding.textInputLayoutUserName.error =
                getString(R.string.username_restrictions)
            false
        } else if (userName.length <= 2) {
            binding.textInputLayoutUserName.error = getString(R.string.username_is_too_short)
            false
        } else if (userName.length >= 20) {
            binding.textInputLayoutUserName.error = getString(R.string.username_is_too_long)
            false
        } else {
            binding.textInputLayoutUserName.error = null
            true
        }
    }

    private fun checkEmailAddress(email: String): Boolean {
        return if (email.isBlank() || email.isEmpty()) {
            binding.textInputLayoutEmail.error = getString(R.string.email_is_required)
            false
        } else if (email.length > 50 || email.length < 5 || !Patterns.EMAIL_ADDRESS.matcher(email)
                .matches()
        ) {
            binding.textInputLayoutEmail.error = getString(R.string.email_is_invalid)
            false
        } else {
            binding.textInputLayoutEmail.error = null
            true
        }

    }

}