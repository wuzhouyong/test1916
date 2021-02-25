package com.teamone.project.system.domain.vo;

import com.teamone.project.system.domain.entity.User;

import lombok.Data;

@Data
public class TokenInfoVO {
    private String token;
    private long expireTime;
    private User user;
}
