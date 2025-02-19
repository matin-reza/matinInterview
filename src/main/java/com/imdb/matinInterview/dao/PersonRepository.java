package com.imdb.matinInterview.dao;

import com.imdb.matinInterview.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends GenericRepository<Person, Long> {
    Optional<Person> findPersonByNameEqualsIgnoreCase(String name);
}
