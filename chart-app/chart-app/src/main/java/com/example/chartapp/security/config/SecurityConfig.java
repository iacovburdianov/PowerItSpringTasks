package com.example.chartapp.security.config;

import com.example.chartapp.security.filter.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private JwtAuthFilter authFilter;

    @Bean
    //authentication
    public UserDetailsService userDetailsService() {
        return new UserInfoUserDetailsService();
    }


    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeRequests()
                .requestMatchers(HttpMethod.POST, "/cities").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/cities/{id}").hasAuthority("ROLE_USER")
                .requestMatchers(HttpMethod.GET, "/cities/all").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/cities/{id}").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/cities/{id}").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/cities/sorted/**").permitAll()
                .requestMatchers("/cities/pagination/**").permitAll()
                .requestMatchers("/cities/sorted-paginated/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/cities/authenticate").permitAll()

                .requestMatchers(HttpMethod.POST, "/countries").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/countries/{id}").hasAuthority("ROLE_USER")
                .requestMatchers(HttpMethod.GET, "/countries/all").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/countries/{id}").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/countries/{id}").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/countries/sorted/**").permitAll()
                .requestMatchers("/countries/pagination/**").permitAll()
                .requestMatchers("/countries/sorted-paginated/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/countries/authenticate").permitAll()

                .requestMatchers(HttpMethod.POST, "/regions").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/regions/{id}").hasAuthority("ROLE_USER")
                .requestMatchers(HttpMethod.GET, "/regions/all").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/regions/{id}").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/regions/{id}").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/regions/sorted/**").permitAll()
                .requestMatchers("/regions/pagination/**").permitAll()
                .requestMatchers("/regions/sorted-paginated/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/regions/authenticate").permitAll()


                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
