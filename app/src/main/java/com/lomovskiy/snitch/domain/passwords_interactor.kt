package com.lomovskiy.snitch.domain

import com.lomovskiy.snitch.domain.repo.PasswordsRepo
import kotlinx.coroutines.flow.StateFlow

interface PasswordsInteractor {

    suspend fun savePassword(login: String, password: String)

    fun subscribeOnPasswordsUpdates(): StateFlow<List<PasswordEntity>>

}

class PasswordsInteractorImpl(
    private val passwordsRepo: PasswordsRepo
) : PasswordsInteractor {

    override suspend fun savePassword(login: String, password: String) {
        passwordsRepo.create(PasswordEntity(login, password))
    }

    override fun subscribeOnPasswordsUpdates(): StateFlow<List<PasswordEntity>> {
        return passwordsRepo.readAllFlow()
    }

}