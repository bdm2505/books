package ru.lytvest.books.repository

import org.springframework.data.repository.CrudRepository

import ru.lytvest.books.entity.UserPass
import java.util.*

interface UserPassRepository :CrudRepository< UserPass, Long> {
    fun findByLogin(login: String): Optional<UserPass>
}