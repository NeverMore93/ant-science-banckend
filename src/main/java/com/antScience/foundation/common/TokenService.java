package com.antScience.foundation.common;

import com.antScience.foundation.common.date.DateService;
import com.antScience.foundation.exception.BusinessException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by xubt on 7/8/16.
 */
@Service
public class TokenService {

    private static final String SIGNING_KEY = "secret";
    public static final int FIFTEEN_MINUTES = 15 * 60;

    private DateService dateService = new DateService();

    public String buildToken(String userName) throws Exception {
        Date expirationTime = dateService.addSecond(new Date(), FIFTEEN_MINUTES);
        Claims claims = Jwts.claims().setSubject(userName).setExpiration(expirationTime);
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SIGNING_KEY).compact();
    }

    @ApiOperation("判断Token是否过期")
    public boolean isExpired(String token) throws Exception {
        Claims claims = Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();
        Date expirationTime = claims.getExpiration();
        return expirationTime.before(new Date());
    }

    @ApiOperation("判断用户名是否被篡改")
    public boolean isTampered(String token, String userName) throws Exception {
        Claims claims = Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();
        return !claims.getSubject().equals(userName);
    }

    private boolean isTokenEmpty(String token) {
        return token == null || "".equals(token) || "null".equals(token);
    }

    public String identify(String token, String userName) throws Exception {
        if (isTokenEmpty(token)) {
            throw new BusinessException("当前用户未认证,请先登录认证。");
        }
        if (isExpired(token)) {
            throw new BusinessException("认证已经过期,请重新认证获取token。");
        }
        if (isTampered(token, userName)) {
            throw new BusinessException("请求头部的用户名与token中的不一致,请求可能被篡改。");
        }
        return buildToken(userName);
    }
}
