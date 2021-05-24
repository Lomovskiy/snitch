package com.lomovskiy.snitch.domain

import java.util.*

class PasswordEntity(
    val id: String,
    val url: String,
    val name: String,
    val login: String,
    val password: String
) {

    companion object {

        fun stub(): PasswordEntity {
            return PasswordEntity(
                id = UUID.randomUUID().toString(),
                url = "url",
                name = "name",
                login = "login",
                password = "password"
            )
        }

    }

}
