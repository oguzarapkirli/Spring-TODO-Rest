package com.oguzarapkirli.springtodorest.service;

import com.oguzarapkirli.springtodorest.model.Todo;
import com.oguzarapkirli.springtodorest.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("ALL")
@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public List<Todo> getAllNotCompletedTodos() {
        return todoRepository.findAllByCompletedIsFalse();
    }

    @Override
    public List<Todo> getAllCompletedTodos() {
        return todoRepository.findAllByCompletedIsTrue();
    }

    @Override
    public Todo getTodoById(Long id) {
        var todo = todoRepository.findById(id);
        return todo.get();
    }

    @Override
    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodoById(Long id, Todo todo) {
        var todoToUpdate = todoRepository.findById(id);
        todoToUpdate.get().setTitle(todo.getTitle());
        todoToUpdate.get().setDescription(todo.getDescription());
        todoToUpdate.get().setCompleted(todo.isCompleted());
        return todoRepository.save(todoToUpdate.get());
    }

    @Override
    public boolean deleteTodoById(Long id) {
        todoRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public Todo completeTodoById(Long id) {
        todoRepository.completeTodoById(id);
        return todoRepository.findById(id).get();
    }
}
