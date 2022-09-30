package com.carproject.application;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtToken {
    private String SECRET_KEY = "liberate-tutume-ex-inferis-ad-astra-per-aspera";
    private String audience = "WorkshopApplication";

    private Claims getClaims(String token){
        JwtParser parser = Jwts.parser().setSigningKey(SECRET_KEY);
        Jws<Claims> signatureAndClaims = parser.parseClaimsJws(token);
        Claims claims = signatureAndClaims.getBody();

        return claims;
    }

    public String getUsername(String token){
        Claims claims = getClaims(token);
        return claims.get("username", String.class);
    }

    public String generateToken(String subject, String username, String secretKey, String role,
                                String audience){
        JwtBuilder builder = Jwts.builder();
        builder = builder.setSubject(subject)
                .claim("username", username)
                .claim("role", role)
                .setIssuer("http://localhost:8080")
                .setAudience(audience)
                .signWith(SignatureAlgorithm.HS256, secretKey); //pakai algoritma hs256, kalo mo diganti juga bisa

        return builder.compact();
    }

    public Boolean validationToken(String token, UserDetails userDetails){
        Claims claims = getClaims(token); //claims ada di dlm payload. tengok di jwt.io
        //jadi decoded itu akan digenerate jdi token encoded
        //jadi waktu login, kita bawa informasi token, token itu
        String user = claims.get("username", String.class);
        String audience = claims.getAudience();

        return (user.equals(userDetails.getUsername()) && audience.equals(audience));
    }
}
