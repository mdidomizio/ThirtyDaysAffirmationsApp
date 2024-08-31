package com.example.a30daysaffirmationapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    @StringRes val title: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val description: Int,
    @StringRes val dayNumber: Int
)
