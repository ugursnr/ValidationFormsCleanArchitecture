package com.ugurrsnr.validationformscleanarchitecture.domain.use_cases

import android.util.Patterns


class ValidateTerms {

    fun execute(acceptedTerms : Boolean) : ValidationResult{
        if (!acceptedTerms){
            return ValidationResult(
                successful = false,
                errorMessage = "Please accept the terms" //could be extracted as string resource for usecase
            )
        }

        return ValidationResult(
            successful = true
        )

    }
}