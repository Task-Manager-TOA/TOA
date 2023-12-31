package ru.fursa.toa.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import ru.fursa.toa.R


private val UrbanistExtraBold = FontFamily(Font(R.font.urbanist_extrabold))
private val UrbanistSemiBold = FontFamily(Font(R.font.urbanist_semibold))
private val UrbanistBold = FontFamily(Font(R.font.urbanist_bold))
private val UrbanistMedium = FontFamily(Font(R.font.urbanist_medium))
private val UrbanistLight = FontFamily(Font(R.font.urbanist_light))
private val UrbanistRegular = FontFamily(Font(R.font.urbanist_regular))

val TOATypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = UrbanistExtraBold,
        fontSize = 40.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = UrbanistExtraBold,
        fontSize = 35.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = UrbanistSemiBold,
        fontSize = 13.sp
    ),
    titleMedium = TextStyle(
        fontFamily = UrbanistMedium,
        fontSize = 15.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = UrbanistLight,
        fontSize = 13.sp
    ),
    labelMedium = TextStyle(
        fontFamily = UrbanistRegular,
        fontSize = 12.sp
    )
)
