package com.avizway.Springdemo.controller;

import com.avizway.Springdemo.dto.ProfileUpdateRequest;
import com.avizway.Springdemo.model.User;
import com.avizway.Springdemo.model.UserActivity;
import com.avizway.Springdemo.service.UserActivityService;
import com.avizway.Springdemo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
@Slf4j
public class ProfileController {

    private final UserService userService;
    private final UserActivityService activityService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String showProfile(Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        List<UserActivity> recentActivities = activityService.getRecentActivities(user.getId());
        
        model.addAttribute("user", user);
        model.addAttribute("recentActivities", recentActivities);
        model.addAttribute("profileUpdate", new ProfileUpdateRequest());
        
        return "profile";
    }

    @PostMapping("/update")
    public String updateProfile(@Valid @ModelAttribute("profileUpdate") ProfileUpdateRequest request,
                               BindingResult result,
                               Authentication authentication,
                               HttpServletRequest httpRequest,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        
        User user = userService.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            model.addAttribute("recentActivities", activityService.getRecentActivities(user.getId()));
            return "profile";
        }

        // Check if email is being changed and if it's already taken
        if (!user.getEmail().equals(request.getEmail()) && 
            userService.existsByEmail(request.getEmail())) {
            model.addAttribute("error", "Email already in use");
            model.addAttribute("user", user);
            model.addAttribute("recentActivities", activityService.getRecentActivities(user.getId()));
            return "profile";
        }

        // Update basic info
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());

        // Update password if provided
        if (request.getNewPassword() != null && !request.getNewPassword().isEmpty()) {
            if (!request.getNewPassword().equals(request.getConfirmPassword())) {
                model.addAttribute("error", "Passwords do not match");
                model.addAttribute("user", user);
                model.addAttribute("recentActivities", activityService.getRecentActivities(user.getId()));
                return "profile";
            }
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            activityService.logActivity(user, "PASSWORD_CHANGED", "User changed password", httpRequest);
        }

        userService.updateUser(user);
        activityService.logActivity(user, "PROFILE_UPDATED", "User updated profile information", httpRequest);

        redirectAttributes.addFlashAttribute("success", "Profile updated successfully!");
        return "redirect:/profile";
    }
}
