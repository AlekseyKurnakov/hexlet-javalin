@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.MainPage
@param MainPage page

@template.layout.page(
    content = @`
        <h2>Главная страница</h2>
        <a href="${NamedRoutes.usersPath()}">Все пользователи</a>
        <p>Добро пожаловать на сайт.</p>

        @if(page.getCurrentUser() == null)
            <a href="${NamedRoutes.buildSessionsPath()}">Войти</a>
        @endif

        @if(!page.isVisited())
            Это сообщение показывается только один раз. Если вы хотите увидеть его снова, сотрите куки
        @endif

        @if(page.getCurrentUser() != null)
            Добро пожаловать, ${page.getCurrentUser()}.
            Чтобы разлогиниться, удалите куку JSESSIONID из браузера
        @endif
    `
)