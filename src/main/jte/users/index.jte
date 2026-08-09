@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.users.UsersPage;
@param UsersPage page

@template.layout.page(
    content = @`
        @for(var user : page.getUsers())
            <div>
                <a href="${NamedRoutes.userPath(user.getId())}">${user.getName()}</a>
            </div>
        @endfor

        <div>
            <a href="${NamedRoutes.buildUserPath()}">Зарегистрироваться</a>
        </div>
    `
)