package jp.co.axa.apidemo.config;

import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configures the web security authentication.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    /**
     * Authentication for user and password credential.
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("user")
                .password("{noop}password").roles("USER");
    }

    /**
     * Configures the http security for the authorized requests.
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // permit all users in "/api/v1" url request
        // only permit the role USER in "/api/v1/employees/**" url request
        http
                .authorizeRequests().antMatchers("/api/v1").permitAll().and()
                .authorizeRequests().antMatchers("/api/v1/employees/**").hasRole("USER").and().formLogin();

        // for h2 Console configuration
        http
                .authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/console/**").permitAll();
        http
                .csrf().disable();
        http
                .headers().frameOptions().disable();
    }

}