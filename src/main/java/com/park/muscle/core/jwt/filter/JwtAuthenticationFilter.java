package com.park.muscle.core.jwt.filter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.park.muscle.core.jwt.application.JwtTokenProvider;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //사용자의 요청 헤더에서 Authorization 값을 가져온다.
        String header = request.getHeader(AUTHORIZATION);

        if(validateHeader(header)) {
            // 헤더에서 JWT를 받아온다.
            String accessToken = header.substring(7);

            log.info("accessToken: {} 으로 Authentication 객체를 찾습니다.", accessToken);
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

            //SecurityContext에 Authentication 객체를 저장한다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("SecurityContextHolder 에 Authentication 객체를 저장했습니다. 인증 완료 {}",
                    authentication.getName());
        }

        filterChain.doFilter(request, response);
    }

    /**
     * header가 null이거나, Bearer로 시작하는지 검증한다.
     */
    private boolean validateHeader(String header) {
        return header != null && header.startsWith("Bearer ");
    }
}
