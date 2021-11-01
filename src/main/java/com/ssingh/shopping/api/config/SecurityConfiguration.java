package com.ssingh.shopping.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class SecurityConfiguration {

    static void configureRequestAuthorization(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/api/*/ping", "/ws", "/*/api-docs").permitAll()
                .regexMatchers(".*swagger.*").permitAll()
                .antMatchers("/api/*/products/add", "/api/*/products/update", "/api/*/products/delete").hasRole("ADMIN")
                .antMatchers("/api/*/news/add", "/api/*/news/update", "/api/*/news/delete").hasRole("ADMIN")
                .antMatchers("/api/*/cart/**").hasRole("CUSTOMER")
                .antMatchers("/api/*/order/add").hasRole("CUSTOMER")
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().csrf().disable();
    }

    @EnableWebSecurity
    public static class InMemorySecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            auth.inMemoryAuthentication()
                    .withUser("admin")
                    .password(encoder.encode("admin"))
                    .roles("ADMIN")
                    .and()
                    .withUser("user")
                    .password(encoder.encode("user"))
                    .roles("USER")
                    .and()
                    .withUser("customer")
                    .password(encoder.encode("customer"))
                    .roles("USER", "CUSTOMER");

        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            configureRequestAuthorization(http);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}