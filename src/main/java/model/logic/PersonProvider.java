package model.logic;

import javax.ejb.Remote;
import java.util.HashSet;
import java.util.List;

@Remote
public interface PersonProvider {
    PersonDO getPersonById(int id);
    PersonDO getPersonByName(String name);
    PersonDO getPersonByUsername(String username);
    void createPerson(String name, String username, String password, String role);
    List<PersonDO> getAllPerson();
}
