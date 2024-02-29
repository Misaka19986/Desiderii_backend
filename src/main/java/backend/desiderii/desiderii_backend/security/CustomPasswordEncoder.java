package backend.desiderii.desiderii_backend.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import static backend.desiderii.desiderii_backend.utils.EncryptPasswordUtils.encryptPassword;
import static backend.desiderii.desiderii_backend.utils.EncryptPasswordUtils.matchesPassword;

public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return encryptPassword(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return matchesPassword(rawPassword.toString(), encodedPassword);
    }
}
