package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursesPage;
import org.apache.commons.text.StringEscapeUtils;

import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // Описываем, что загрузится по адресу /
        app.get("/users", ctx -> ctx.result("GET /users"));

        app.get("/hello", ctx -> {
            String name = ctx.queryParam("name");

            if (name == null) {
                name = "World";
            }

            ctx.result("Hello, " + name + "!");
        });

        app.get("/users/{id}", ctx -> {
            String userId = ctx.pathParam("id");

            ctx.render("users/show.jte", model("id", userId));
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/courses", ctx -> {
            List<Course> courses = Data.getCourses();
            String term = ctx.queryParam("term");

            if (term == null) {
                term = "";
            }

            if (!term.isBlank()) {
                String search = term.toLowerCase();

                courses = courses.stream()
                        .filter(course -> course.getName().toLowerCase().contains(search)
                                || course.getDescription().toLowerCase().contains(search))
                        .toList();
            }

            CoursesPage page = new CoursesPage(courses, term);
            ctx.render("courses/index.jte", model("page", page));
        });



        // Стартуем веб-сервер
        app.start(7070);
    }


}