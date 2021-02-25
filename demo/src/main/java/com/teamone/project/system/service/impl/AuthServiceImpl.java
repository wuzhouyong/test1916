package com.teamone.project.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teamone.common.core.Constant;
import com.teamone.common.core.ResponseResult;
import com.teamone.common.core.ResultGenerator;
import com.teamone.common.exception.ServiceException;
import com.teamone.common.utils.JWTUtils;
import com.teamone.project.system.mapper.UserMapper;
import com.teamone.project.system.domain.dto.LoginDTO;
import com.teamone.project.system.domain.entity.User;
import com.teamone.project.system.domain.vo.TokenInfoVO;
import com.teamone.project.system.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl extends ServiceImpl<UserMapper, User> implements AuthService {
    @Autowired
    private JWTUtils jwtUtil;

    private static Map<String, User> userMap;

    static {
        userMap = new HashMap<>(4);
        userMap.put("admin", new User("001", "admin", "1234"));
    }

    @Override
    public ResponseResult login(LoginDTO loginDTO) {

        User user = verificationLogin(loginDTO);

        Map<String, Object> tokenData = new HashMap<>(2);
        tokenData.put(Constant.USER_ID, user.getUserId());
        tokenData.put(Constant.USERNAME, loginDTO.getUsername());
        tokenData.put(Constant.PASSWORD, loginDTO.getPassword());

        TokenInfoVO tokenInfoVO = jwtUtil.createJWT(tokenData);
        tokenInfoVO.setUser(user);
        return ResultGenerator.genSuccessResult(tokenInfoVO);
    }

    @Override
    public ResponseResult refreshToken(String token) {
        Map<String, Object> map = jwtUtil.getJWTData(token);
        String username = (String) map.get(Constant.USERNAME);
        String password = (String) map.get(Constant.PASSWORD);

        User user = verificationLogin(new LoginDTO(username, password));

        TokenInfoVO tokenInfoVO = jwtUtil.refreshToken(token);

        tokenInfoVO.setUser(user);
        return ResultGenerator.genSuccessResult(tokenInfoVO);
    }

    /**
     * 验证登录
     *
     * @param loginDTO
     * @return
     */
    private User verificationLogin(LoginDTO loginDTO) {
        User user = userMap.get(loginDTO.getUsername());
        if (user == null) {
            throw new ServiceException("用户名不存在");
        }
        user.setPassword(null);
        return user;
    }
}
