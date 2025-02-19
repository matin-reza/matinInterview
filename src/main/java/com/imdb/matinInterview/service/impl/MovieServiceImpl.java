package com.imdb.matinInterview.service.impl;

import com.imdb.matinInterview.dao.MovieRepository;
import com.imdb.matinInterview.dto.TitlePopularBaseGenreDTO;
import com.imdb.matinInterview.entity.Genre;
import com.imdb.matinInterview.entity.Movie;
import com.imdb.matinInterview.service.GenreService;
import com.imdb.matinInterview.service.MovieService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.awt.print.Book;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl extends GenericServiceImpl<Movie, Long> implements MovieService {
    private MovieRepository movieRepository;
    private GenreService genreService;
    private EntityManager entityManager;

    public MovieServiceImpl(MovieRepository movieRepository,
                            GenreService genreService,
                            EntityManager entityManager
    ) {
        super(movieRepository);
        this.movieRepository = movieRepository;
        this.genreService = genreService;
        this.entityManager = entityManager;
    }

    @Override
    public List<String> getMoviesByActorName(List<String> actorNames) {
        return movieRepository.getMovieByActorNames(actorNames);
    }

    @Override
    public List<String> getTopMoveBaseOnGenreEachYear(String genre) {
        Optional<Genre> genreOptional = genreService.findGenreByTitleEqualsIgnoreCase(genre);
        if (genreOptional.isEmpty())
            throw new NoResultException("not found with this genre title");
        List<TitlePopularBaseGenreDTO> results = movieRepository.getPopularByGenreEachYear(genreOptional.get().getId());
        if (results != null && results.size() > 0) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<String> query = cb.createQuery(String.class);
            Root<Movie> root = query.from(Movie.class);
            Join<Movie, Genre> genreJoin = root.join("genres");

            Predicate[] predicates = new Predicate[results.size()];
            for (int i = 0; i < results.size(); i++) {
                predicates[i] = cb.and(
                        cb.equal(root.get("year"), results.get(i).year()),
                        cb.lessThan(cb.sum(cb.sum(root.get("vot"), root.get("rating")), -1 * results.get(i).voteRating()), 1),//because of floating data type
                        cb.equal(genreJoin.get("id"), genreOptional.get().getId())
                );
            }

            query.select(root.get("title")).where(cb.or(predicates));
            return entityManager.createQuery(query).getResultList();
        }
        throw new NoResultException("not found with this genre title");
    }
}
