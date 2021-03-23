package com.lomovskiy.snitch

import android.app.Application
import com.lomovskiy.snitch.data.PasswordsRepoImpl
import com.lomovskiy.snitch.domain.PasswordsInteractor
import com.lomovskiy.snitch.domain.PasswordsInteractorImpl
import com.lomovskiy.snitch.domain.repo.PasswordsRepo

class AppLoader : Application() {

    companion object {
        lateinit var interactor: PasswordsInteractor
    }

    private lateinit var passwordsRepo: PasswordsRepo

    override fun onCreate() {
        super.onCreate()
        passwordsRepo = PasswordsRepoImpl()
        interactor = PasswordsInteractorImpl(passwordsRepo)
    }

}