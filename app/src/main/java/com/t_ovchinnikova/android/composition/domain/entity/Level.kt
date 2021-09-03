package com.t_ovchinnikova.android.composition.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class Level : Parcelable {
    TEST,
    EASY,
    NORMAL,
    HARD
}