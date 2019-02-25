package model.logic;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface PersonProvider {
    PersonDO getPersonById(int id);
    PersonDO getPersonByName(String name);
    void createPerson(String name);
    List<PersonDO> getAllPerson();
}
