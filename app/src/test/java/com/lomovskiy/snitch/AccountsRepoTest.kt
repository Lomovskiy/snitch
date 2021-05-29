package com.lomovskiy.snitch

import com.lomovskiy.snitch.data.AccountsRepoImpl
import com.lomovskiy.snitch.domain.AccountEntity
import com.lomovskiy.snitch.domain.repo.AccountsRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class AccountsRepoTest {

    private lateinit var sut: AccountsRepo

    @Before
    fun setUp() {
        sut = AccountsRepoImpl(TestCoroutineDispatcher())
    }

    @Test
    fun createNewPassword_areCreatedSuccessfully() = runBlockingTest {
        val accountEntityStub: AccountEntity = AccountEntity.empty()
        sut.create(accountEntityStub)
        assertTrue(sut.readAll().value.contains(accountEntityStub))
    }

    @Test
    fun readAll_returnEmpty_whenAccountsDontExist() = runBlockingTest {
        assertTrue(sut.readAll().value.isEmpty())
    }

    @Test
    fun readAll_returnNotEmpty_whenAccountsExist() = runBlockingTest {
        sut.create(AccountEntity.empty())
        assertTrue(sut.readAll().value.isNotEmpty())
    }

}
