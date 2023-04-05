package hh.sof3as3.WineList;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import hh.sof3as3.WineList.web.UserDetailServiceImpl;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
	
	@Autowired
	UserDetailServiceImpl userDetailsService;
	
	//"/winelist", "/foodlist", "/api**", "/wine**"
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests()
			.requestMatchers("/css/**").permitAll()
			.requestMatchers("/winelist", "/foodlist").permitAll()
			.requestMatchers(toH2Console()).permitAll()
			.anyRequest().authenticated()
			.and()
		.csrf().ignoringRequestMatchers(toH2Console())
		.and()
		.headers().frameOptions().disable()
		.and()
		.formLogin()
			.defaultSuccessUrl("/winelist", true)
			.permitAll()
			.and()
		.logout()
			.permitAll()
			.and()
		.httpBasic();
		return http.build();

	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
