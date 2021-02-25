package com.teamone.common.utils;

import com.teamone.common.exception.TokenException;
import com.teamone.project.system.domain.vo.TokenInfoVO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import lombok.extern.slf4j.Slf4j;

/**
 * jwt 工具类
 *
 * @author lw
 * @date 2019/6/6 9:13 下午
 */
@Component
@Slf4j
public class JWTUtils {
    /**
     * 加密SECRET
     */
    @Value("${spring-boot-api.jwt.secret}")
    private String secret;
    /**
     * 过期时间
     */
    @Value("${spring-boot-api.jwt.expire-day}")
    private Long expireDay;

    /**
     * 创建token
     *
     * @param map
     * @return
     */
    public TokenInfoVO createJWT(Map<String, Object> map) {
        Map<String, Object> header = new HashMap<>(2);
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        Instant now = Instant.now();
        Date expireTime = Date.from(now.plus(expireDay, ChronoUnit.DAYS));
        Date issuedAt = Date.from(now);
        String jwt = Jwts.builder()
                .setClaims(map)
                .setHeader(header)
                .setExpiration(expireTime)
                .setIssuedAt(issuedAt)
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(secret))
                .compact();

        String token = Base64.getEncoder().encodeToString(jwt.getBytes());

        TokenInfoVO tokenInfoVO = new TokenInfoVO();
        tokenInfoVO.setToken(token);
        tokenInfoVO.setExpireTime(expireTime.getTime());
        return tokenInfoVO;
    }


    /**
     * 校验token
     *
     * @param jwtToken
     * @return
     */
    public boolean verify(String jwtToken) {
        try {
            byte[] b = Base64.getDecoder().decode(jwtToken);
            String base64jwt = new String(b);
            Jwts.parser().setSigningKey(TextCodec.BASE64.encode(secret)).parse(base64jwt);
            return true;
        } catch (Exception e) {
            throw new TokenException();
        }
    }

    /**
     * Token解密
     *
     * @param jwtToken
     * @return
     */
    public Map<String, Object> getJWTData(String jwtToken) {
        try {
            byte[] b = Base64.getDecoder().decode(jwtToken);
            String base64jwt = new String(b);
            Jwt jwt = Jwts.parser().setSigningKey(TextCodec.BASE64.encode(secret)).parse(base64jwt);
            return (Map<String, Object>) jwt.getBody();
        } catch (Exception e) {
            throw new TokenException();
        }
    }

    /**
     * 刷新token
     *
     * @param token
     * @return
     */
    public TokenInfoVO refreshToken(String token) {
        return verify(token) ? createJWT(getJWTData(token)) : null;
    }
}
