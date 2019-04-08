package model.logic;


import javax.annotation.security.DeclareRoles;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Application;
import javax.ws.rs.ext.Provider;

import java.io.IOException;

import static security.Constants.ADMIN;
import static security.Constants.USER;

@DeclareRoles({ADMIN, USER})
@ApplicationPath("/rest")
public class JaxRsActivator extends Application {

}