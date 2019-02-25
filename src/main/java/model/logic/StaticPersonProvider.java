package com.icecreamstand.model.logic;

import interceptor.MyInterceptor;
import model.logic.PersonDO;
import model.logic.PersonProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Init;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.interceptor.Interceptor;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Interceptors(MyInterceptor.class)
@Stateless
public class StaticPersonProvider implements PersonProvider {

    @PersistenceContext
    private EntityManager em;

    @Override
    public PersonDO getPersonById(int id) {
        return em.createNamedQuery("person.findOne", PersonDO.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public List<PersonDO> getAllPerson() {
        return em.createNamedQuery("person.getAll", PersonDO.class).getResultList();
    }

    @Override
    public void createPerson(String name) {
        PersonDO person = new PersonDO();
        person.setName(name);
        em.persist(person);
    }

    @Override
    public PersonDO getPersonByName(String name) {
        return null;
    }
}
