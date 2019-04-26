package security;

import javax.security.enterprise.credential.AbstractClearableCredential;
import javax.security.enterprise.credential.Password;

public class UsernamePasswordTwoFactorCredential extends AbstractClearableCredential {

    private final String user;
    private final Password password;
    private final String twofactor;

    UsernamePasswordTwoFactorCredential(String user, String password, String twofactor) {
        this.user = user;
        this.password = new Password(password);
        this.twofactor = twofactor;
    }

    public Password getPassword() {
        return this.password;
    }

    public String getPasswordAsString() {
        return String.valueOf(this.getPassword().getValue());
    }

    public String getTwofactor() {
        return this.twofactor;
    }

    public String getUser() {
        return this.user;
    }

    @Override
    protected void clearCredential() {
        this.password.clear();
    }

    public boolean compareTo(String callerName, String password) {
        return this.getUser().equals(callerName) && this.getPassword().compareTo(password);
    }
}
