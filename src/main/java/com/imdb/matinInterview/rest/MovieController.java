package com.imdb.matinInterview.rest;

import com.imdb.matinInterview.service.MovieService;
import com.imdb.matinInterview.validation.MovieValidation;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/movieByActorName")
    public ResponseEntity getMoviesByActorName(@RequestParam List<String> actorNames) {
        MovieValidation.validateActorNames(actorNames);
        return ResponseEntity.ok(movieService.getMoviesByActorName(actorNames));
    }

    @GetMapping("/popularEachYear/genre")
    public ResponseEntity getTopMoveBaseOnGenreEachYear(@RequestParam @NotBlank String genre) {
        return ResponseEntity.ok(movieService.getTopMoveBaseOnGenreEachYear(genre));
    }
}
