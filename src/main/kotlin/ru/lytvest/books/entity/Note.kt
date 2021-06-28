package ru.lytvest.books.entity

import org.springframework.web.bind.annotation.Mapping
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "notes")

class Note(

    @Column(length = 100)
    val title:String = "",
    @Column(length = 500)
    val text:String = "",

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    var author: UserInfo = UserInfo(),

    @OneToMany(fetch = FetchType.EAGER)
    val tags: Set<Tag> = setOf(),

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "full_text_id")
    val full: NoteFullText = NoteFullText(),

    val time: Date = Date(),
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
)
