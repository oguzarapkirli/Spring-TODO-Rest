package com.oguzarapkirli.springtodorest.repository;

import com.oguzarapkirli.springtodorest.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByUserId (long userId);

    Todo findByIdAndUserId (long id, long userId);

    List<Todo> findAllByCompletedIsFalseAndUserId (long userId);
}
