@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.users.UserPage
@param UserPage page

@template.layout.page(
    content = @`
        <h1>User Name: ${page.getUser().getName()}</h1>
        <div>
            <a href="${NamedRoutes.editUserPath(page.getUser().getId())}">Редактировать</a>
        </div>
    `
)