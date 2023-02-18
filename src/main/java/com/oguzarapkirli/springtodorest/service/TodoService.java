package com.oguzarapkirli.springtodorest.service;

import com.oguzarapkirli.springtodorest.model.Todo;

import java.util.List;

public interface TodoService {

    List<Todo> getAllTodos();

    List<Todo> getAllNotCompletedTodos();

    List<Todo> getAllCompletedTodos();

    Todo getTodoById(Long id);

    Todo addTodo(Todo todo);

    Todo updateTodoById(Long id, Todo todo);

    boolean deleteTodoById(Long id);

    Todo completeTodoById(Long id);

}
