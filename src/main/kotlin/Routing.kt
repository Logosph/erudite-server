package ru.logosph

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.logosph.auth.AuthSchema
import ru.logosph.auth.SignupSchema
import ru.logosph.database.tokens.Tokens
import ru.logosph.database.tokens.TokensDTO
import ru.logosph.database.users.Users
import ru.logosph.database.users.UsersDTO
import ru.logosph.utils.isValidEmail
import java.util.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        post("/signup") {
            val user = call.receive<SignupSchema>()
            if (!user.email.isValidEmail()) {
                call.respond(HttpStatusCode.BadRequest)
            }

            val userDTO = Users.fetchUser(user.login)

            if (userDTO == null) {
                Users.insert(
                    UsersDTO(
                        login = user.login,
                        password = user.password,
                        email = user.email,
                    )
                )
                val token = UUID.randomUUID().toString()
                Tokens.insertToken(
                    TokensDTO(
                        login = user.login,
                        token = token
                    )
                )
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.Conflict)
            }
        }
        post("/login") {
            val user = call.receive<AuthSchema>()
            val userDTO = Users.fetchUser(user.login)
            if (userDTO == null) {
                call.respond(HttpStatusCode.NotFound)
            }
            if (userDTO!!.password != user.password) {
                call.respond(HttpStatusCode.Unauthorized)
            }
            val token = UUID.randomUUID().toString()
            Tokens.insertToken(
                TokensDTO(
                    login = user.login,
                    token = token
                )
            )
            call.respond(HttpStatusCode.OK, message = token)

        }
    }
}
