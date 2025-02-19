package com.imdb.matinInterview.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "MOVIE")
@Getter
@Setter
public class Movie extends BaseEntity<Long> {

    public Movie() {
    }

    public Movie(String title,
                 List<Genre> genres,
                 List<Person> actors,
                 Person director,
                 String description,
                 Integer year,
                 Integer runTime,
                 Float rating,
                 Integer vot) {
        this.title = title;
        this.genres = genres;
        this.actors = actors;
        this.director = director;
        this.description = description;
        this.year = year;
        this.runTime = runTime;
        this.rating = rating;
        this.vot = vot;
    }

    @Column(name = "TITLE")
    private String title;

    @ManyToMany
    @JoinTable(
            name = "MOVIE_GENRE",
            joinColumns = @JoinColumn(name = "MOVIE_ID"),
            inverseJoinColumns = @JoinColumn(name = "GENRE_ID"))
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "MOVIE_PERSON",
            joinColumns = @JoinColumn(name = "MOVIE_ID"),
            inverseJoinColumns = @JoinColumn(name = "PERSON_ID"))
    private List<Person> actors;

    @ManyToOne
    @JoinColumn(name = "DIRECTOR_ID")
    private Person director;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "YEAR_")
    private Integer year;

    @Column(name = "RUN_TIME")
    private Integer runTime;

    @Column(name = "RATING")
    private Float rating;

    private Integer vot;
}
