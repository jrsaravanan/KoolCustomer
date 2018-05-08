package com.nathan.customer.auth.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuthConfig extends AuthorizationServerConfigurerAdapter {

	private final AuthenticationManager authenticationManager;
	
	@Autowired
	public OAuthConfig(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(this.authenticationManager);
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients
			.inMemory()
			.withClient("html5")
			.secret("password")
			.authorizedGrantTypes("password")
			.scopes("openid");
	}
}
