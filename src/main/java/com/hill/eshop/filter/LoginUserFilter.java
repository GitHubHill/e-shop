package com.hill.eshop.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hill.eshop.constant.IGConstant;
import com.hill.eshop.constant.PropName;
import com.hill.eshop.constant.ResponseCode;
import com.hill.eshop.model.util.TokenBody;
import com.hill.eshop.model.util.TokenInfo;
import com.hill.eshop.redis.dao.IRedisDao;
import com.hill.eshop.redis.dao.impl.RedisDao;
import com.hill.eshop.response.BaseResponse;
import com.hill.eshop.util.JWTUtil;
import com.hill.eshop.util.PropUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.time.Instant;
import java.util.Base64;


@Provider

@LoginUser
public class LoginUserFilter implements ContainerRequestFilter, IGConstant, ResponseCode {

    @Context
    private HttpServletRequest request;
    @Context
    private ResourceInfo resourceInfo;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private IRedisDao redisDao;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private Base64.Decoder decoder;
    @Autowired
    private PropUtils propUtils;

    @Override
    public void filter(ContainerRequestContext context) {

        String token = context.getHeaderString("x-access-token");
        if (StringUtils.isNotEmpty(token)) {
            if (token.contains(DOT) && token.split(REGEX_DOT).length == 3) {
                TokenBody tokenBody = null;
                try {
                    String encodeBodyString = token.split(REGEX_DOT)[1];
                    String decodeBodyString = new String(decoder.decode(encodeBodyString), "UTF-8");
                    tokenBody = mapper.readValue(decodeBodyString, TokenBody.class);
                    TokenInfo tokenInfo = new TokenInfo(tokenBody);
                    context.setProperty(TOKEN_INFO, tokenInfo);

                    // TODO API權限驗證, 若無法在此完成判斷功能 改為使用1.aop or intercepter 2.每支resource api 增加下方程式
                    // resourceUtil.checkPermission(tokenInfo, 10101);
                } catch (ExpiredJwtException e) {
                    String signature = token.split(REGEX_DOT)[2];
                    if (redisDao.get(signature, RedisDao.TOKEN_DB).isPresent() && tokenBody != null) {
                        refreshToken(signature, tokenBody, context);
                    } else {
                        context.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(new BaseResponse(UNAUTHORIZED, "token is expired")).build());
                    }
                } catch (SignatureException e) {
                    context.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(new BaseResponse(UNAUTHORIZED, "unexpected signature")).build());
                } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | IOException e) {
                    context.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(new BaseResponse(UNAUTHORIZED, e.getMessage())).build());
                }
            } else {
                context.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(new BaseResponse(UNAUTHORIZED, "token format error")).build());
            }
        } else {
            context.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(new BaseResponse(UNAUTHORIZED, "token not found")).build());
        }

    }

    private void refreshToken(String oldSignature, TokenBody tokenBody, ContainerRequestContext context) {
        tokenBody.setExp(Instant.now().getEpochSecond() + propUtils.getIntProp(PropName.application, "renewToken"));
        String newToken = jwtUtil.generateToken(tokenBody);
        String newSignature = newToken.split(REGEX_DOT)[2];
        redisDao.get(oldSignature, RedisDao.TOKEN_DB).ifPresent(value -> redisDao.set(newSignature, value, RedisDao.TOKEN_DB, propUtils.getIntProp(PropName.application, "tokenExpire")));
        redisDao.remove(oldSignature, RedisDao.TOKEN_DB);
        context.setProperty(TOKEN_INFO, new TokenInfo(tokenBody, newToken));
    }

}
