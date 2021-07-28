package com.bilal.cactus_notes_project.ui.signup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bilal.cactus_notes_project.R
import com.bilal.cactus_notes_project.databinding.ActivitySignupBinding
import com.bilal.cactus_notes_project.ui.api.api
import com.bilal.cactus_notes_project.ui.api.signup.RegisterRequest
import com.bilal.cactus_notes_project.ui.api.signup.RegisterResponse
import com.bilal.cactus_notes_project.ui.api.signup.error.RegisterErrorResponse
import com.bilal.cactus_notes_project.ui.signup.validation.EmailValidator
import com.bilal.cactus_notes_project.ui.signup.validation.PasswordValidator
import com.bilal.cactus_notes_project.ui.signup.validation.UsernameValidator
import com.bilal.cactus_notes_project.ui.user.UserStore
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val emailValidator = EmailValidator()
    private val passwordValidator = PasswordValidator()
    private val usernameValidator = UsernameValidator()

    private val userStore = UserStore(this)

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

                val emailError = emailValidator.validate(email).resolveAsString()
                val passwordError = passwordValidator.validate(password).resolveAsString()
                val usernameError = usernameValidator.validate(username).resolveAsString()
                textInputLayoutEmail.error = emailError
                textInputLayoutPassword.error = passwordError
                textInputLayoutUsername.error = usernameError

                if (emailError == null && passwordError == null && usernameError == null) {
                    sendRegisterRequest(email, username, password)
                }
            }

            buttonHaveAnAccount.setOnClickListener {
                // TODO: Go to LoginActivity
            }
        }
    }

    private fun sendRegisterRequest(email: String, username: String, password: String) {
        val request = RegisterRequest(email, username, password)

        api.register(request).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                when (response.code()) {
                    200 -> onSuccess(response.body()!!)
                    400 -> onClientError(response.errorBody()!!)
                    else -> onUnexpectedError()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Snackbar.make(binding.root, R.string.couldnt_connect, LENGTH_LONG).show()
            }
        })
    }

    private fun onSuccess(response: RegisterResponse) {
        userStore.saveJwt(response.jwt)
        // TODO: navigate to note list
    }

    private fun onClientError(responseBody: ResponseBody) {
        val bodyStr = responseBody.string()
        val errorResponse = Gson().fromJson(bodyStr, RegisterErrorResponse::class.java)
        val errorMessage = errorResponse.message[0].messages[0].message

        Snackbar.make(binding.root, errorMessage, LENGTH_LONG).show()
    }

    private fun onUnexpectedError() {
        Snackbar.make(binding.root, R.string.unexpected_error_occurred, LENGTH_LONG).show()
    }

    private fun Int?.resolveAsString() = this?.let { getString(it) }
}