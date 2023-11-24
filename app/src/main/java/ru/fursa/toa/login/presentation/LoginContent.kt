package ru.fursa.toa.login.presentation

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import ru.fursa.toa.R
import ru.fursa.toa.core.ui.components.PrimaryButton
import ru.fursa.toa.core.ui.components.SecondaryButton
import ru.fursa.toa.core.ui.components.TOAPasswordTextField
import ru.fursa.toa.core.ui.components.TOATextField
import ru.fursa.toa.core.ui.theme.TOATheme
import ru.fursa.toa.core.utils.UiText
import ru.fursa.toa.core.utils.getString
import ru.fursa.toa.login.domain.model.Credentials
import ru.fursa.toa.login.domain.model.Email
import ru.fursa.toa.login.domain.model.Password

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    viewState: LoginViewState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    val context = LocalContext.current

    Scaffold(containerColor = MaterialTheme.colorScheme.primary) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ic_toa),
                alignment = Alignment.Center,
                contentDescription = stringResource(R.string.logo),
                modifier = Modifier.size(250.dp)
            )

            Spacer(modifier = Modifier.height(120.dp))
            TOATextField(
                text = viewState.credentials.email.value,
                onTextChanged = { username -> onEmailChanged(username) },
                labelText = stringResource(R.string.email),
                modifier = Modifier.fillMaxWidth(),
                errorMessage = (viewState as? LoginViewState.InputError)?.emailInputErrorMessage
            )
            Spacer(modifier = Modifier.height(12.dp))
            TOAPasswordTextField(
                text = viewState.credentials.password.value,
                onTextChanged = { password -> onPasswordChanged(password) },
                labelText = stringResource(R.string.password),
                modifier = Modifier.fillMaxWidth(),
                errorMessage = (viewState as? LoginViewState.InputError)?.passwordInputErrorMessage
            )

            if (viewState is LoginViewState.SubmissionError) {
                Text(
                    modifier = Modifier.padding(all = 8.dp),
                    text = viewState.errorMessage.getString(context),
                    color = MaterialTheme.colorScheme.error
                )
            }

            Spacer(modifier = Modifier.height(48.dp))
            PrimaryButton(
                text = stringResource(R.string.log_in),
                onClick = { onLoginClick() },
                backgroundColor = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(12.dp))
            SecondaryButton(
                text = stringResource(R.string.sign_up),
                onClick = { onSignUpClick() },
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview(
    name = "Night mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Day mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun LoginContentPreview(
    @PreviewParameter(LoginViewStateProvider::class)
    viewState: LoginViewState,
) {

    TOATheme {
        Box(modifier = Modifier.fillMaxSize()) {
            LoginContent(
                viewState = viewState,
                onSignUpClick = { },
                onLoginClick = { },
                onPasswordChanged = { },
                onEmailChanged = { }
            )

            CircularProgressIndicator(
                modifier = Modifier
                    .padding(top = 25.dp)
                    .wrapContentSize()
                    .align(Alignment.Center),
                color = Color.White
            )
        }
    }
}

class LoginViewStateProvider : PreviewParameterProvider<LoginViewState> {
    override val values: Sequence<LoginViewState>
        get() {
            val activeCredentials = Credentials(
                Email("ilya.fursa08@gmail.com"),
                Password("12345678"),
            )

            val emptyCredentials = Credentials()

            return sequenceOf(
                LoginViewState.Initial,
                LoginViewState.Active(
                    activeCredentials,
                    emailInputErrorMessage = UiText.StringText("Please enter an email."),
                    passwordInputErrorMessage = UiText.StringText("Please enter a password"),
                ),
                LoginViewState.Submitting(activeCredentials),
                LoginViewState.SubmissionError(
                    credentials = activeCredentials,
                    errorMessage = UiText.StringText("Oops! Something went wrong..."),
                ),
                LoginViewState.InputError(
                    credentials = emptyCredentials,
                    emailInputErrorMessage = "Plz enter email",
                    passwordInputErrorMessage = "Plz enter password"
                ),
                LoginViewState.Active(
                    credentials = activeCredentials,
                ),
            )
        }
}