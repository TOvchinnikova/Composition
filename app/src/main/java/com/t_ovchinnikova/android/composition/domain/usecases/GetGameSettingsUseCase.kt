package com.t_ovchinnikova.android.composition.domain.usecases

import com.t_ovchinnikova.android.composition.domain.entity.GameSettings
import com.t_ovchinnikova.android.composition.domain.entity.Level
import com.t_ovchinnikova.android.composition.domain.repository.GameRepository

class GetGameSettingsUseCase(private val repository: GameRepository) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}