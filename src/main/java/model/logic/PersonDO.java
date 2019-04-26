package model.logic;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "person.findOne", query = "select p from PersonDO p where p.id = :id"),
        @NamedQuery(name = "person.getByUsername", query = "select p from PersonDO p where p.username = :username"),
        @NamedQuery(name = "person.getAll", query = "select p from PersonDO p")
}
)
@Table(name = "person")
public class PersonDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean isDeleted;

    private boolean using2FA;
    private String secret;

    private String role;

    public PersonDO(int id, String name, String username, String password, String role,
                    boolean isDeleted, boolean using2FA, String secret) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isDeleted = isDeleted;
        this.using2FA = using2FA;
        this.secret = secret;
    }

    public PersonDO() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        this.isDeleted = deleted;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setUsing2FA(boolean using2FA) {
        this.using2FA = using2FA;
    }

    public boolean isUsing2FA() {
        return using2FA;
    }
}

