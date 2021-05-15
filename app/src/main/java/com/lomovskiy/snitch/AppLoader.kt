package com.lomovskiy.snitch

import android.app.Application
import com.lomovskiy.snitch.data.PasswordsRepoImpl
import com.lomovskiy.snitch.domain.PasswordsInteractor
import com.lomovskiy.snitch.domain.PasswordsInteractorImpl
import com.lomovskiy.snitch.domain.repo.PasswordsRepo

class AppLoader : Application() {

    companion object {

        private val passwordsRepo: PasswordsRepo = PasswordsRepoImpl()

        val interactor: PasswordsInteractor = PasswordsInteractorImpl(passwordsRepo)

    }

}
