package com.shubham.todoapp.Configuration;

import com.shubham.todoapp.Service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

      @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
          http
                  .csrf(csrf -> csrf.disable())
                  .authorizeHttpRequests(auth -> auth
                          .requestMatchers( "/public/**","/HealthCheck").permitAll()
                          .anyRequest().authenticated()
                  )
                  .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                  .formLogin(form -> form.disable())
                  .httpBasic(Customizer.withDefaults());

          return http.build();

      }

   @Bean
   public AuthenticationManager authenticationManager(HttpSecurity http) throws  Exception{
       AuthenticationManagerBuilder builder =
               http.getSharedObject(AuthenticationManagerBuilder.class);

       builder
               .userDetailsService(userDetailsService)
               .passwordEncoder(passwordEncoder());
       return builder.build();

   }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
