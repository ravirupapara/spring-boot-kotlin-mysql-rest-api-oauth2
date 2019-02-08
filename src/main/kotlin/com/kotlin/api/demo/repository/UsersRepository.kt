package com.kotlin.api.demo.repository

import com.kotlin.api.demo.model.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : JpaRepository<Users, Long> {
	fun findUserByEmail(email:String):Users
}

