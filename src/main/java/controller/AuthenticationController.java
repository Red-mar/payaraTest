package controller;


import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import interceptor.MyInterceptor;
import model.facade.MyFacade;
import model.logic.PersonDO;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.interceptor.InterceptorBinding;
import javax.interceptor.Interceptors;
import javax.json.Json;
import javax.json.JsonObject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static security.Constants.ADMIN;
import static security.Constants.USER;

@Path("auth")
public class AuthenticationController {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class.getName());

    @Inject
    private SecurityContext securityContext;

    @Inject
    private MyFacade facade;

    @Interceptors({MyInterceptor.class})
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

    @POST
    @RolesAllowed({USER, ADMIN})
    @Path("2fa")
    public Response init2FA() {
       PersonDO person = facade.getPersonByName(
               securityContext.getCallerPrincipal().getName());

       GoogleAuthenticator auth = new GoogleAuthenticator();
       GoogleAuthenticatorKey key = auth.createCredentials();
       person.setSecret(key.getKey());
       person.setUsing2FA(true);
       facade.updatePerson(person);
       String url = GoogleAuthenticatorQRGenerator.getOtpAuthTotpURL(
               "Test",person.getUsername(), key);
       LOGGER.info("URL:" + url);
       JsonObject result = Json.createObjectBuilder()
               .add("url", url).build();
       return Response.ok(result).build();
    }

    @POST
    @RolesAllowed({USER, ADMIN})
    @Path("2fa/confirm")
    public boolean confirm2FA(int code) {
        PersonDO person = facade.getPersonByName(
                securityContext.getCallerPrincipal().getName());

        GoogleAuthenticator auth = new GoogleAuthenticator();
        boolean result = auth.authorize(person.getSecret(), code);
        person.setUsing2FA(true);
        facade.updatePerson(person);
        return result;
    }

    @POST
    @RolesAllowed({USER, ADMIN})
    @Path("2fa/disable")
    public boolean disable2FA() {
        PersonDO person = facade.getPersonByName(
                securityContext.getCallerPrincipal().getName());

        GoogleAuthenticator auth = new GoogleAuthenticator();
        person.setSecret("");
        person.setUsing2FA(false);
        facade.updatePerson(person);
        return true;
    }


}