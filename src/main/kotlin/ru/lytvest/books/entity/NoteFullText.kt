package ru.lytvest.books.entity

import javax.persistence.*


@Entity
@Table(name = "notes_full_text")
data class NoteFullText(

    @Column(length = 50000)
    val fullText: String = "",

    @OneToOne(mappedBy = "full")
    var note:Note? = null,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
)
