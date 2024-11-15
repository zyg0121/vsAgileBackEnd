package com.bytewizard.vsagilebackend.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "MDg0MzYzMTU4NjE1Mzg1MjI3NjEyMzU0ODc3MjQ3MjE="; // 替换为您的实际密钥

    // 生成 Token
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10小时有效期
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // 解析 Token
    public static Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // 检查 Token 是否过期
    public static boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // 从 Token 中获取用户名
    public static String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }
}
