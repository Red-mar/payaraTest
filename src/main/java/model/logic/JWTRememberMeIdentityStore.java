package model.logic;

import io.jsonwebtoken.ExpiredJwtException;

import javax.inject.Inject;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.credential.RememberMeCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.RememberMeIdentityStore;
import java.util.Set;

public class JWTRememberMeIdentityStore implements RememberMeIdentityStore {

    @Inject
    private TokenProvider tokenProvider;

    @Override
    public CredentialValidationResult validate(RememberMeCredential rememberMeCredential) {
        try {
            if (tokenProvider.validateToken(rememberMeCredential.getToken())) {
                JWTCredential credential = tokenProvider.getCredential(rememberMeCredential.getToken());
                return new CredentialValidationResult(credential.getPrincipal(), credential.getAuthorities());
            }
            return CredentialValidationResult.INVALID_RESULT;
        } catch (ExpiredJwtException e) {
            return CredentialValidationResult.INVALID_RESULT;
        }
    }

    @Override
    public String generateLoginToken(CallerPrincipal callerPrincipal, Set<String> set) {
        return tokenProvider.createToken(callerPrincipal.getName(), set, true);
    }

    @Override
    public void removeLoginToken(String s) {
        //
    }
}
