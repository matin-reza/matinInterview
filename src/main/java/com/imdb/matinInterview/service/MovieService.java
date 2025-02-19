package com.imdb.matinInterview.service;

import com.imdb.matinInterview.entity.Movie;

import javax.naming.NameNotFoundException;
import java.util.List;

public interface MovieService extends GenericService<Movie, Long> {
    List<String> getMoviesByActorName(List<String> actorNames);

    List<String> getTopMoveBaseOnGenreEachYear(String genre);
}
