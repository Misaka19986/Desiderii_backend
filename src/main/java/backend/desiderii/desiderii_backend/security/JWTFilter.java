package backend.desiderii.desiderii_backend.security;

import backend.desiderii.desiderii_backend.entity.User;
import backend.desiderii.desiderii_backend.mapper.UserMapper;
import backend.desiderii.desiderii_backend.utils.JWTUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserMapper userMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("JWTFilter: processing");
        String accessToken = request.getHeader("accessToken");
        String refreshToken = request.getHeader("refreshToken");
        DecodedJWT decodedAccessToken = JWTUtils.parseToken(accessToken);
        DecodedJWT decodedRefreshToken = JWTUtils.parseToken(refreshToken);
        Long uid;

        // Tokens' payload get different uid or not exist
        if(!decodedAccessToken.getClaim("uid").asInt()
                .equals(decodedRefreshToken.getClaim("uid").asInt())) {
            logger.warn("Wrong payload");
            throw new ServletException();
        }else if(null == decodedAccessToken.getClaim("uid")
                || null == decodedRefreshToken.getClaim("uid")) {
            logger.warn("Null payload");
            throw new ServletException();
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
                throw new ServletException();
            }
        }

        User userDB = userMapper.selectById(uid);

        if(null == userDB){
            logger.warn("Wrong payload");
            throw new ServletException();
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDB.getAlias(), userDB.getPassword(), null);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
