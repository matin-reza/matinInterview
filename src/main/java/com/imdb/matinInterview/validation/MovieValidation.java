package com.imdb.matinInterview.validation;

import java.util.List;

public class MovieValidation {
    public static void validateActorNames(List<String> actorNames) {
        if (actorNames == null || actorNames.size() != 2) {
            throw new IllegalArgumentException("The size of request-actor-name must be 2 elements");
        }
        for (String name : actorNames) {
            if (name == null || name.equals(""))
                throw new IllegalArgumentException("The value of request-parameter-entry is invalid");
        }
    }
}
