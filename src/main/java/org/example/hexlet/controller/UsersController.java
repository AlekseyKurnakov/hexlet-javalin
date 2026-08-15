package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.MainPage;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.EditUserPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.UserRepository;
import org.example.hexlet.util.NamedRoutes;
import java.time.LocalDateTime;
import static io.javalin.rendering.template.TemplateUtil.model;

public class UsersController {

    public static void logRequestTime(Context ctx) {
        System.out.println(LocalDateTime.now());
    }

    public static void index(Context ctx) {
        var users = UserRepository.getEntities();
        var page = new UsersPage(users);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flashType"));
        // Отдаем обратно url + query params
        ctx.render("users/index.jte", model("page", page));
    }
    public static void show(Context ctx) {
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        User user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        UserPage page = new UserPage(user);
        ctx.render("users/show.jte", model("page", page));
    }

    public static void build(Context ctx) {
        BuildUserPage page = new BuildUserPage();
        ctx.render("users/build.jte", model("page", page));
    }

    public static void create(Context ctx) {
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
            ctx.sessionAttribute("flash", "Создан новый пользователь");
            ctx.sessionAttribute("flashType", "success");
            ctx.redirect(NamedRoutes.usersPath());
        } catch (ValidationException e) {
            ctx.sessionAttribute("flash", "Ошибка создания пользователя");
            ctx.sessionAttribute("flashType", "danger");
            var page = new BuildUserPage(name, email, e.getErrors());
            ctx.render("users/build.jte", model("page", page));
        }
    }

    public static void edit(Context ctx) {
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        User user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        EditUserPage page = new EditUserPage(
                user.getId()
                ,user.getName()
                ,user.getEmail()
                ,null);
        ctx.render("users/edit.jte", model("page", page));
    }

    public static void update(Context ctx) {
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        var name = ctx.formParam("name").trim();
        var email = ctx.formParam("email").trim().toLowerCase();

        try {
            var passwordConfirmation = ctx.formParam("passwordConfirmation");
            var password = ctx.formParamAsClass("password", String.class)
                    .check(value -> value.equals(passwordConfirmation), "Passwords are not the same")
                    .check(value -> value.length() > 6, "Password is to short")
                    .get();
            User user = UserRepository.find(id)
                    .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            ctx.redirect(NamedRoutes.usersPath());
        } catch (ValidationException e) {
            EditUserPage page = new EditUserPage(
                    id
                    ,name
                    ,email
                    ,e.getErrors());
            ctx.render("users/edit.jte", model("page", page));
        }
    }

}
