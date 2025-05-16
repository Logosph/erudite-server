package ru.logosph.utils


fun String.isValidEmail(): Boolean {
    val regex = "[A-Za-z0-9]+@[A-Za-z]+\\.[A-Za-z]{1,4}".toRegex()
    return regex.matches(this)
}