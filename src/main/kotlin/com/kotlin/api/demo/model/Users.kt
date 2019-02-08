package com.kotlin.api.demo.model

import java.sql.Timestamp
import java.util.Date
import javax.persistence.*

@Entity(name = "users")
data class Users(
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@get: Column(unique = true, nullable = false, precision = 10)
	val id: Int = 0,

	@get: Column(nullable = false, length = 255)
	val name: String,

	@get: Column(nullable = false, length = 255)
	val email: String,

	@get: Column(name = "phone_no", nullable = false, length = 60)
	val phoneNo: String,

	@get: Column(nullable = false, length = 60)
	val password: String,

	@get: Column(name = "is_admin", nullable = false)
	val isAdmin: Boolean = false,

	@get: Column(name = "is_super_admin", nullable = false)
	val isSuperAdmin: Boolean = false,

	@get: Column(name = "created_at")
	val createdAt: Timestamp = Timestamp(Date().getTime()),

	@get: Column(name = "updated_at")
	val updatedAt: Timestamp? = null
) {
	public override fun toString(): String {
		val sb = StringBuffer("[Users |")
		sb.append(" id=").append(id)
		sb.append("]")
		return sb.toString()
	}
}