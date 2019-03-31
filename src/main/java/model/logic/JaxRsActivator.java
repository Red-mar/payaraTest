package model.logic;


import javax.annotation.security.DeclareRoles;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import static security.Constants.ADMIN;
import static security.Constants.USER;

@DeclareRoles({ADMIN, USER})
@ApplicationPath("/rest")
public class JaxRsActivator extends Application {

}