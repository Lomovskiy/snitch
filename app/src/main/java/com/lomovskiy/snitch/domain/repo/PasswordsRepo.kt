package com.lomovskiy.snitch.domain.repo

import com.lomovskiy.snitch.domain.PasswordEntity
import kotlinx.coroutines.flow.StateFlow

interface PasswordsRepo {

    suspend fun create(entity: PasswordEntity)

    suspend fun getById(id: String): PasswordEntity?

//    fun readAllFlow(): StateFlow<List<PasswordEntity>>

}
