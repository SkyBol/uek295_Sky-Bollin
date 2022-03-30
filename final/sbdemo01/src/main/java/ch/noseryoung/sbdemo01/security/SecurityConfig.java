package ch.noseryoung.sbdemo01.security;

import ch.noseryoung.sbdemo01.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity @RequiredArgsConstructor @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String PATH = "/api/**";

    private final UserService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/perform_login").permitAll()
                .antMatchers("/**").hasAnyAuthority("ACCESS")
                .antMatchers(HttpMethod.POST, PATH).hasAnyAuthority("POST")
                .antMatchers(HttpMethod.PUT, PATH).hasAnyAuthority("UPDATE")
                .antMatchers(HttpMethod.DELETE, PATH).hasAnyAuthority("DELETE")
                .and()
                .formLogin()
                .loginProcessingUrl("/perform_login")
                .failureUrl("/login.html?error=true");
    }
}
