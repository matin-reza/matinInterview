package com.imdb.matinInterview.config;

import com.imdb.matinInterview.entity.Genre;
import com.imdb.matinInterview.entity.Movie;
import com.imdb.matinInterview.entity.Person;
import com.imdb.matinInterview.service.GenreService;
import com.imdb.matinInterview.service.MovieService;
import com.imdb.matinInterview.service.PersonService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
@RequiredArgsConstructor
public class ImdbCSVReader {

    private static Logger LOGGER = LogManager.getLogger(ImdbCSVReader.class);

    private final PersonService personService;
    private final GenreService genreService;
    private final MovieService movieService;
    final String regex = "\"(.*?)\"";

    private String preProcessing(String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String original = matcher.group(1);
            String modified = original.replace(",", "|");
            input = input.replaceAll(original, modified);
        }
        return input;
    }

    private List<Genre> getGenres(String[] genreArray) {
        List<Genre> genreList = new ArrayList<>();
        for (String genreTitle : genreArray) {
            Genre genre = new Genre();
            genre.setTitle(genreTitle.replaceAll("\"", ""));
            genreList.add(genreService.save(genre));
        }
        return genreList;
    }

    private List<Person> getActors(String[] actorArray) {
        List<Person> actorList = new ArrayList<>();
        for (String actorTitle : actorArray) {
            Person person = new Person();
            person.setName(actorTitle.replaceAll("\"", ""));
            actorList.add(personService.save(person));
        }
        return actorList;
    }

    private void collectDataAndSaveToDB(String row) {
        try {
            String[] splitRow = row.split(",");
            String movieTitle = splitRow[1];
            String[] genres = splitRow[2].replaceAll("\"", "").split("\\|");
            String description = splitRow[3];
            String director = splitRow[4];
            String[] actors = splitRow[5].replaceAll("\"", "").split("\\|");
            String year = splitRow[6];
            String runtime = splitRow[7];
            String rating = splitRow[8];
            String vot = splitRow[9];

            movieService.save(new Movie(movieTitle,
                    this.getGenres(genres),
                    this.getActors(actors),
                    personService.save(new Person(director)),
                    description,
                    Integer.valueOf(year),
                    Integer.valueOf(runtime),
                    Float.valueOf(rating),
                    Integer.valueOf(vot)));
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.error(e);
        }
    }

    @PostConstruct
    public void readMoviesFromCSV() {
        try {

            ClassPathResource resource = new ClassPathResource("IMDB-Movie-Data.csv");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                reader.lines().skip(1).forEach(a -> {
                    String entireRow = preProcessing(a);
                    collectDataAndSaveToDB(entireRow);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
