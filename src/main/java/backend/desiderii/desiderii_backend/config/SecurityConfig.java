package backend.desiderii.desiderii_backend.config;

import backend.desiderii.desiderii_backend.security.SecurityDsl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/user/login").permitAll()
                .requestMatchers("/user/register").permitAll()
                .anyRequest().authenticated()
        )
                .formLogin(Customizer.withDefaults());

        http.with(SecurityDsl.securityDsl(), dsl -> dsl
                .flag(true));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Dev env user
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("123456")
                .roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }
}
