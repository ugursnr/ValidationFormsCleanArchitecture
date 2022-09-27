package com.ugurrsnr.validationformscleanarchitecture.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ugurrsnr.validationformscleanarchitecture.domain.use_cases.ValidateEmail
import com.ugurrsnr.validationformscleanarchitecture.domain.use_cases.ValidatePassword
import com.ugurrsnr.validationformscleanarchitecture.domain.use_cases.ValidateRepeatedPassword
import com.ugurrsnr.validationformscleanarchitecture.domain.use_cases.ValidateTerms

class MainViewModel(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatedPassword : ValidateRepeatedPassword = ValidateRepeatedPassword(),
    private val validateTerms: ValidateTerms =ValidateTerms()
) : ViewModel() {

    var state by mutableStateOf(RegistirationFormState())
}