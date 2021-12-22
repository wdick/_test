package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public WebSecurityConfig() {
        super(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilter(new WebAsyncManagerIntegrationFilter());
        http.exceptionHandling();
        http.headers();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.securityContext();
        http.servletApi();
        http.anonymous();

        http
                .authorizeRequests()
                .antMatchers("/actuator/health/*").permitAll()
                .antMatchers("/actuator/**").hasRole("MONITOR")
                .antMatchers("/v1/**").permitAll()
                .anyRequest().denyAll();

        http.httpBasic(Customizer.withDefaults());
    }

}
