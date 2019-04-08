package model.logic;

import interceptor.MyInterceptor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.DatatypeConverter;

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
            byte[] hash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            String encoded = DatatypeConverter.printBase64Binary(hash);

            person.setPassword(encoded);
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
