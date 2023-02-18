package com.oguzarapkirli.springtodorest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Todo {

    @Id
    @SequenceGenerator(name = "todo_id_seq", sequenceName = "todo_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "todo_id_seq", strategy = GenerationType.SEQUENCE)
    private long id;

    @NotBlank(message = "Title is cannot be blank")
    private String title;

    @Size(min = 10, message = "Description must be at least 10 characters")
    private String description;

    private boolean isCompleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    private User userId;
}
