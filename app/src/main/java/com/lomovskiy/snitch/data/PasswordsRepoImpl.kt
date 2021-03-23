package com.lomovskiy.snitch.data

import com.lomovskiy.snitch.domain.PasswordEntity
import com.lomovskiy.snitch.domain.repo.PasswordsRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PasswordsRepoImpl : PasswordsRepo {

    private val cache = MutableStateFlow<List<PasswordEntity>>(emptyList())

    override suspend fun create(entity: PasswordEntity) {
        cache.emit(cache.value.toMutableList().apply { add(entity) })
    }

    override fun readAllFlow(): StateFlow<List<PasswordEntity>> {
        return cache
    }

}
