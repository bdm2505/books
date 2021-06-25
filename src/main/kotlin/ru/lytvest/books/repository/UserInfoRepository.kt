package ru.lytvest.books.repository

import org.springframework.data.repository.CrudRepository
import ru.lytvest.books.entity.UserInfo

interface UserInfoRepository: CrudRepository<UserInfo, Long> {
}