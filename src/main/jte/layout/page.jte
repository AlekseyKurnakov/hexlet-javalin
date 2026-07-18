@import gg.jte.Content
@param Content content

<!doctype html>
<html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Hexlet Javalin</title>
    </head>
    <body>
        <header>
            <h1>Hexlet Javalin</h1>
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