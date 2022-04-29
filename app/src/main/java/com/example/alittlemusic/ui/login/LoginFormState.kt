package com.example.alittlemusic.ui.login

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val phoneError: Int? = null,
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)