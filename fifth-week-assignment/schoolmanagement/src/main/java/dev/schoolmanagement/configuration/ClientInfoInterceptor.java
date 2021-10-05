package dev.schoolmanagement.configuration;

import dev.schoolmanagement.entity.ClientInfo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@RequiredArgsConstructor
public class ClientInfoInterceptor implements HandlerInterceptor, WebMvcConfigurer {
    @Autowired
    private final ClientInfo clientInfo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Get address, url and session info from request object
        clientInfo.setClientIp(request.getRemoteAddr());
        clientInfo.setClientSessionId(request.getSession().getId());
        clientInfo.setClientURL(request.getRequestURI());
        return true;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this).addPathPatterns("/**");
    }
}
