package model.logic;

import model.facade.MyFacade;

import javax.inject.Inject;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static javax.security.enterprise.identitystore.IdentityStore.ValidationType.PROVIDE_GROUPS;

public class AuthorizationIdentityStore implements IdentityStore {

    @Inject
    MyFacade facade;

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        Set<String> result = facade.getGroupsByUsername(validationResult.getCallerPrincipal().getName());
        if (result == null) {
            result = emptySet();
        }
        return result;
    }

    @Override
    public Set<ValidationType> validationTypes() {
        return singleton(PROVIDE_GROUPS);
    }
}
