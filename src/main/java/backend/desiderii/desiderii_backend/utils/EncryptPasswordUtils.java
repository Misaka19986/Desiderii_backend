package backend.desiderii.desiderii_backend.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptPasswordUtils {
    public static String encryptPassword(String rawPassword){
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

    public static boolean matchesPassword(String rawPassword, String encodedPassword){
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }
}
