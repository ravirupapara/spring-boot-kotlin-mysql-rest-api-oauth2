package com.kotlin.api.demo.spring.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler
import org.springframework.security.oauth2.provider.token.TokenStore
import javax.sql.DataSource

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfiguration(
		private val tokenStore: TokenStore,
		private val userApprovalHandler: UserApprovalHandler,
		private val authenticationManager: AuthenticationManager,
		private val customUserDetailsService: CustomUserDetailsService,
		private val passwordEncoder: PasswordEncoder,
		private val dataSource: DataSource
) : AuthorizationServerConfigurerAdapter() {

	@Throws(Exception::class)
	override fun configure(clients: ClientDetailsServiceConfigurer) {
//		clients.inMemory().withClient("clientapp")
//				.authorizedGrantTypes("password")
//				.scopes("read", "write", "trust")
//				.secret(passwordEncoder.encode("password")).autoApprove(true)
		clients.jdbc(dataSource)
	}

	@Throws(Exception::class)
	override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
		endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
				.authenticationManager(authenticationManager).userDetailsService(customUserDetailsService)
	}
	
}