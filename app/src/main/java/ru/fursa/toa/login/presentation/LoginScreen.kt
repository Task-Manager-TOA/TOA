package ru.fursa.toa.login.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val viewState = viewModel.viewState.collectAsState()

    LoginContent(
        viewState = viewState.value,
        onEmailChanged = viewModel::emailChanged,
        onPasswordChanged = viewModel::passwordChanged,
        onLoginClick = viewModel::loginButtonClicked,
        onSignUpClick = viewModel::signUpButtonClicked
    )
}