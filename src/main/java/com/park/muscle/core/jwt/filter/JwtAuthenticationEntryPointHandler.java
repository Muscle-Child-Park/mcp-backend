package com.park.muscle.core.jwt.filter;

import static com.park.muscle.global.exception.ErrorCode.RESOURCE_UNAUTHORIZED;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.park.muscle.global.exception.ErrorResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        objectMapper.writeValue(response.getWriter(), ErrorResponse.of(RESOURCE_UNAUTHORIZED));
    }
}
