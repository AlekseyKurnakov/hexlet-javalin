package org.example.hexlet.model;
import lombok.ToString;

@ToString
public final class Course {
    private Long id;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @ToString.Include
    private String name;
    private String description;

    public Course(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public void setId(Long id) {
        this.id = id;
    }
}