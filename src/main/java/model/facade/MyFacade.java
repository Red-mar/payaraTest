package model.facade;

import model.logic.PersonDO;
import model.logic.PersonProvider;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import java.io.*;
import java.util.List;

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

    public void createPerson(String name) { pp.createPerson(name);}

    public List<PersonDO> getAllPersons() { return pp.getAllPerson();}
}
