package backend.desiderii.desiderii_backend.security;

import backend.desiderii.desiderii_backend.config.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthFailedProcess implements AuthenticationEntryPoint {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        logger.info("验证失败!");
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Response<String> resp = new Response<String>(103, "未登录，请先登录", null);
        objectMapper.writeValue(response.getWriter(), resp);
    }
}
