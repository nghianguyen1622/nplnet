package com.npl.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
	
	@Bean
	AuthenticationSuccessHandler successHandler() {
		return new CustomLoginSuccessHandler("/");
	}

	@Bean
	AuthenticationFailureHandler failHandler() {
		return new CustomLoginFailHandler();
	}

	@Bean
	UserDetailsService userDetailsService() {
		return new NplUserDetailsService();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setHideUserNotFoundExceptions(false);

		return authProvider;
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	
	public SimpleUrlAuthenticationFailureHandler failureHandler() {
		return new SimpleUrlAuthenticationFailureHandler("/login?error=true");
	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests()
                .antMatchers("/css/**",
                        "/fonts/**",
                        "/images/**",
                        "/js/**",
                        "/webjars/**").permitAll()
//                .antMatchers("/**").hasAuthority("Admin")
                .antMatchers("/1010/**", "/6010/**", "/6030/**").hasAuthority("Admin")

                .antMatchers("/1020/**", "/1030/**", "/2010/**", "/2020/**", "/2030/**", "/2040/**",
                		"/3010/**", "/3020/**", "/3030/**", "/3040/**", "/4010/**", "/4020/**", "/4030/**", "/4040/**"
                		)
                .hasAnyAuthority("Admin", "Member" , "Review")

                .anyRequest().authenticated()
                .and()
                .formLogin(login -> login
                        .loginPage("/login")
                        .successHandler(successHandler())
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error=true")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll()).logout(logout -> logout.permitAll())
                .rememberMe(me -> me
                		.tokenRepository(this.persistentTokenRepository()) //
                		.tokenValiditySeconds(1 * 24 * 60 * 60)); // 24h
			;
			
		return http.build();
	}
    
    @Bean
	PersistentTokenRepository persistentTokenRepository() {
	    InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
	    return memory;
	}

}
