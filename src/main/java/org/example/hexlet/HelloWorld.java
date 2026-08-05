package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import static io.javalin.rendering.template.TemplateUtil.model;

import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.dto.courses.CoursesPage;
import org.apache.commons.text.StringEscapeUtils;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;
import org.example.hexlet.util.NamedRoutes;

import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // Описываем, что загрузится по адресу /
        app.get(NamedRoutes.usersPath(), ctx -> {
            var users = UserRepository.getEntities();
            var page = new UsersPage(users);
            // Отдаем обратно url + query params
            ctx.render("users/index.jte", model("page", page));
        });


        app.get(NamedRoutes.buildUserPath(), ctx -> {
            var page = new BuildUserPage();
            ctx.render("users/build.jte", model("page", page));
        });


        app.post(NamedRoutes.usersPath(), ctx -> {
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
                ctx.redirect(NamedRoutes.usersPath());
            } catch (ValidationException e) {
                var page = new BuildUserPage(name, email, e.getErrors());
                ctx.render("users/build.jte", model("page", page));
            }
        });

        app.get(NamedRoutes.usersPath("{id}"), ctx -> {
            String userId = ctx.pathParam("id");

            ctx.render("users/show.jte", model("id", userId));
        });



        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get(NamedRoutes.coursePath(), ctx -> {
            List<Course> courses = CourseRepository.getEntities();
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

        app.get(NamedRoutes.buildCoursePath(), ctx -> {
            var page = new BuildCoursePage();
            ctx.render("courses/build.jte", model("page", page));
        });

        app.post(NamedRoutes.coursePath(), ctx -> {
            String name = ctx.formParam("name");
            String description = ctx.formParam("description");

            try {
                String validatedName = ctx.formParamAsClass("name", String.class)
                        .check(
                                value -> value.length() > 2,
                                "Название курса слишком короткое"
                        )
                        .get();

                String validatedDescription = ctx.formParamAsClass("description", String.class)
                        .check(
                                value -> value.length() > 10,
                                "Описание курса слишком короткое"
                        )
                        .get();

                Course course = new Course(validatedName, validatedDescription);
                CourseRepository.save(course);

                ctx.redirect(NamedRoutes.coursePath());
            } catch (ValidationException e) {
                BuildCoursePage page = new BuildCoursePage(
                        name,
                        description,
                        e.getErrors()
                );

                ctx.render("courses/build.jte", model("page", page));
            }
        });



        // Стартуем веб-сервер
        app.start(7070);
    }


}