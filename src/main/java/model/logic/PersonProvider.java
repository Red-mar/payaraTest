package model.logic;

import javax.ejb.Remote;
import java.util.HashSet;
import java.util.List;

@Remote
public interface PersonProvider {
    PersonDO getPersonById(int id);
    PersonDO getPersonByUsername(String username);
    PersonDO createPerson(PersonDO person);
    List<PersonDO> getAllPerson();
    boolean deletePersonById(int id);
    boolean updatePerson(PersonDO person);
}
