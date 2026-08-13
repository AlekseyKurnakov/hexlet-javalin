@import org.example.hexlet.util.NamedRoutes
@import org.example.hexlet.dto.sessions.BuildSessionPage
@param BuildSessionPage page

@template.layout.page(
    content = @`
        @if(page.getErrors() != null)
            <ul>
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        @endif

        <form action="${NamedRoutes.sessionsPath()}" method="post">
          <input type="text" placeholder="Имя" name="nickname" required/>
          <input type="password" placeholder="Пароль" name="password" required/>
          <input type="submit" />
        </form>
    `
)