package ru.lytvest.books

import org.springframework.security.core.Authentication
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import ru.lytvest.books.entity.UserInfo
import ru.lytvest.books.entity.UserPass
import ru.lytvest.books.entity.UserRegistration
import ru.lytvest.books.repository.NoteRepository
import ru.lytvest.books.repository.UserInfoRepository
import ru.lytvest.books.repository.UserPassRepository

@RestController
class BaseController(
    val noteRepository: NoteRepository,
    val userPassRepository: UserPassRepository,
    val userInfoRepository: UserInfoRepository,
) {

    @GetMapping
    fun index(model: ModelMap, auth: Authentication?): ModelAndView {
        model["auth"] = auth
        model["books"] = true
        return ModelAndView("index", model)
    }

    @GetMapping("chats")
    fun chats(model: ModelMap, auth: Authentication?): ModelAndView {
        model["auth"] = auth
        model["chats"] = true
        return ModelAndView("chats", model)
    }


    @GetMapping("library")
    fun library(model: ModelMap, auth: Authentication?): ModelAndView {
        model["auth"] = auth
        model["library"] = true
        return ModelAndView("library", model)
    }

    @GetMapping("settings")
    fun settings(model: ModelMap, auth: Authentication?): ModelAndView {
        model["auth"] = auth
        model["settings"] = true
        return ModelAndView("chats", model)
    }

    @GetMapping("admin")
    fun admin(model: ModelMap, auth: Authentication?): ModelAndView {
        model["auth"] = auth
        return ModelAndView("index", model)
    }

    @GetMapping("registration")
    fun registration(model: ModelMap, auth: Authentication?): ModelAndView {
        model["auth"] = auth
        return ModelAndView("registration", model)
    }

    @PostMapping("registration-data")

    fun registrationData(user: UserRegistration?, model: ModelMap, auth: Authentication?): ModelAndView {
        model["auth"] = auth
        println("registration $user")
        if (user == null || user.login.length < 3 || user.pass.length < 4 || user.name.length < 3 ||
            !userPassRepository.findByLogin(user.login).isEmpty()
        ) {
            model["error"] = true
            return ModelAndView("registration", model)
        }
        val userInfo = UserInfo(user.email, user.name)
        val userPass = UserPass(user.login, user.pass, userInfo)
        userInfoRepository.save(userInfo)
        userPassRepository.save(userPass)

        return ModelAndView("redirect:/login")
    }
}

