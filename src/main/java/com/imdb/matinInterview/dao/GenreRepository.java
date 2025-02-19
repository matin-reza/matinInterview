package com.imdb.matinInterview.dao;

import com.imdb.matinInterview.entity.Genre;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends GenericRepository<Genre, Long> {
    Optional<Genre> findGenreByTitleEqualsIgnoreCase(String title);
}
