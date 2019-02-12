package model.logic;

import javax.persistence.*;

@Entity
@Table(name="person")
public class PersonDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public PersonDO(int id, String name) {
        this.id = id;
        this.name = name;
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
}
