package model.logic;

public interface PersonProvider {
    PersonDO getPersonById(int id);
    PersonDO getPersonByName(String name);
}
