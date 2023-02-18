package com.oguzarapkirli.springtodorest.controller;

import com.oguzarapkirli.springtodorest.model.Todo;
import com.oguzarapkirli.springtodorest.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/todos", produces = "application/json")
@Tag(name = "Todos", description = "the Todos API with OpenAPI 3")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    @Operation(summary = "Get all todos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the todos"),
            @ApiResponse(responseCode = "204", description = "No todos found")
    })
    public ResponseEntity<List<Todo>> getAllTodos() {
        if (todoService.getAllTodos().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @Operation(summary = "Add a new todo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo added successfully"),
            @ApiResponse(responseCode = "400", description = "Todo could not added has error in request body")
    })
    @PostMapping
    public ResponseEntity<Todo> addTodo(@Valid @RequestBody Todo todo) {
        if (todoService.addTodo(todo) == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(todoService.addTodo(todo));
    }

    @Operation(summary = "Delete a todo by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Todo not found")
    })
    @GetMapping("/completed")
    public ResponseEntity<List<Todo>> getAllCompletedTodos() {
        if (todoService.getAllCompletedTodos().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(todoService.getAllCompletedTodos());
    }

    @Operation(summary = "Get all not completed todos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the todos"),
            @ApiResponse(responseCode = "204", description = "No todos found")
    })
    @GetMapping("/uncompleted")
    public ResponseEntity<List<Todo>> getAllNotCompletedTodos() {
        if (todoService.getAllNotCompletedTodos().isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(todoService.getAllNotCompletedTodos());
    }

    @Operation(summary = "Get a todo by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the todo"),
            @ApiResponse(responseCode = "404", description = "Todo not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        if (todoService.getTodoById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    @Operation(summary = "Update a todo by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo updated successfully"),
            @ApiResponse(responseCode = "404", description = "Todo not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodoById(@PathVariable Long id, @Valid @RequestBody Todo todo) {
        if (todoService.updateTodoById(id, todo) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(todoService.updateTodoById(id, todo));
    }

    @Operation(summary = "Delete a todo by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Todo not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable Long id) {
        if (todoService.deleteTodoById(id)) {
            return ResponseEntity.ok().body("Todo deleted successfully.");
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Complete a todo by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo completed successfully"),
            @ApiResponse(responseCode = "404", description = "Todo not found")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<Todo> completeTodoById(@PathVariable Long id) {
        if (todoService.completeTodoById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(todoService.completeTodoById(id));
    }

}
