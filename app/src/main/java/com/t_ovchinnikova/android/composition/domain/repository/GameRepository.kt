package com.t_ovchinnikova.android.composition.domain.repository

import com.t_ovchinnikova.android.composition.domain.entity.GameSettings
import com.t_ovchinnikova.android.composition.domain.entity.Level
import com.t_ovchinnikova.android.composition.domain.entity.Question

interface GameRepository {

    fun generateQuestion(maxSumValue: Int, countOfOptions: Int): Question

    fun getGameSettings(level: Level): GameSettings
}