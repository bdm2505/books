package ru.lytvest.books.entity

import javax.persistence.*


@Entity
@Table(name = "tags")
data class Tag(
    @Column(length = 50)
    val name: String = "",

    @ManyToMany
    var notes: Set<Note>, 

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
)
