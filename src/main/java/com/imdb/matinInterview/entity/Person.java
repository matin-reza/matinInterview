package com.imdb.matinInterview.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PERSON")
@Getter
@Setter
public class Person extends BaseEntity<Long> {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }
}
