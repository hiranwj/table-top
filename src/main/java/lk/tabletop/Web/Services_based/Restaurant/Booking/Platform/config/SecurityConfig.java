package lk.tabletop.Web.Services_based.Restaurant.Booking.Platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // disable CSRF for Postman testing
                .authorizeHttpRequests()
                .requestMatchers("/api/restaurants/**").permitAll() // allow all /api/restaurants endpoints
                .anyRequest().authenticated() // other endpoints require auth
                .and()
                .httpBasic(); // optional for testing admin endpoints

        return http.build();
    }
}
