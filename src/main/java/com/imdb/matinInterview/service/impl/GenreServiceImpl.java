package com.imdb.matinInterview.service.impl;

import com.imdb.matinInterview.dao.GenreRepository;
import com.imdb.matinInterview.entity.Genre;
import com.imdb.matinInterview.service.GenreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class GenreServiceImpl extends GenericServiceImpl<Genre, Long> implements GenreService {
    private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        super(genreRepository);
        this.genreRepository = genreRepository;
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        Optional<Genre> persistedGenre = genreRepository.findGenreByTitleEqualsIgnoreCase(genre.getTitle());
        if (persistedGenre.isEmpty())
            return super.save(genre);
        return persistedGenre.get();
    }

    @Override
    public Optional<Genre> findGenreByTitleEqualsIgnoreCase(String title) {
        return genreRepository.findGenreByTitleEqualsIgnoreCase(title);
    }
}
