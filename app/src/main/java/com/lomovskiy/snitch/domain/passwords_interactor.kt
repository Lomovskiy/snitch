package com.lomovskiy.snitch.domain

import com.lomovskiy.snitch.domain.repo.PasswordsRepo
import kotlinx.coroutines.flow.StateFlow
import java.util.*

interface PasswordsInteractor {

    suspend fun savePassword(passwordEntity: PasswordEntity)

    fun subscribeOnPasswordsUpdates(): StateFlow<List<PasswordEntity>>

}

class PasswordsInteractorImpl(
    private val passwordsRepo: PasswordsRepo
) : PasswordsInteractor {

    override suspend fun savePassword(passwordEntity: PasswordEntity) {
        passwordsRepo.create(passwordEntity)
    }

    override fun subscribeOnPasswordsUpdates(): StateFlow<List<PasswordEntity>> {
        TODO()
    }

}