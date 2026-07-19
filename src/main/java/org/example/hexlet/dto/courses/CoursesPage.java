package org.example.hexlet.dto.courses;

import java.util.List;

import org.example.hexlet.model.Course;

public class CoursesPage {
    private List<Course> courses;
    public String term;

    public List<Course> getCourses() {
        return courses;
    }

    public String getTerm() {
        return term;
    }

    public CoursesPage(List<Course> courses, String term) {
        this.courses = courses;
        this.term = term;
    }
}