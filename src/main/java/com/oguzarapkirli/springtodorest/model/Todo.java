package com.oguzarapkirli.springtodorest.model;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Hidden
    private long id;

    @Size(min = 3, max = 20, message = "Title must be at least 3 characters")
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @Size(min = 5, max = 100, message = "Description must be at least 5 characters")
    @NotBlank(message = "Description cannot be blank")
    private String description;

    @Hidden
    boolean completed = false;

    @Hidden
    final Timestamp createdDate = new Timestamp(new Date().getTime());

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id && completed == todo.completed && title.equals(todo.title) && description.equals(todo.description) && Objects.equals(createdDate, todo.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, completed, createdDate);
    }
}
