package controller;


import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("auth")
public class AuthenticationController {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class.getName());

    @Inject
    private SecurityContext securityContext;

    @GET
    @PermitAll
    @Path("login")
    public Response login() {
        LOGGER.log(Level.INFO, "login");
        if (securityContext.getCallerPrincipal() != null) {
            JsonObject result = Json.createObjectBuilder()
                    .add("user", securityContext.getCallerPrincipal().getName())
                    .build();
            return Response.ok(result).build();
        }
        return Response.status(UNAUTHORIZED).build();
    }

}