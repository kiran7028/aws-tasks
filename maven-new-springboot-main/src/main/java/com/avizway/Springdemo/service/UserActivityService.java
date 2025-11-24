package com.avizway.Springdemo.service;

import com.avizway.Springdemo.model.User;
import com.avizway.Springdemo.model.UserActivity;
import com.avizway.Springdemo.repository.UserActivityRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserActivityService {

    private final UserActivityRepository activityRepository;

    @Transactional
    public void logActivity(User user, String action, String description, HttpServletRequest request) {
        try {
            UserActivity activity = new UserActivity();
            activity.setUser(user);
            activity.setAction(action);
            activity.setDescription(description);
            activity.setIpAddress(getClientIp(request));
            activity.setUserAgent(request.getHeader("User-Agent"));
            activityRepository.save(activity);
            log.debug("Activity logged for user {}: {}", user.getUsername(), action);
        } catch (Exception e) {
            log.error("Failed to log activity for user {}", user.getUsername(), e);
        }
    }

    public List<UserActivity> getRecentActivities(Long userId) {
        return activityRepository.findTop10ByUserIdOrderByCreatedAtDesc(userId);
    }

    public Page<UserActivity> getUserActivities(Long userId, int page, int size) {
        return activityRepository.findByUserIdOrderByCreatedAtDesc(userId, PageRequest.of(page, size));
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
