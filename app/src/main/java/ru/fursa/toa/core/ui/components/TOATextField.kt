package ru.fursa.toa.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults.textFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.fursa.toa.R
import ru.fursa.toa.core.ui.theme.TOATheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TOATextField(
    text: String,
    onTextChanged: (String) -> Unit,
    labelText: String,
    errorMessage: String? = null,
    modifier: Modifier = Modifier,
) {
    val textColors = textFieldColors(
        textColor = Color.Black,
        containerColor = Color.White
    )

    Column {
        OutlinedTextField(
            value = text,
            colors = textColors,
            onValueChange = onTextChanged,
            label = { Text(text = labelText, color = Color.White) },
            shape = RoundedCornerShape(50),
            isError = errorMessage != null,
            modifier = modifier.heightIn(dimensionResource(id = R.dimen.text_field_height)),
        )

        Text(
            text = errorMessage.orEmpty(),
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(start = 16.dp)
        )
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
fun PreviewTOATextField() {
    TOATheme {
        Surface {
            TOATextField(
                text = "TOA text field",
                onTextChanged = { },
                labelText = "Label"
            )
        }
    }
}

@Preview(
    name = "Error",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Error",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun PreviewTOATextFieldError() {
    TOATheme {
        Surface {
            TOATextField(
                text = "TOA text field",
                onTextChanged = { },
                labelText = "Label",
                errorMessage = "Label field is wrong"
            )
        }
    }
}