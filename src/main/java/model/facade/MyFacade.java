package model.facade;

import model.logic.PersonDO;
import model.logic.PersonProvider;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
public class MyFacade {

    @EJB
    private PersonProvider pp;

    @PostConstruct
    public void init() {

    }

    public PersonDO getPersonById(int id) {
        return pp.getPersonById(id);
    }

    public String getExpectedPassword(String username) {
        return pp.getPersonByUsername(username).getPassword();
    }

    public Set<String> getGroupsByUsername(String username) {
        Set<String> set = new HashSet<String>();
        set.add(pp.getPersonByUsername(username).getRole().toString());
        return set;
    }

    public void createPerson(String name, String username, String password, String role) { pp.createPerson(name, username, password, role);}

    public List<PersonDO> getAllPersons() { return pp.getAllPerson();}

    public boolean deletePersonById(int id) {
        return pp.deletePersonById(id);
    }
}
