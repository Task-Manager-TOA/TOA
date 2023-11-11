package ru.fursa.toa.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import ru.fursa.toa.R
import ru.fursa.toa.ui.theme.TOATheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TOATextField(
    text: String,
    onTextChanged: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChanged,
        label = { Text(text = labelText) },
        shape = RoundedCornerShape(50),
        modifier = modifier.heightIn(dimensionResource(id = R.dimen.text_field_height))
    )
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