package com.icecreamstand.model.logic;

import model.logic.PersonDO;
import model.logic.PersonProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Init;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class StaticPersonProvider implements PersonProvider {

    private ArrayList<PersonDO> personList;

    @PersistenceContext
    private EntityManager em;

    public void initialise(){
        personList = new ArrayList<>();
        personList.add(new PersonDO(1, "test"));
        personList.add(new PersonDO(2, "test2"));
    }

    @Override
    public PersonDO getPersonById(int id) {
        PersonDO result = null;
        result = new PersonDO();
        result.setName("hibernater");
        //em.createNativeQuery("DROP TABLE PERSON").executeUpdate();
        //em.createNativeQuery("CREATE TABLE PERSON").executeUpdate();
        em.persist(result);
        return result;
    }

    @Override
    public PersonDO getPersonByName(String name) {
        return null;
    }
}
