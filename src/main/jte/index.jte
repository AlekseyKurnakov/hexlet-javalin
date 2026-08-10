@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.MainPage
@param MainPage page

@template.layout.page(
    content = @`
        <h2>Главная страница</h2>
        <a href="${NamedRoutes.usersPath()}">Все пользователи</a>
        <p>Добро пожаловать на сайт.</p>

        @if(!page.isVisited())
            Это сообщение показывается только один раз. Если вы хотите увидеть его снова, сотрите куки
        @endif
    `
)