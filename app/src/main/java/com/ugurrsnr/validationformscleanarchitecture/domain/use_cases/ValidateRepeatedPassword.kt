package com.ugurrsnr.validationformscleanarchitecture.domain.use_cases

import android.util.Patterns


class ValidateRepeatedPassword {

    fun execute(password : String, repeatedPassword : String) : ValidationResult{
        if (password != repeatedPassword){
            return ValidationResult(
                successful = false,
                errorMessage = "The passwords don't match" //could be extracted as string resource for usecase
            )
        }

        return ValidationResult(
            successful = true
        )

    }
}