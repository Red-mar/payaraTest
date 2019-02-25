package model.logic;

import javax.persistence.*;

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
