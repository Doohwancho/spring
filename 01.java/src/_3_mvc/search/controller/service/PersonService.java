package _3_mvc.search.controller.service;

import _3_mvc.search.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> searchPersons(String searchString);
}
