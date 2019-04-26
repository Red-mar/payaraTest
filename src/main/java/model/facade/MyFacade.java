package model.facade;

import model.logic.PersonDO;
import model.logic.PersonProvider;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class MyFacade {

    @EJB
    private PersonProvider pp;

    @PostConstruct
    public void init() {

    }

    public PersonDO getPersonById(int id) {
        return pp.getPersonById(id);
    }

    public PersonDO getPersonByName(String username) { return pp.getPersonByUsername(username); }

    public String getExpectedPassword(String username) {
        return pp.getPersonByUsername(username).getPassword();
    }

    public Set<String> getGroupsByUsername(String username) {
        Set<String> set = new HashSet<String>();
        set.add(pp.getPersonByUsername(username).getRole().toString());
        return set;
    }

    public void createPerson(PersonDO person) {
        pp.createPerson(person);
    }

    public List<PersonDO> getAllPersons() { return pp.getAllPerson();}

    public boolean deletePersonById(int id) {
        return pp.deletePersonById(id);
    }

    public boolean updatePerson(PersonDO person) {
        return pp.updatePerson(person);
    }
}
