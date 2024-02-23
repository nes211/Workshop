package pl.tdelektro.workshop.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    //private BCryptPasswordEncoder passwordEncoder;
    //private UserSecurityServiceImpl userSecurityService;

    //Access configuration
    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .securityMatcher("/user/**")
                .authorizeHttpRequests(authorize -> authorize
                                .anyRequest().hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST, "/user/add")
//                        .permitAll()
//                        .requestMatchers(HttpMethod.DELETE,"/user/**", "/task/**","/car/**")
//                        .hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.GET,"/user/**", "/task/**","/car/**")
//                        .hasAnyRole("USER", "ADMIN")
//                        .requestMatchers(HttpMethod.PUT,"/user/**", "/task/**","/car/**")
//                        .hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.POST,"/user/**", "/task/**","/car/**")
//                        .hasRole("ADMIN")

                ).httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

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

