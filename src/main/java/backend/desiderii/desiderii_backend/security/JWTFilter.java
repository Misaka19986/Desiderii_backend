package backend.desiderii.desiderii_backend.security;

import backend.desiderii.desiderii_backend.utils.JWTUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.rmi.server.ExportException;

public class JWTFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            logger.info("JWTFilter: processing");
            String accessToken = request.getHeader("accessToken");
            String refreshToken = request.getHeader("refreshToken");
            DecodedJWT decodedAccessToken = JWTUtils.parseToken(accessToken);
            DecodedJWT decodedRefreshToken = JWTUtils.parseToken(refreshToken);
            Long uid;

            // Tokens' payload get different uid or not exist
            if(decodedAccessToken.getClaim("uid").asInt()
                    != decodedRefreshToken.getClaim("uid").asInt()) {
                logger.warn("Wrong payload");
                throw new Exception();
            }else if(null == decodedAccessToken.getClaim("uid")
                    || null == decodedRefreshToken.getClaim("uid")) {
                logger.warn("Null payload");
                throw new Exception();
            }else{
                uid = decodedRefreshToken.getClaim("uid").asLong();
            }

            if(null == decodedAccessToken){
                if(null != decodedRefreshToken){
                    // Access token expired, but refresh token valid
                    // Gen a new access token
                    response.addHeader("accessToken", JWTUtils.createAccessToken(uid));
                }else{
                    // Refresh token expired
                    throw new Exception();
                }
            }
        }catch (Exception e){
            logger.warn("JWT check failed");
        }

        filterChain.doFilter(request, response);
    }
}
