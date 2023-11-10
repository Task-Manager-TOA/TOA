package ru.fursa.toa.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import ru.fursa.toa.R
import ru.fursa.toa.ui.theme.TOATheme

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    val buttonColors = buttonColors(containerColor = color)

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.button_height)),
        shape = RoundedCornerShape(50),
        colors = buttonColors
    ) {
        Text(text = text.toUpperCase(Locale.current))
    }
}

@Preview(
    name = "Night mode",
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "Day mode",
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
fun PrimaryButtonPreview() {
    TOATheme {
        PrimaryButton(
            text = "Primary Button",
            onClick = { }
        )
    }
}