package pl.tdelektro.workshop.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import pl.tdelektro.workshop.repository.UserRepository;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    UserRepository userRepository;

    //Access configuration
    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers
                        .frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/h2/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/add")
                        .permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/user/**", "/task/**", "/car/**")
                        .hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user/email/**")
                        .hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET,"user/**", "/task/**", "/car/**")
                        .hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/user/**", "/task/**", "/car/**")
                        .hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/user/**", "/task/**", "/car/**")
                        .hasAuthority("ADMIN")
                )
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

