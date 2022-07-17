package com.epam.selectioncommitteespring.Config;

import com.epam.selectioncommitteespring.Model.Entity.Role;
import com.epam.selectioncommitteespring.Model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

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


    //   @Override

//    protected UserDetailsService userDetailsService() {
//        //UserDetails
//        //Обьект спринга содержащий минимальную ифну о пользователее
//        //username и password
//        UserDetails user = User
//                .builder()
//                .username("alilax")
//                .password("{bcrypt}$2a$12$krnxGAIGUbgmj5hIMniwZezhBMJ10M6S.26TjI89WzCon29gFJaoy")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User
//                .builder()
//                .username("admin")
//                .password("{bcrypt}$2a$12$krnxGAIGUbgmj5hIMniwZezhBMJ10M6S.26TjI89WzCon29gFJaoy")
//                .roles("ADMIN", "USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }
}
