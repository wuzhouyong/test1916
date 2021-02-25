package com.teamone.common.utils;

import com.teamone.project.system.domain.entity.User;

/**
 * @author： lw
 * @email：salecoding@gmail.com
 * @date：2020/6/11
 */
public class ContextUtils {
    private static ThreadLocal<User> userContext = new ThreadLocal<>();

    public static void setUser(User user) {
        userContext.set(user);
    }

    public static User getUser() {
        return userContext.get();
    }

    public static String getUsername() {
        return getUser() != null ? getUser().getUsername() : null;
    }
}
