package ru.lytvest.books.controller

import org.springframework.security.core.Authentication
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import ru.lytvest.books.entity.Note
import ru.lytvest.books.entity.UserInfo
import ru.lytvest.books.entity.UserPass
import ru.lytvest.books.repository.NoteRepository
import ru.lytvest.books.repository.UserPassRepository

@RestController
class NotesController(
    val noteRepository: NoteRepository,
    val userPassRepository: UserPassRepository,
) {

    @GetMapping("notes")
    fun notes(model: ModelMap, auth: Authentication?): ModelAndView {
        model["auth"] = auth
        model["notes-header"] = true
        model["notes"] = noteRepository.findAll()
        return ModelAndView("notes", model)
    }

    @GetMapping("user/note-create")
    fun noteCreate(model: ModelMap, auth: Authentication?): ModelAndView {
        model["user"] = auth.user() ?: return ModelAndView("redirect:/login")
        model["notes-header"] = true
        println("get note create")
        return ModelAndView("note-create", model)
    }

    @PostMapping("user/note-create")
    fun noteCreate(note: Note?, model: ModelMap, auth: Authentication?): ModelAndView {
        println("post note create!!")
        val user = auth.user() ?: return ModelAndView("redirect:/login")
        model["user"] = user

        if (note == null || note.text.length < 10 || note.title.length < 4){
            model["error"] = true
            return ModelAndView("note-create", model)
        }
        note.author = user
        noteRepository.save(note)
        return ModelAndView("redirect:/notes")
    }
}

fun Authentication?.user(): UserInfo? {
    if (this == null || this.principal !is UserPass)
        return null
    return (this.principal as UserPass).userInfo
}