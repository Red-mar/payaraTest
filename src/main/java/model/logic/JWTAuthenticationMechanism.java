package model.logic;

import io.jsonwebtoken.ExpiredJwtException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.authentication.mechanism.http.RememberMe;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static model.logic.Constants.AUTHORIZATION_HEADER;
import static model.logic.Constants.BEARER;

@RememberMe(
        cookieMaxAgeSeconds = 60 * 60 * 24,
        isRememberMeExpression = "self.isRememberMe(httpMessageContext)"
)
@RequestScoped
public class JWTAuthenticationMechanism implements HttpAuthenticationMechanism {

    @Inject
    private IdentityStoreHandler identityStoreHandler;

    @Inject
    private TokenProvider tokenProvider;


    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse respone, HttpMessageContext context) throws AuthenticationException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String token = extractToken(context);

        if (name != null && password != null) {
            CredentialValidationResult result = identityStoreHandler.validate(new UsernamePasswordCredential(name, password));
            if (result.getStatus() == CredentialValidationResult.Status.VALID) {
                return createToken(result, context);
            }
            return context.responseUnauthorized();
        } else if (token != null) {
            return validateToken(token, context);
        } else if (context.isProtected()) {
            return context.responseUnauthorized();
        }
        // Do do nothing.
        return context.doNothing();
    }

    private AuthenticationStatus validateToken(String token, HttpMessageContext context) {
        try {
            if (tokenProvider.validateToken(token)) {
                JWTCredential credential = tokenProvider.getCredential(token);
                return context.notifyContainerAboutLogin(credential.getPrincipal(), credential.getAuthorities());
            }
            return context.responseUnauthorized();
        } catch (ExpiredJwtException e) {
            return context.responseUnauthorized();
        }
    }

    private AuthenticationStatus createToken(CredentialValidationResult result, HttpMessageContext context) {
        if (!isRememberMe(context)) {
            String jwt = tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups(), false);
            context.getResponse().setHeader(AUTHORIZATION_HEADER, BEARER + jwt);
        }
        return context.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
    }

    private String extractToken(HttpMessageContext context) {
        String authorizationHeader = context.getRequest().getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            String token = authorizationHeader.substring(BEARER.length(), authorizationHeader.length());
            return token;
        }
        return null;
    }

    public Boolean isRememberMe(HttpMessageContext context) {
        return Boolean.valueOf(context.getRequest().getParameter("rememberme"));
    }
}


