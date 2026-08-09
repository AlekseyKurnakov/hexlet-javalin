@import org.example.hexlet.util.NamedRoutes

@template.layout.page(
    content = @`
        <h2>Главная страница</h2>
        <a href="${NamedRoutes.usersPath()}">Все пользователи</a>
        <p>Добро пожаловать на сайт.</p>
    `
)