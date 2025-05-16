package ru.logosph.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens : Table("tokens") {

    private val login = varchar("login", 50)
    private val token = varchar("token", 50)

    fun insertToken(tokensDTO: TokensDTO) {
        transaction {
            insert {
                it[login] = tokensDTO.login
                it[token] = tokensDTO.token
            }
        }
    }
}