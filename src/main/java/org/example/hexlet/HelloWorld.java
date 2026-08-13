package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import org.example.hexlet.controller.RootController;
import org.example.hexlet.controller.SessionsController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.util.NamedRoutes;


public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });
        app.before(UsersController::logRequestTime);
        app.get(NamedRoutes.usersPath(), UsersController::index);
        app.get(NamedRoutes.buildUserPath(), UsersController::build);
        app.post(NamedRoutes.usersPath(), UsersController::create);
        app.get(NamedRoutes.editUserPath("{id}"), UsersController::edit);
        app.post(NamedRoutes.updateUserPath("{id}"), UsersController::update);
        app.get(NamedRoutes.userPath("{id}"), UsersController::show);

        app.get(NamedRoutes.rootPath(), RootController::index);

        app.get(NamedRoutes.buildSessionsPath(), SessionsController::build);
        app.post(NamedRoutes.sessionsPath(), SessionsController::create);



        // Стартуем веб-сервер
        app.start(7070);
    }



}