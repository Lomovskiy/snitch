package com.lomovskiy.snitch.domain

class PasswordEntity(
    val name: String,
    val login: String,
    val password: String
) {

    companion object {

        fun stub(): PasswordEntity {
            return PasswordEntity(
                name = "name",
                login = "login",
                password = "password"
            )
        }

    }

}
