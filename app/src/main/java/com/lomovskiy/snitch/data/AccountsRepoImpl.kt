package com.lomovskiy.snitch.data

import com.lomovskiy.snitch.domain.AccountEntity
import com.lomovskiy.snitch.domain.repo.AccountsRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountsRepoImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher
) : AccountsRepo {

    private val cache = MutableStateFlow(emptyList<AccountEntity>())

    override suspend fun create(entity: AccountEntity) {
        withContext(dispatcher) {
            cache.value = cache.value.toMutableList().apply {
                add(entity)
            }
        }
    }

    override fun readAll(): StateFlow<List<AccountEntity>> {
        return cache
    }

}
