package dev.hbrown.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.logging.Logger;

public class JwtUtil {

    private static Logger logger = Logger.getLogger(JwtUtil.class.getName());

    private static final String secret = System.getenv("secret");
    private static final Algorithm algorithm = Algorithm.HMAC256(secret);

    public static String generate(int id, String username, String role){

        String token = JWT.create()
                .withClaim("id",id)
                .withClaim("username",username)
                .withClaim("role",role)
                .sign(algorithm);

        logger.info("JWT created...");
        return token;
    }

    public static DecodedJWT isValidJWT(String token){
        try{
            DecodedJWT jwt = JWT.require(algorithm).build().verify(token);
            logger.info("JWT verified...");
            return jwt;
        } catch(JWTVerificationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
