@file:Suppress("MagicNumber")

package ru.fursa.toa.core.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val PrimaryBlue = Color(0xFF0A3751)
private val SecondaryOrange = Color(0xFFFC8138)
private val BackgroundBlue = Color(0xFFF4F4F8)
private val OnBackgroundBlack = Color(0xFF282828)

val lightColorPalette = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = Color.White,
    secondary = SecondaryOrange,
    onSecondary = Color.White,
    background = BackgroundBlue,
    onBackground = OnBackgroundBlack,
    surface = Color.White,
    onSurface = PrimaryBlue
)

