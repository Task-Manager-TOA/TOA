package ru.fursa.toa.login.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.fursa.toa.R
import ru.fursa.toa.ui.components.PrimaryButton
import ru.fursa.toa.ui.components.SecondaryButton
import ru.fursa.toa.ui.components.TOAPasswordTextField
import ru.fursa.toa.ui.components.TOATextField
import ru.fursa.toa.ui.theme.TOATheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    viewState: LoginViewState,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
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
                text = viewState.username,
                onTextChanged = { username -> onUsernameChanged(username) },
                labelText = stringResource(R.string.username),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))
            TOAPasswordTextField(
                text = viewState.password,
                onTextChanged = { password -> onPasswordChanged(password) },
                labelText = stringResource(R.string.password),
                modifier = Modifier.fillMaxWidth()
            )
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
fun LoginContentPreview() {

    val viewState = LoginViewState(
        username = "ilya.fursa",
        password = "12345678"
    )

    TOATheme {
        LoginContent(
            viewState = viewState,
            onSignUpClick = { },
            onLoginClick = { },
            onPasswordChanged = { },
            onUsernameChanged = { }
        )
    }
}