package model.logic;

import interceptor.MyInterceptor;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static model.logic.Constants.ADMIN;

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
    public void createPerson(String name, String username, String password, String role) {
        
        try {
            PersonDO person = new PersonDO();
            person.setName(name);

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            String encryptedString = new String(messageDigest.digest());

            person.setPassword(encryptedString);
            person.setUsername(username);
            person.setRole(role);

            em.persist(person);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public PersonDO getPersonByName(String name) {
        return null;
    }

    @Override
    public PersonDO getPersonByUsername(String username) {
        return em.createNamedQuery("person.getByUsername", PersonDO.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public boolean deletePersonById(int id) {
        try {
            em.remove(getPersonById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
