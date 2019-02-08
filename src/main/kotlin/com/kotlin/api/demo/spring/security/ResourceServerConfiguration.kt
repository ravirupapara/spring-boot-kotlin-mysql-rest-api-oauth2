package com.kotlin.api.demo.spring.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler

@Configuration
@EnableResourceServer
class ResourceServerConfiguration : ResourceServerConfigurerAdapter() {
	override fun configure(resources: ResourceServerSecurityConfigurer) {
		resources.resourceId(RESOURCE_ID).stateless(false)
	}

	@Throws(Exception::class)
	override fun configure(http: HttpSecurity) {
		http.anonymous().disable().authorizeRequests()
				.antMatchers("/token/revoke/**").permitAll().antMatchers("/api/**")
				.access("hasRole('ADMIN')").and().exceptionHandling()
				.accessDeniedHandler(OAuth2AccessDeniedHandler())
	}

	companion object {
		private val RESOURCE_ID = "rest_api"
	}
}