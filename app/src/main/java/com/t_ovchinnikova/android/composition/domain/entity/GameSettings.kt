package com.t_ovchinnikova.android.composition.domain.entity

import java.io.Serializable

data class GameSettings (
    val maxSumValue: Int,
    val minCountOfRightAnswers: Int,
    val minPercentOfRightAnswer: Int,
    val gameTimeInSeconds: Int
): Serializable