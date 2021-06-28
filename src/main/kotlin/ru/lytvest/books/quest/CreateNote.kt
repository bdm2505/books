package ru.lytvest.books.quest

import ru.lytvest.books.entity.Note
import ru.lytvest.books.entity.NoteFullText
import ru.lytvest.books.entity.Tag
import ru.lytvest.books.entity.UserInfo

data class CreateNote(
    val title:String = "",
    val text:String = "",
    val tags:String = ""
){
    fun toNote(author: UserInfo, allTags: MutableIterable<Tag>): Note {
        val map = allTags.map { it.id.toString() to it }.toMap()
        val list = tags.split(" ").mapNotNull { map[it] }

        val note = Note(title, shortText(), author, list.toSet(), NoteFullText(text))
        note.full.note = note
        return note
    }

    private fun shortText() : String{
        if (text.length <= 500 )
            return text

        val index = text.lastIndexOf(' ', 499)

        if (index in 400..496)
            return text.substring(0..index) + "..."

        return text.substring(0..496) + "..."
    }
}
