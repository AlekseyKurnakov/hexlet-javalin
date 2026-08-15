@import org.example.hexlet.dto.BasePage
@import gg.jte.Content
@param Content content
@param BasePage page = null

<!doctype html>
<html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Hexlet Javalin</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    </head>
    @if(page != null && page.getFlash() != null)
        <div class="alert alert-${page.getFlashType()}" role="alert">
            ${page.getFlash()}
        </div>
    @endif
    <body>
        <header>
            <h1>Домашний чат Курнаковых</h1>
            <nav>
                <a href="/">Главная</a>
            </nav>
        </header>

        <main>
            ${content}
        </main>
        <footer>
            <p>
                <a href="https://github.com/AlekseyKurnakov">Мой GitHub</a>
            </p>
        </footer>
    </body>
</html>