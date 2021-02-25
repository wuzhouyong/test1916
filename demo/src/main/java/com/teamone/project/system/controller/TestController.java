package com.teamone.project.system.controller;

import com.teamone.common.core.ResponseResult;
import com.teamone.common.core.ResultGenerator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public ResponseResult test() {
        return ResultGenerator.genSuccessResult("I am master");
    }
}
