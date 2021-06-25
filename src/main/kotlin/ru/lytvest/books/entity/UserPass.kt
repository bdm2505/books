package ru.lytvest.books.entity

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "users_pass")

class UserPass : UserDetails {

    @Column(length = 60, unique = true)
    var login: String = ""

    @Column(length = 100)
    var pass: String = ""

    @Column(length = 14)
    var role: String = "ROLE_USER"

    @Column
    var enabled: Boolean = true

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userinfo_id")
    var userInfo: UserInfo = UserInfo()

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    constructor()

    constructor(login: String, pass: String, userInfo: UserInfo, role: String = "ROLE_USER", enabled: Boolean = true) {
        this.login = login
        this.pass = pass
        this.role = role
        this.enabled = enabled
        this.userInfo = userInfo
    }


    override fun getAuthorities(): List<SimpleGrantedAuthority> {
        return listOf(SimpleGrantedAuthority(role))
    }

    override fun getPassword(): String {
        return pass
    }

    override fun getUsername(): String {
        return login
    }

    override fun isAccountNonExpired(): Boolean {
        return enabled
    }

    override fun isAccountNonLocked(): Boolean {
        return enabled
    }

    override fun isCredentialsNonExpired(): Boolean {
        return enabled
    }

    override fun isEnabled(): Boolean {
        return enabled
    }
}