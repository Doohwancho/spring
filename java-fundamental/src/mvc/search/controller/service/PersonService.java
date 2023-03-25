package mvc.search.controller.service;

import mvc.search.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> searchPersons(String searchString);
}
