package ru.fursa.toa.core.utils

import android.content.Context
import androidx.annotation.StringRes

sealed class UiText {
   data class StringText(val value: String): UiText()
   data class ResourceText(@StringRes val value: Int): UiText()
}

fun UiText.getString(context: Context): String {
    return when(this) {
        is UiText.StringText -> this.value
        is UiText.ResourceText -> context.getString(this.value)
    }
}