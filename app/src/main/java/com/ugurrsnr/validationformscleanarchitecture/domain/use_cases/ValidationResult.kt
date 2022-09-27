package com.ugurrsnr.validationformscleanarchitecture.domain.use_cases

data class ValidationResult(
    val successful : Boolean,
    val errorMessage : String? = null
) {
}