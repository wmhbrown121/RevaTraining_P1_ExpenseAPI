package dev.hbrown.utiltests;

import com.auth0.jwt.interfaces.DecodedJWT;
import dev.hbrown.utils.JwtUtil;
import org.junit.jupiter.api.Test;

public class JwtTest {

    @Test
    void create_jwt(){
        String jwt = JwtUtil.generate(1,"wmhbrown","manager");
        System.out.println(jwt);
    }

    @Test
    void decode_jwt(){
        DecodedJWT jwt = JwtUtil.isValidJWT("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiZW1wbG95ZWUiLCJ1c2VySWQiOjEsInVzZXJuYW1lIjoid21oYnJvd24ifQ.Ol2Aze1LrNjwinmguzW2_orsXyludce2IqOPbJAj4wg");
        String role = jwt.getClaim("role").asString();
        System.out.println(role);
    }

    @Test
    void tampered_jwt(){
        DecodedJWT jwt = JwtUtil.isValidJWT("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoibWFuYWdlciIsInVzZXJJZCI6MSwidXNlcm5hbWUiOiJ3bWhicm93biJ9.upNZFdJ7SaNu9KNnU48obTTpWnk8cnT6oHtjUFRtDY4");
        String role = jwt.getClaim("role").asString();
        System.out.println(role);
    }

}
