package com.kotlin.api.demo.spring.security

import com.kotlin.api.demo.model.Users
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Collections
import com.helpr.corporate.api.model.Users

class CustomUserDetails(users: Users) : UserDetails {
	private val username: String
	private val password: String
	private val authorities: Collection<out GrantedAuthority>

	companion object {
		private val serialVersionUID = 1L
	}

	init {
		this.username = users.email
		this.password = users.password
		this.authorities = translate(users)
	}

	private fun translate(users: Users): Collection<out GrantedAuthority> {
		val authorities = ArrayList<GrantedAuthority>()
		if (users.isAdmin || users.isSuperAdmin)
			authorities.add(SimpleGrantedAuthority("ROLE_ADMIN"))
		return authorities
	}

	override fun getAuthorities(): Collection<out GrantedAuthority> {
		return authorities
	}

	override fun getPassword(): String {
		return password
	}

	override fun getUsername(): String {
		return username
	}

	override fun isAccountNonExpired(): Boolean {
		return true
	}

	override fun isAccountNonLocked(): Boolean {
		return true
	}

	override fun isCredentialsNonExpired(): Boolean {
		return true
	}

	override fun isEnabled(): Boolean {
		return true
	}
}