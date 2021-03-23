package com.lomovskiy.snitch.domain

import kotlin.random.Random

class PasswordEntity(
    val login: String,
    val password: String
) {

    companion object {

        fun stub(): PasswordEntity {
            return PasswordEntity(
                login = Random(5).nextInt().toString(),
                password = Random(5).nextInt().toString()
            )
        }

    }

}
