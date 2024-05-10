package mx.metaphorce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

	private final JwtAuthConverter jwtAuthConverter = new JwtAuthConverter();

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/eureka/**").permitAll()
						.requestMatchers("/actuator/**").permitAll().anyRequest().authenticated())
				.oauth2ResourceServer(conf -> conf.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)));
		return http.build();
	}
}