package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.sessions.BuildSessionPage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.UserRepository;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public class SessionsController {

    public static void build(Context ctx) {
        var page = new BuildSessionPage();
        ctx.render("sessions/build.jte", model("page", page));
    }

    public static void create(Context ctx) {

        String nickname = ctx.formParam("nickname").trim();

        User user = UserRepository.findByNickname(nickname)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + nickname + " not found"));
        try {
            var password = ctx.formParamAsClass("password", String.class)
                    .check(value -> value.equals(user.getPassword()), "Не верный пароль")
                    .get();
            ctx.sessionAttribute("currentUser", nickname);
            ctx.redirect("/");
        } catch (ValidationException e) {
            var page = new BuildSessionPage(nickname, e.getErrors());
            ctx.render("sessions/build.jte", model("page", page));
        }
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect("/");
    }
}
