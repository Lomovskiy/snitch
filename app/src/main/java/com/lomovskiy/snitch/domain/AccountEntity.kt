package com.lomovskiy.snitch.domain

import java.util.*

class AccountEntity(
    val id: String,
    val name: String,
    val type: Type,
    val balance: Double
) {

    enum class Type {
        CASH,
        CARD
    }

    companion object {

        fun empty(): AccountEntity {
            return AccountEntity(
                id = UUID.randomUUID().toString(),
                name = "name1",
                type = Type.CARD,
                balance = 0.0
            )
        }

    }

}
