package com.maroune.urlshortner.auth;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthResource {

    @Path("/token")
    @POST
    @PermitAll
    public Response getToken(AuthRequest authRequest) {
        var user = User.findByName(authRequest.username());

        if (user != null && BcryptUtil.matches(authRequest.password(), user.password)) {
            JwtClaimsBuilder claimsBuilder = Jwt.claims();
            String jwtToken = claimsBuilder.issuer("https://marouane.com/issuer")
                    .subject(user.username)
                    .issuedAt(currentTimeInSecs())
                    .expiresAt(currentTimeInSecs() + 120L)
                    .groups(user.role).sign();


            return Response.ok(jwtToken).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();

    }

    private static int currentTimeInSecs() {
        long currentTimeMS = System.currentTimeMillis();
        return (int) (currentTimeMS / 1000);
    }
}
