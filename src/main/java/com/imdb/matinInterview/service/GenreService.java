package com.imdb.matinInterview.service;

import com.imdb.matinInterview.entity.Genre;

import java.util.Optional;

public interface GenreService extends GenericService<Genre, Long> {
    Optional<Genre> findGenreByTitleEqualsIgnoreCase(String title);
}
