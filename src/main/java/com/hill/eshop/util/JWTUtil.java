package com.hill.eshop.util;


import com.hill.eshop.constant.PropName;
import com.hill.eshop.model.util.TokenBody;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;


@Component
public class JWTUtil {

    private byte[] signKeyByte;

    @Autowired
    private PropUtils propUtils;

    @PostConstruct
    public void init() throws UnsupportedEncodingException {
        signKeyByte = "26749e477e957ca6c7dfa0f50a0b80a7".getBytes("UTF-8");
    }

    public byte[] getSignKeyByte() {
        return signKeyByte;
    }

    public String generateToken(TokenBody tokenBody) {
        JwtBuilder builder = Jwts.builder();
        try {
            for (Field field : tokenBody.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                builder.claim(field.getName(), field.get(tokenBody));
            }
            return builder.signWith(SignatureAlgorithm.HS256, signKeyByte).compact();
        } catch (IllegalAccessException e) {
            return null;
        }
    }

}
