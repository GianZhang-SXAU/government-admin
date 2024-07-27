//package com.zja.sxau.govenmentadmin.utils;
//
//import com.zja.sxau.govenmentadmin.entity.Admin;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
////JWT生成和验证令牌
//@Component
//public class JwtUtil {
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private long expiration;
//
//    public String generateToken(Admin admin) {
//        return Jwts.builder()
//                .setSubject(admin.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//    }
//
//    public Claims getClaimsFromToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public boolean validateToken(String token, Admin admin) {
//        final String username = getUsernameFromToken(token);
//        return (username.equals(admin.getUsername()) && !isTokenExpired(token));
//    }
//
//    public String getUsernameFromToken(String token) {
//        Claims claims = getClaimsFromToken(token);
//        return claims.getSubject();
//    }
//
//    public boolean isTokenExpired(String token) {
//        Claims claims = getClaimsFromToken(token);
//        return claims.getExpiration().before(new Date());
//    }
//}
