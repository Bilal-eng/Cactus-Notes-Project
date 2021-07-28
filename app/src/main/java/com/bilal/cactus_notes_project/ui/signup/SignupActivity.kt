package com.bilal.cactus_notes_project.ui.signup

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bilal.cactus_notes_project.databinding.ActivitySignupBinding
import com.bilal.cactus_notes_project.ui.signup.validation.EmailValidator
import com.bilal.cactus_notes_project.ui.signup.validation.PasswordValidator
import com.bilal.cactus_notes_project.ui.signup.validation.UsernameValidator


class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val emailValidator = EmailValidator()
    private val passwordValidator = PasswordValidator()
    private val usernameValidator = UsernameValidator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        with(binding) {
            btnSignup.setOnClickListener {
                val email = textInputEditTextEmail.text.toString()
                val username = textInputEditTextUsername.text.toString()
                val password = textInputEditTextPassword.text.toString()

                val emailResult = emailValidator.validate(email).resolveAsString()
                val passwordResult = passwordValidator.validate(password).resolveAsString()
                val usernameResult = usernameValidator.validate(username).resolveAsString()
                textInputLayoutEmail.error = emailResult
                textInputLayoutPassword.error = passwordResult
                textInputLayoutUsername.error = usernameResult

                if (emailResult == null && passwordResult == null && usernameResult == null) {
                    Toast.makeText(this@SignupActivity, "Success", Toast.LENGTH_LONG).show()
                    // TODO: Go to LoginActivity
                }

            }

            buttonHaveAnAccount.setOnClickListener {
                //TODO: Go to LoginActivity
            }
        }

    }

    private fun Int?.resolveAsString() = this?.let { getString(it) }
}