package backend.desiderii.desiderii_backend.config;

import backend.desiderii.desiderii_backend.security.AuthFailedProcess;
import backend.desiderii.desiderii_backend.security.SecurityDsl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .logout().disable();

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                .anyRequest().authenticated()
        );

        http.exceptionHandling(exception ->
                exception.authenticationEntryPoint(new AuthFailedProcess())
                        .accessDeniedPage("/"));

        http.with(SecurityDsl.securityDsl(), dsl -> dsl
                .flag(true));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer(){
        return web -> web.ignoring().requestMatchers("/user/login").requestMatchers("/user/register");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Dev env user
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("123456")
                .roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }
}
