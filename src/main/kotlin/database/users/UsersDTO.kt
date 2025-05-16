package ru.logosph.database.users

data class UsersDTO(
    val login: String,
    val password: String,
    val email: String,
)