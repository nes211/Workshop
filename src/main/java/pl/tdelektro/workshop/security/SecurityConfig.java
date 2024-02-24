package pl.tdelektro.workshop.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {


    //Access configuration
    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/user/add")
                        .permitAll()
                        .requestMatchers("/h2/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/user/**", "/task/**", "/car/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user/**")
                        .hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/user/**", "/task/**", "/car/**")
                        .hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/user/**", "/task/**", "/car/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/user/**", "/task/**", "/car/**")
                        .hasRole("ADMIN")

                )
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        User.UserBuilder users = User.builder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());
        manager.createUser(users.username("user").password("password").roles("USER").build());

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use NoOpPasswordEncoder to disable password decoding
        return NoOpPasswordEncoder.getInstance();
    }


}

