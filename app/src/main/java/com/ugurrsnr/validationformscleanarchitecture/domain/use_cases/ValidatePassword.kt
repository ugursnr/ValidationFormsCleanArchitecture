package com.ugurrsnr.validationformscleanarchitecture.domain.use_cases

import android.util.Patterns


class ValidatePassword {

    fun execute(password : String) : ValidationResult{
        if (password.length < 8){
            return ValidationResult(
                successful = false,
                errorMessage = "Password should be longer than 8 characters" //could be extracted as string resource for usecase
            )
        }
        val containsLetterAndDigits = password.any{ it.isDigit()} && password.any { it.isLetter() }

        if (!containsLetterAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "Password should contain digits and letters" //could be extracted as string resource for usecase
            )
        }
        return ValidationResult(
            successful = true
        )

    }
}