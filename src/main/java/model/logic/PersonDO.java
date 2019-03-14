package model.logic;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "person.findOne", query = "select p from PersonDO p where p.id = :id"),
        @NamedQuery(name = "person.getAll", query = "select p from PersonDO p")
}
)
@Table(name="person")
public class PersonDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String username;
    private String password;

    @ManyToMany(cascade=CascadeType.REMOVE)
    private List<RoleDO> roles;

    public PersonDO(int id, String name, String username, String password, List<RoleDO> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = roles;
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

    public List<RoleDO> getRoles() { return roles; }

    public void setRoles(List<RoleDO> roles) { this.roles = roles; }
}

