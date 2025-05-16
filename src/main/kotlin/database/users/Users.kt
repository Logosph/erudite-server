package ru.logosph.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.logosph.database.tokens.TokensDTO

object Users : Table("users") {
    private val email = varchar("email", length = 50)
    private val password = varchar("password", length = 50)
    private val login = varchar("login", length = 50)

    fun insert(usersDTO: UsersDTO) {
        transaction {
            insert {
                it[email] = usersDTO.email
                it[password] = usersDTO.password
                it[login] = usersDTO.login
            }
        }
    }

    fun fetchUser(login: String): UsersDTO? {
        return try {
            transaction {
                Users.selectAll().where { Users.login eq login }.singleOrNull()?.let { row ->
                    UsersDTO(
                        login = row[Users.login],
                        password = row[Users.password],
                        email = row[Users.email]
                    )
                }
            }
        } catch (e: Exception) {
            null
        }
    }
}