package model.logic;


import javax.annotation.security.DeclareRoles;
import javax.ejb.Stateless;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import static model.logic.Constants.*;

@DeclareRoles({ADMIN, USER})
@ApplicationPath("/rest")
public class JaxRsActivator extends Application {

}