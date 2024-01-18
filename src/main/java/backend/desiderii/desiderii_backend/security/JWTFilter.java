package backend.desiderii.desiderii_backend.security;

import backend.desiderii.desiderii_backend.utils.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String accessToken = request.getHeader("accessToken");
            String refreshToken = request.getHeader("refreshToken");

            if(null != JWTUtils.parseToken(accessToken)){

            }
        }catch (Exception e){

        }

        filterChain.doFilter(request, response);
    }
}
