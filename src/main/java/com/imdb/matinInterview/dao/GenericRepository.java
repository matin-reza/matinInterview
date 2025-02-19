package com.imdb.matinInterview.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T, PK> extends JpaRepository<T, PK> {

}
