package com.microservices.hrapigatewayzuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	@Autowired
	private JwtTokenStore tokenStore;

	private static final String[] PUBLIC = { "/hr-auth/oauth/token" };
	private static final String[] OPERATOR_URLS = { "/hr-worker/**" };
	private static final String[] ADMIN_URLS = { "/hr-payroll/**", "/hr-user/**" };

	/** Enable the project to read tokens JWT. */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(PUBLIC).permitAll().antMatchers(HttpMethod.GET, OPERATOR_URLS)
				.hasAnyRole("OPERATOR", "ADMIN").antMatchers(ADMIN_URLS).hasRole("ADMIN").anyRequest().authenticated();
	}

}
