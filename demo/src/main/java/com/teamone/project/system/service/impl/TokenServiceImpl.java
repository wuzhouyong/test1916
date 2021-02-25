package com.teamone.project.system.service.impl;

import com.teamone.common.exception.ServiceException;
import com.teamone.common.utils.CacheUtils;
import com.teamone.project.system.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    CacheUtils mCacheUtil;
    /**
     * access-token
     */
    @Value("${spring-boot-api.jwt.access-token}")
    private String accessToken;
    /**
     * submit-token
     */
    @Value("${spring-boot-api.jwt.submit-token}")
    private String submitToken;

    @Override
    public String createToken() {
        String uuid = UUID.randomUUID().toString();
        mCacheUtil.cache().put(uuid, uuid);
        return uuid;
    }

    @Override
    public void checkToken(HttpServletRequest request) throws ServiceException {
        String token = request.getHeader(submitToken);
        String uuid = mCacheUtil.cache().getIfPresent(token);
        if (StringUtils.isBlank(uuid)) {
            throw new ServiceException("请勿重复提交");
        }
    }
}
