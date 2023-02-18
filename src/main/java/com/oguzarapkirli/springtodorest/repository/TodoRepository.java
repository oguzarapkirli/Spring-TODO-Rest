package com.oguzarapkirli.springtodorest.repository;

import com.oguzarapkirli.springtodorest.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByCompletedIsFalse();

    List<Todo> findAllByCompletedIsTrue();

    @Modifying
    @Query("UPDATE Todo t SET t.completed = true WHERE t.id = ?1")
    void completeTodoById(Long id);
}
