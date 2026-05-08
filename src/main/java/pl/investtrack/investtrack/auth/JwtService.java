package pl.investtrack.investtrack.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Log4j2
@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String signingKey ;
    public String generateToken(String email) {
        log.info("Generating token for user " + email);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000))
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(signingKey.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isTokenValid(String token) {
        try{
             Jwts.parserBuilder().setSigningKey(signingKey.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token);
             return true;
        }catch(Exception e){
            log.info("Invalid token",e.getMessage());
            return false;
        }
    }
}
