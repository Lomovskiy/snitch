package com.lomovskiy.snitch.data

import com.lomovskiy.snitch.domain.PasswordEntity
import com.lomovskiy.snitch.domain.repo.PasswordsRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PasswordsRepoImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher
) : PasswordsRepo {

    private val cache = mutableListOf<PasswordEntity>()

    override suspend fun create(entity: PasswordEntity) {
        withContext(dispatcher) {
            cache.add(entity)
        }
    }

    override suspend fun getById(id: String): PasswordEntity? {
        return withContext(dispatcher) {
            return@withContext cache.find { passwordEntity: PasswordEntity ->
                passwordEntity.id == id
            }
        }
    }

}
