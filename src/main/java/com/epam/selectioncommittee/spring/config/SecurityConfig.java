package com.epam.selectioncommittee.spring.config;

import com.epam.selectioncommittee.spring.model.entity.Role;
import com.epam.selectioncommittee.spring.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private  UserService userService;

    @Autowired
    @Lazy
    public void setUserService(UserService userService) {

        this.userService = userService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
                .antMatchers("/admin/**")
                .hasAuthority(Role.RoleName.ADMIN.name())
                .antMatchers("/user/**")
                .hasAnyAuthority(Role.RoleName.USER.name(), Role.RoleName.ADMIN.name())
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {

        DaoAuthenticationProvider daoAuthenticationProvider =
                new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);

        return daoAuthenticationProvider;
    }
}
