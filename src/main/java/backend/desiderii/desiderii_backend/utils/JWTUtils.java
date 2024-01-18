package backend.desiderii.desiderii_backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class JWTUtils {
    private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);

    // Signature
    private static final String signature = "desiderii_xJFYtyX+#8";

    // Access token time to live
    // 2 hours ( 60 * 2 * 1000 ms)
    private static final Long atllt = 60L * 2L * 1000L;

    // Refresh token time to live
    // 1 day (60 * 60 * 24 * 1000 ms)
    private static final Long rtttl = 60L * 60L * 24L * 1000L;

    public static String createAccessToken(Long uid) {
        String token = "";

        try{
            token = JWT.create()
                    .withClaim("uid", uid)  // Payload
                    .withExpiresAt(new Date(System.currentTimeMillis() + atllt))    // Expire time
                    .sign(Algorithm.HMAC256(signature));
        }catch (JWTCreationException e){
            System.out.println("createAccessToken: Create failed\n");
        }

        return token;
    }

    public static String createRefreshToken(Long uid) {
        String token = "";

        try{
            token = JWT.create()
                    .withClaim("uid", uid)  // Payload
                    .withExpiresAt(new Date(System.currentTimeMillis() + rtttl))    // Expire time
                    .sign(Algorithm.HMAC256(signature));
        }catch (JWTCreationException e){
            System.out.println("createRefreshToken: Create failed\n");
        }

        return token;
    }

    public static DecodedJWT parseToken(String token) {
        DecodedJWT verify = null;
        if(null == token){
            return null;
        }

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(signature)).build();

        try{
            verify = jwtVerifier.verify(token);
            return verify;
        }catch (SignatureVerificationException e){
            logger.warn("parseAccessToken: Invalid signature\n");
        }catch (TokenExpiredException e){
            logger.warn("parseAccessToken: Token expired\n");
        }catch (AlgorithmMismatchException e){
            logger.warn("parseAccessToken: Wrong algorithm\n");
        }catch (Exception e){
            logger.warn("parseAccessToken: Invalid token\n");
        }

        return null;
    }
}
