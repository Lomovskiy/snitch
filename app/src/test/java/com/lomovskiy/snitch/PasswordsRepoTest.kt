package com.lomovskiy.snitch

import com.lomovskiy.snitch.data.PasswordsRepoImpl
import com.lomovskiy.snitch.domain.PasswordEntity
import com.lomovskiy.snitch.domain.repo.PasswordsRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.withTestContext
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.*

@ExperimentalCoroutinesApi
class PasswordsRepoTest {

    private lateinit var sut: PasswordsRepo

    @Before
    fun setUp() {
        sut = PasswordsRepoImpl(TestCoroutineDispatcher())
    }

    @Test
    fun createNewPassword_areCreatedSuccessfully() = runBlockingTest {
        val passwordEntityStub: PasswordEntity = PasswordEntity.stub()
        sut.create(passwordEntityStub)
        val passwordEntity: PasswordEntity? = sut.getById(passwordEntityStub.id)
        assertTrue(passwordEntity != null)
        assertEquals(passwordEntityStub.id, passwordEntity!!.id)
    }

    @Test
    fun getById_returnNull_whenPasswordNotExist() = runBlockingTest {
        val passwordEntity: PasswordEntity? = sut.getById(UUID.randomUUID().toString())
        assertTrue(passwordEntity == null)
    }

    @Test
    fun getById_returnEntity_whenPasswordExist() = runBlockingTest {
        val passwordEntityStub: PasswordEntity = PasswordEntity.stub()
        sut.create(passwordEntityStub)
        val passwordEntity: PasswordEntity? = sut.getById(passwordEntityStub.id)
        assertTrue(passwordEntity != null)
    }

    @Test
    fun getAll_returnEmptyList_whenPasswordsIsEmpty() = runBlockingTest {
        val passwords = sut.getAll().value
        assertTrue(passwords.isEmpty())
    }

    @Test
    fun getAll_notReturnEmptyList_whenPasswordsIsNotEmpty() = runBlockingTest {
        sut.create(PasswordEntity.stub())
        val passwords = sut.getAll().value
        assertTrue(passwords.isNotEmpty())
    }

}
