package cn.tjpuacm.pcregister.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.Map;

/**
 * @author ningxy
 * @date 2018-10-21 20:15
 */
public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * SECRET
     */
    private static String SECRET;

    /**
     * ISSUER
     */
    private static final String ISS = "ningxy";

    /**
     * 过期时间是(秒)，1个小时=3600秒
     */
    private static final long EXPIRATION = 3600L;

    /**
     * 选择了记住我之后的过期时间为7天
     */
    private static final long EXPIRATION_REMEMBER = 604800L;

    /**
     * 创建token
     *
     * @param username     用户名
     * @param isRememberMe 是否选择了'记住我'
     * @return token
     */
    public static String generateToken(String username, boolean isRememberMe) {
        final long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        final long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + expiration * 1000))
                .compact();
    }

    /**
     * 创建token
     *
     * @param claimsMap  自定义参数Map
     * @param expiration 有效时间（秒）
     * @return token
     */
    public static String generateToken(Map<String, Object> claimsMap, long expiration) {
        final long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setSubject("")
                .setClaims(claimsMap)
                .setIssuer(ISS)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + expiration * 1000))
                .compact();
    }

    /**
     * 从token中获取用户名
     *
     * @param token token
     * @return username 用户名
     */
    public static String getUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    /**
     * 从token中获取自定义参数Map
     *
     * @param token token
     * @return 自定义参数Map
     */
    public static Map<String, Object> getClaimsMap(String token) {
        return getTokenBody(token);
    }

    /**
     * 检查token是否在有效期内
     *
     * @param token token
     * @return true: 有效（未过期）; false: 失效（过期）
     */
    public static boolean isValid(String token) {
        Date d = getTokenBody(token).getExpiration();
        return getTokenBody(token).getExpiration().after(new Date());
    }

    /**
     * 解析token获取信息
     *
     * @param token token
     * @return token中信息
     */
    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        this.SECRET = secret;
    }
}
