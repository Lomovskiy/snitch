package com.lomovskiy.snitch.data

import com.lomovskiy.snitch.domain.PasswordEntity
import com.lomovskiy.snitch.domain.repo.PasswordsRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PasswordsRepoImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher
) : PasswordsRepo {

    private val cache = MutableStateFlow<List<PasswordEntity>>(emptyList())

    override suspend fun create(entity: PasswordEntity) {
        withContext(dispatcher) {
            val currentList = cache.value.toMutableList()
            currentList.add(entity)
            cache.value = currentList
        }
    }

    override suspend fun getById(id: String): PasswordEntity? {
        return withContext(dispatcher) {
            val currentList = cache.value
            return@withContext currentList.find { passwordEntity: PasswordEntity ->
                passwordEntity.id == id
            }
        }
    }

    override fun getAll(): StateFlow<List<PasswordEntity>> {
        return cache
    }

}
