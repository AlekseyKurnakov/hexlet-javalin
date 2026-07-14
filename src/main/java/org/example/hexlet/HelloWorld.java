package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursesPage;

import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // Описываем, что загрузится по адресу /
        app.get("/", ctx -> ctx.result("Hello World"));
        app.get("/users", ctx -> ctx.result("GET /users"));

        app.get("/hello", ctx -> {
            String name = ctx.queryParam("name");

            if (name == null) {
                name = "World";
            }

            ctx.result("Hello, " + name + "!");
        });

        app.get("/users/{id}/post/{postId}", ctx -> {
            String userId = ctx.pathParam("id");
            String postId = ctx.pathParam("postId");
            ctx.result("User ID: " + userId + "\n" + "Post ID "  + postId);
        });

        app.get("/courses", ctx -> {
            List<Course> courses = Data.getCourses();
            String header = "Курсы по программированию";
            CoursesPage page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", model("page", page));
        });




        // Стартуем веб-сервер
        app.start(7070);
    }


}