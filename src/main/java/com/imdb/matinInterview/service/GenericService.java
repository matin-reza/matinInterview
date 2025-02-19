package com.imdb.matinInterview.service;

public interface GenericService<T, PK> {
    T save(T t);

    T loadById(PK pk);
}
