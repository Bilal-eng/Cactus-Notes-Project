package com.bilal.cactus_notes_project.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bilal.cactus_notes_project.databinding.ActivityLoginBinding
import com.bilal.cactus_notes_project.ui.login.validation.IdentifierValidator
import com.bilal.cactus_notes_project.ui.login.validation.PasswordValidator

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val identifierValidator = IdentifierValidator()
    private val passwordValidator = PasswordValidator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding) {
            btnLogin.setOnClickListener {
                val identifier = textInputEditTextEmailLogin.text.toString().trim()
                val password = textInputEditTextPasswordLogin.text.toString().trim()

                val identifierResult = identifierValidator.validate(identifier).resolveAsString()
                val passwordResult = passwordValidator.validate(password).resolveAsString()

                textInputLayoutEmailLogin.error = identifierResult
                textInputLayoutPasswordLogin.error = passwordResult

                if (identifierResult == null && passwordResult == null) {
                    // TODO: Go to NotesActivity
                }

            }

            btnCreateAnAccount.setOnClickListener {
                // TODO: startNewActivity(SignupActivity::class.java)
            }
        }
    }

    private fun Int?.resolveAsString() = this?.let { getString(it) }
}