package com.lomovskiy.snitch.domain.repo

import com.lomovskiy.snitch.domain.AccountEntity
import kotlinx.coroutines.flow.StateFlow

interface AccountsRepo {

    suspend fun create(entity: AccountEntity)

    fun readAll(): StateFlow<List<AccountEntity>>

}
