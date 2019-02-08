package com.kotlin.api.demo.spring.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.approval.ApprovalStore
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
		private val clientDetailsService: ClientDetailsService,
		private val userDetailsService: CustomUserDetailsService,
		private val dataSource: DataSource
) : WebSecurityConfigurerAdapter() {
	@Bean
	fun passwordEncoder(): PasswordEncoder {
		//return BCryptPasswordEncoder()
		return NoOpPasswordEncoder.getInstance()
	}

	@Throws(Exception::class)
	override fun configure(auth: AuthenticationManagerBuilder) {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
	}

	@Throws(Exception::class)
	override fun configure(http: HttpSecurity) {
//    http.cors().and().authorizeRequests().anyRequest().authenticated()
		http.csrf().disable().anonymous().disable().authorizeRequests().antMatchers("/oauth/token", "/token/revoke/**", "/api/**")
				.permitAll().anyRequest().authenticated();
	}

	@Bean
	@Throws(Exception::class)
	override fun authenticationManagerBean(): AuthenticationManager {
		return super.authenticationManagerBean()
	}

	@Bean
	fun tokenStore(): TokenStore {
		return JdbcTokenStore(dataSource)
	}

	@Bean
	@Autowired
	fun userApprovalHandler(tokenStore: TokenStore): TokenStoreUserApprovalHandler {
		val handler = TokenStoreUserApprovalHandler()
		handler.setTokenStore(tokenStore)
		handler.setRequestFactory(DefaultOAuth2RequestFactory(clientDetailsService))
		handler.setClientDetailsService(clientDetailsService)
		return handler
	}

	@Bean
	@Autowired
	@Throws(Exception::class)
	fun approvalStore(tokenStore: TokenStore): ApprovalStore {
		val store = TokenApprovalStore()
		store.setTokenStore(tokenStore)
		return store
	}

	@Bean
	fun tokenServices(tokenStore: TokenStore): DefaultTokenServices {
		val tokenServices = DefaultTokenServices()
		tokenServices.setTokenStore(tokenStore)
		return tokenServices
	}
}