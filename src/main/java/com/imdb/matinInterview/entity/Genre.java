package com.imdb.matinInterview.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BASE_GENRE")
@Getter
@Setter
public class Genre extends BaseEntity<Long> {
    private String title;
}
