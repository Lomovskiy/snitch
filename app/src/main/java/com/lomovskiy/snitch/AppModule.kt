package com.lomovskiy.snitch

import com.lomovskiy.snitch.data.AccountsRepoImpl
import com.lomovskiy.snitch.domain.repo.AccountsRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    companion object {

        @Provides
        fun provideDispatcher(): CoroutineDispatcher {
            return Dispatchers.Default
        }

    }

    @Binds
    abstract fun bindAccountsRepo(impl: AccountsRepoImpl): AccountsRepo

}