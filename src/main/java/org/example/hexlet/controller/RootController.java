package org.example.hexlet.controller;

import io.javalin.http.Context;
import org.example.hexlet.dto.MainPage;

import static io.javalin.rendering.template.TemplateUtil.model;

public class RootController {

    public static void index(Context ctx) {
        Boolean visited = Boolean.valueOf(ctx.cookie("visited"));
        String currentUser = ctx.sessionAttribute("currentUser");
        MainPage page = new MainPage(visited, currentUser);

        ctx.render("index.jte", model("page", page));

        ctx.cookie("visited", String.valueOf(true));
    }
}