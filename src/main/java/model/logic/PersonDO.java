package model.logic;

import com.sun.xml.internal.ws.developer.Serialization;

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

    private String role;

    public PersonDO(int id, String name, String username, String password, String role, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isDeleted = isDeleted;
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
}

