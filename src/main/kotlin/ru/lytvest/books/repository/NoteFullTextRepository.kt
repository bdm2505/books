package ru.lytvest.books.repository

import org.springframework.data.repository.CrudRepository
import ru.lytvest.books.entity.NoteFullText

interface NoteFullTextRepository : CrudRepository<NoteFullText, Long> {
}