package ru.fursa.toa.ui.login

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.fursa.toa.ui.components.PrimaryButton
import ru.fursa.toa.ui.components.SecondaryButton
import ru.fursa.toa.ui.theme.TOATheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    viewState: LoginViewState,
) {
    Scaffold(containerColor = MaterialTheme.colorScheme.primary) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        )
        {
            Spacer(modifier = Modifier.weight(1f))
            PrimaryButton(
                text = "Log in",
                onClick = { },
                backgroundColor = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(12.dp))
            SecondaryButton(
                text = "Sign up",
                onClick = { },
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
        LoginContent(viewState = viewState)
    }
}