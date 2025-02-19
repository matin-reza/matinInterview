package com.imdb.matinInterview.dao;

import com.imdb.matinInterview.dto.TitlePopularBaseGenreDTO;
import com.imdb.matinInterview.entity.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends GenericRepository<Movie, Long> {
    @Query(" select m.title from Movie m inner join m.actors a where a.name in(:actorNames)")
    List<String> getMovieByActorNames(List<String> actorNames);

    @Query(" select new com.imdb.matinInterview.dto.TitlePopularBaseGenreDTO(cast(max(m.vot + m.rating) as Integer), m.year) from Movie m inner join m.genres g where g.id = :genreId group by m.year")
    List<TitlePopularBaseGenreDTO> getPopularByGenreEachYear(Long genreId);
}
