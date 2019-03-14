package model.logic;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class RoleDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public RoleDO(String name) { this.name = name; }

    public RoleDO() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
