package com.ugurrsnr.validationformscleanarchitecture.domain.use_cases

import android.util.Patterns


class ValidateEmail {

    fun execute(email : String) : ValidationResult{
        if (email.isBlank()){
            return ValidationResult(
                successful = false,
                errorMessage = "The email cannot be blank!" //could be extracted as string resource for usecase
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(
                successful = false,
                errorMessage = "That is not  valid email" //could be extracted as string resource for usecase
            )
        }
        return ValidationResult(
            successful = true
        )

    }
}