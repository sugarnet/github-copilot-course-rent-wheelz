package com.dss.copilot.rent.model.service.impl;

import com.dss.copilot.rent.config.TokenJwtConfig;
import com.dss.copilot.rent.model.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

/**
 * TokenServiceImpl class.
 * add @Service annotation.
 * implements TokenService interface.
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String getSubjectFromToken(String token) {
        token = token.replace("Bearer ", "");
        Claims claims = Jwts.parser().verifyWith(TokenJwtConfig.SECRET_KEY).build().parseSignedClaims(token)
                .getPayload();
        return (String) claims.get("userEmail");
    }
}
