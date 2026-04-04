package com.chetraseng.sunrise_task_flow_api.security;

import com.chetraseng.sunrise_task_flow_api.model.UserModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class SecurityUtils {
    @Component
    public static class securityUtils {

        public Optional<UserModel> getCurrentUser() {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            if (auth == null || !(auth.getPrincipal() instanceof UserModel)) {
                return Optional.empty();
            }

            return Optional.of((UserModel) auth.getPrincipal());
        }
    }
}
