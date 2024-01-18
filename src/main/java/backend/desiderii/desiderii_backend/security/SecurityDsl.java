package backend.desiderii.desiderii_backend.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SecurityDsl extends AbstractHttpConfigurer<SecurityDsl, HttpSecurity> {
    private boolean flag;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new JWTFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    public SecurityDsl flag(boolean value) {
        this.flag = value;
        return this;
    }

    public static SecurityDsl securityDsl() {
        return new SecurityDsl();
    }
}
