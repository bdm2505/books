package ru.lytvest.books.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "userinfo")
data class UserInfo(

    @Column(length = 50)
    val email: String = "",
    @Column(length = 50)
    val name: String = "",
    @Column
    val created : Date = Date(),

    @Column(length = 50)
    val icon: String = "avatar-1.jpg",


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
)
