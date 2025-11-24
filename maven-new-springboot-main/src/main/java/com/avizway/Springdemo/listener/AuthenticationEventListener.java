package com.avizway.Springdemo.listener;

import com.avizway.Springdemo.model.User;
import com.avizway.Springdemo.service.UserActivityService;
import com.avizway.Springdemo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationEventListener {

    private final UserActivityService activityService;
    private final UserService userService;

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        log.info("User logged in: {}", username);
        
        userService.findByUsername(username).ifPresent(user -> {
            HttpServletRequest request = getCurrentRequest();
            if (request != null) {
                activityService.logActivity(user, "LOGIN", "User logged in successfully", request);
            }
        });
    }

    private HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes != null ? attributes.getRequest() : null;
    }
}
