package ru.lytvest.books.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "notes")

class Note(

    @Column(length = 100)
    val title:String = "",
    @Column(length = 3000)
    val text:String = "",

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    var author: UserInfo = UserInfo(),
    //val tags: List<String> = listOf(),
    val time: Date = Date(),
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
) {
}