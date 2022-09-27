package com.ugurrsnr.validationformscleanarchitecture.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurrsnr.validationformscleanarchitecture.domain.use_cases.ValidateEmail
import com.ugurrsnr.validationformscleanarchitecture.domain.use_cases.ValidatePassword
import com.ugurrsnr.validationformscleanarchitecture.domain.use_cases.ValidateRepeatedPassword
import com.ugurrsnr.validationformscleanarchitecture.domain.use_cases.ValidateTerms
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatedPassword : ValidateRepeatedPassword = ValidateRepeatedPassword(),
    private val validateTerms: ValidateTerms = ValidateTerms()
) : ViewModel() {

    var state by mutableStateOf(RegistirationFormState())

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event : RegistrationFormEvent){
        when(event){
            is RegistrationFormEvent.EmailChanged -> {
                state.copy(email = event.email)
            }
            is RegistrationFormEvent.PasswordChanged -> {
                state.copy(password = event.password)

            }
            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                state.copy(repeatedPassword = event.repeatedPassword)

            }
            is RegistrationFormEvent.AcceptTerms -> {
                state.copy(acceptedTerms = event.isAccepted)

            }
            is RegistrationFormEvent.Submit -> {
                submitData()
            }

        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val repatedPasswordResult = validateRepeatedPassword.execute(state.password,state.repeatedPassword)
        val termsResult = validateTerms.execute(state.acceptedTerms)

        val hasError = listOf(emailResult,passwordResult,repatedPasswordResult,termsResult)
            .any{ !it.successful}

        if ( hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repatedPasswordResult.errorMessage,
                termsError = termsResult.errorMessage
            )
            return
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }

}