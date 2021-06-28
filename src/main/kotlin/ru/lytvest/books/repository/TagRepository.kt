package ru.lytvest.books.repository

import org.springframework.data.repository.CrudRepository
import ru.lytvest.books.entity.Tag
import java.util.*

interface TagRepository : CrudRepository<Tag, Long> {
    fun findByName(name:String): Optional<Tag>
}