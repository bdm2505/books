package ru.lytvest.books.service


import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.lytvest.books.repository.UserPassRepository



@Service
class UserDetailsServiceImpl(
    val userPassRepository: UserPassRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null)
            throw UsernameNotFoundException("username is null")

        return userPassRepository.findByLogin(username)
            .orElseThrow { throw UsernameNotFoundException("user not found") }
    }

}

