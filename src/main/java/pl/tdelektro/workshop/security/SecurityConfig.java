package pl.tdelektro.workshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private BCryptPasswordEncoder passwordEncoder;

        //Access configuration
        @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

            http
                    .csrf(csrf-> csrf.disable())
                    .authorizeRequests()
                    .requestMatchers(HttpMethod.POST,"/user/add").permitAll()
                    .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET).hasRole("USER")
                    .requestMatchers(HttpMethod.GET).hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .httpBasic();

            return http.build();
        }

        @Bean
    public UserDetailsService users(){
            UserDetails admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("password"))
                    .roles("ADMIN")
                    .build();

            UserDetails user = User.builder()
                    .username("user")
                    .password(passwordEncoder.encode("password"))
                    .roles("USER")
                    .build();

            return new InMemoryUserDetailsManager(admin, user);
        }
}
