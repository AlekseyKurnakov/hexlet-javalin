package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursesPage;
import org.apache.commons.text.StringEscapeUtils;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.UserRepository;

import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // Описываем, что загрузится по адресу /
        app.get("/users", ctx -> {
            var users = UserRepository.getEntities();
            var page = new UsersPage(users);
            // Отдаем обратно url + query params
            ctx.render("users/index.jte", model("page", page));
        });

        app.get("/hello", ctx -> {
            String name = ctx.queryParam("name");

            if (name == null) {
                name = "World";
            }

            ctx.result("Hello, " + name + "!");
        });


        app.get("/users/build", ctx -> {
            var page = new BuildUserPage();
            ctx.render("users/build.jte", model("page", page));
        });


        app.post("/users", ctx -> {
            var name = ctx.formParam("name").trim();
            var email = ctx.formParam("email").trim().toLowerCase();

            try {
                var passwordConfirmation = ctx.formParam("passwordConfirmation");
                var password = ctx.formParamAsClass("password", String.class)
                        .check(value -> value.equals(passwordConfirmation), "Passwords are not the same")
                        .check(value -> value.length() > 6, "Password is to short")
                        .get();
                var user = new User(name, email, password);
                UserRepository.save(user);
                ctx.redirect("/users");
            } catch (ValidationException e) {
                var page = new BuildUserPage(name, email, e.getErrors());
                ctx.render("users/build.jte", model("page", page));
            }
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