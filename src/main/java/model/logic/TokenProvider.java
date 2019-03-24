package model.logic;

import io.jsonwebtoken.*;

import javax.annotation.PostConstruct;
import java.sql.Time;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";

    private String secretKey;

    private long tokenValidity;

    private long tokenValidityForRememberMe;

    @PostConstruct
    public void init() {
        this.secretKey =
                "MIGkAgEBBDD2MFRv6BpJU6/zDI2yBfVbe0oeU1nFAoYMedDGtcdwHyWNJSeiYRBA\n" +
                "pVNzMxPSBLWgBwYFK4EEACKhZANiAAQBttEp/qUGnDlmL+o6KZnVs+RoBnEBEGho\n" +
                "PxSUu1Xfj77QQqfuqHOCRzWXseQA1aZB/h6VQEiFovugtG1G3HaMxxrqLLxb10g2\n" +
                "BMaRcAfZyeqc3O0Ui8XXb1esn0gOrCU=";
        this.tokenValidity = TimeUnit.HOURS.toMillis(10);
        this.tokenValidityForRememberMe =
                TimeUnit.HOURS.toMillis(24);
    }

    public String createToken(String username, Set<String> authorities, Boolean rememberMe ) {
        long now = (new Date()).getTime();
        long validity = rememberMe ? tokenValidityForRememberMe : tokenValidity;

        return Jwts.builder()
                .setSubject(username)
                .claim(AUTHORITIES_KEY, authorities.stream().collect(joining(",")))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(new Date(now + validity))
                .compact();
    }

    public JWTCredential getCredential(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        Set<String> authorities
                = Arrays.asList(claims.get(AUTHORITIES_KEY).toString().split(","))
                .stream()
                .collect(Collectors.toSet());

        return new JWTCredential(claims.getSubject(), authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            return false;
        }
    }
}
