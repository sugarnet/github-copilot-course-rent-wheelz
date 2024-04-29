package com.dss.copilot.rent.config;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

/**
 * TokenJwtToken class.
 * add public constant: SECRET
 * add private constructor.
 */
public class TokenJwtConfig {

    private TokenJwtConfig() {
    }
    /**
     * SECRET constant.
     */
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    /**
     * EXPIRATION_TIME constant.
     */
    public static final long EXPIRATION_TIME = 3600000L;
}
