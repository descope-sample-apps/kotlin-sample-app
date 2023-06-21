package com.example.kotlinsampleapp.pages


import java.util.regex.Pattern

// Consider an otp valid if there's 6 digits and only numbers
private const val OTP_VALIDATION_REGEX = "^\\d{6}\$"

class OTPState(val otp: String? = null) :
    TextFieldState(validator = ::isOTPValid, errorFor = ::otpValidationError) {
    init {
        otp?.let {
            text = it
        }
    }
}

/**
 * Returns an error to be displayed or null if no error was found
 */
private fun otpValidationError(otp: String): String {
    return "Invalid otp: $otp"
}

private fun isOTPValid(otp: String): Boolean {
    return Pattern.matches(OTP_VALIDATION_REGEX, otp)
}

val OTPStateSaver = textFieldStateSaver(OTPState())
