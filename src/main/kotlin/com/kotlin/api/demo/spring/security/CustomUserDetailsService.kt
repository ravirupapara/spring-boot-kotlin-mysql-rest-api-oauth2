package com.kotlin.api.demo.spring.security

import com.kotlin.api.demo.repository.UsersRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val usersRepository: UsersRepository) : UserDetailsService {

	@Throws(UsernameNotFoundException::class)
	override fun loadUserByUsername(username: String): UserDetails {
		val user = usersRepository.findUserByEmail(username)
				?: throw UsernameNotFoundException("Username $username not found!")
		return CustomUserDetails(user);
	}

}