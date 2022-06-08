package uz.maniac4j.microstreamperformance.repository;

import uz.maniac4j.microstreamperformance.model.Person;

import java.util.List;

public interface PersonRepository {
    public void add(Person person);

    public List<Person> findAll();

    public List<Person> findByName(String name);

    public Person findById(Long id);

    public void deleteAll();

    public void storeAll();
}
