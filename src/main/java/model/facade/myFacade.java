package model.facade;

import com.icecreamstand.model.logic.StaticPersonProvider;
import model.logic.PersonDO;
import model.logic.PersonProvider;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;

@Singleton
public class myFacade {
    @EJB
    private PersonProvider pp;

    @PostConstruct
    public void init() {

    }

    public PersonDO getPersonById(int id) {
        return pp.getPersonById(id);
    }
}
