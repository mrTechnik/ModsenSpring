package library.app.jwtutilities;

import java.util.Date;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import library.app.domain.Usr;

@Component
public class JwtUtil {

    private static final String SECRET = "secretkey";
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days
    private static String TOKEN_PREFIX = "Bearer";

    public static String generateToken(Usr usr) {
        return TOKEN_PREFIX + " " + Jwts.builder()
            .setSubject(usr.getLogin())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact();
    }

    public static Boolean isTokenValid(String bearToken){
        try{
            if (bearToken != null && bearToken.startsWith(TOKEN_PREFIX)){
                bearToken = bearToken.substring(TOKEN_PREFIX.length());
                Claims parsed_token = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(bearToken).getBody();
                String login = parsed_token.getSubject();
                Date expiration = parsed_token.getExpiration();
                if (login != null && expiration.compareTo(new Date()) >= 0){
                    return true;
                }
            }
            return false;
        } catch (Exception ex){
            return false;
        }
    }
}