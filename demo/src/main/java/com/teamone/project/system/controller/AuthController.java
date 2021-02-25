package com.teamone.project.system.controller;

import com.teamone.common.core.ResponseResult;
import com.teamone.project.system.domain.dto.LoginDTO;
import com.teamone.project.system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseResult login(@Valid LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @PostMapping("/refreshToken")
    public ResponseResult refreshToken(@RequestParam("refreshToken") String refreshToken) {
        return authService.refreshToken(refreshToken);
    }

}
