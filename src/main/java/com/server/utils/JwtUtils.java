package com.server.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    public static final String TOKEN_HEADER = "Authorization";//token的key 也是名 不要写成token这样，要按照规范来
    public static final String TOKEN_PREFIX = "Bearer ";//token值的前缀，这是一种规范 ok
    private static final String SECRET = "hyqjwt";//你自己定的字符串 别让别人知道，加密时候用   盐
    public static final String FUNCTS = "FUNCTS";//获取用户的功能使用的key
    public static final String USERIDINFO = "USER_id";//获取用户使用的key
    public static final String UNINEID = "UNINEID";//获取用户使用的key
    private static final long EXPIRATION = 1800L;// token的生命周期30分

    public static String createJWT(String user_id) {
        String unineId = OtherUtils.getUUID();
        DefaultClaims claims = new DefaultClaims();
        claims.put(USERIDINFO, user_id);
        claims.put(UNINEID, unineId);
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(unineId)
                .setSubject("JWT")   // 主题
                .setIssuer("user")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey); // 签名算法以及密匙
        return builder.compact();
    }

    public static String createJWT(Map<String, Object> map) {
        String unineId = OtherUtils.getUUID();
        DefaultClaims claims = new DefaultClaims();
        claims.putAll(map);
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(unineId)
                .setSubject("OTHER")   // 主题
                .setIssuer("user")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey); // 签名算法以及密匙
        return builder.compact();
    }

    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(SECRET);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


    public static Claims parseJWT(String token) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public static String getUserId(String token) throws Exception {
        return (String) parseJWT(token).get(USERIDINFO);
    }


    public static void main(String[] args) throws Exception {
        String jwt = createJWT("1");
        String userId = getUserId(jwt);
        System.out.println(userId);
    }
}