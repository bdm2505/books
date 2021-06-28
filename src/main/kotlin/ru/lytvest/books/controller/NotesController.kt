package ru.lytvest.books.controller

import org.springframework.security.core.Authentication
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import ru.lytvest.books.entity.Note
import ru.lytvest.books.entity.UserInfo
import ru.lytvest.books.entity.UserPass
import ru.lytvest.books.quest.CreateNote
import ru.lytvest.books.repository.NoteFullTextRepository
import ru.lytvest.books.repository.NoteRepository
import ru.lytvest.books.repository.TagRepository

@RestController
class NotesController(
    val noteRepository: NoteRepository,
    val tagRepository: TagRepository,
    val noteFullTextRepository: NoteFullTextRepository,
) {

    @GetMapping("notes")
    fun notes(model: ModelMap, auth: Authentication?, @RequestParam("tags") tags:String?): ModelAndView {
        model["auth"] = auth
        model["notes-header"] = true

        model["notes"] = tags?.let { noteRepository.findByTagsContains(tagRepository.findByName(it).get()) } ?: noteRepository.findAll()
        return ModelAndView("notes", model)
    }
    @GetMapping("note/{id}")
    fun noteById(model: ModelMap, auth: Authentication?, @PathVariable id:Long): ModelAndView {
        model["user"] = auth.user()
        model["notes-header"] = true
        val note = noteRepository.findById(id).orElse(null)
        model["note"] = note
        model["full-text"] = note?.full?.fullText
        return ModelAndView("note", model)
    }

    @GetMapping("user/note-create")
    fun noteCreate(model: ModelMap, auth: Authentication?): ModelAndView {
        model["user"] = auth.user() ?: return ModelAndView("redirect:/login")
        model["all-tags"] = tagRepository.findAll()
        model["notes-header"] = true
        println("get note create")
        return ModelAndView("note-create", model)
    }

    @PostMapping("user/note-create")
    fun noteCreate(createNote: CreateNote?, model: ModelMap, auth: Authentication?): ModelAndView {
        println("post note create!!")
        val user = auth.user() ?: return ModelAndView("redirect:/login")
        model["user"] = user

        if (createNote == null || createNote.text.length < 10 || createNote.title.length < 4){
            model["error"] = true
            return ModelAndView("note-create", model)
        }
        val note = createNote.toNote(user, tagRepository.findAll())
        noteRepository.save(note)
        noteFullTextRepository.save(note.full)
        return ModelAndView("redirect:/notes")
    }
}

fun Authentication?.user(): UserInfo? {
    if (this == null || this.principal !is UserPass)
        return null
    return (this.principal as UserPass).userInfo
}