package com.lomovskiy.snitch

import android.app.Application
import com.lomovskiy.snitch.data.PasswordsRepoImpl
import com.lomovskiy.snitch.domain.PasswordsInteractor
import com.lomovskiy.snitch.domain.PasswordsInteractorImpl
import com.lomovskiy.snitch.domain.repo.PasswordsRepo
import kotlinx.coroutines.Dispatchers

class AppLoader : Application() {

    companion object {

        private val passwordsRepo: PasswordsRepo = PasswordsRepoImpl(Dispatchers.Default)

        val interactor: PasswordsInteractor = PasswordsInteractorImpl(passwordsRepo)

    }

}
