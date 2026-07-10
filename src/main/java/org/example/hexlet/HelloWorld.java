package org.example.hexlet;

import io.javalin.Javalin;

public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
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

        // Стартуем веб-сервер
        app.start(7070);
    }


}