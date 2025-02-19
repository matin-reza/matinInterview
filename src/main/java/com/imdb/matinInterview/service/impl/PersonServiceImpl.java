package com.imdb.matinInterview.service.impl;

import com.imdb.matinInterview.dao.PersonRepository;
import com.imdb.matinInterview.entity.Person;
import com.imdb.matinInterview.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PersonServiceImpl extends GenericServiceImpl<Person, Long> implements PersonService {
    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        super(personRepository);
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public Person save(Person person) {
        Optional<Person> persistedPerson = personRepository.findPersonByNameEqualsIgnoreCase(person.getName());
        if (persistedPerson.isEmpty())
            return super.save(person);
        return persistedPerson.get();
    }
}
