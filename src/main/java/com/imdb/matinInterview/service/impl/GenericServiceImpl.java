package com.imdb.matinInterview.service.impl;

import com.imdb.matinInterview.dao.GenericRepository;
import com.imdb.matinInterview.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class GenericServiceImpl<T, PK> implements GenericService<T, PK> {

    private final GenericRepository repository;

    @Override
    @Transactional
    public T save(T t) {
        return (T) repository.save(t);
    }

    @Override
    public T loadById(PK pk) {
        return (T) repository.findById(pk);
    }
}
