package gg.jte.generated.ondemand.layout;
import org.example.hexlet.dto.BasePage;
import gg.jte.Content;
public final class JtepageGenerated {
	public static final String JTE_NAME = "layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,13,13,13,14,14,14,14,15,15,15,17,17,27,27,27,35,35,35,2,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content, BasePage page) {
		jteOutput.writeContent("\n<!doctype html>\n<html lang=\"ru\">\n    <head>\n        <meta charset=\"UTF-8\">\n        <title>Hexlet Javalin</title>\n        <link rel=\"stylesheet\"\n              href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\">\n    </head>\n    ");
		if (page != null && page.getFlash() != null) {
			jteOutput.writeContent("\n        <div class=\"alert alert-");
			jteOutput.setContext("div", "class");
			jteOutput.writeUserContent(page.getFlashType());
			jteOutput.setContext("div", null);
			jteOutput.writeContent("\" role=\"alert\">\n            ");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(page.getFlash());
			jteOutput.writeContent("\n        </div>\n    ");
		}
		jteOutput.writeContent("\n    <body>\n        <header>\n            <h1>Домашний чат Курнаковых</h1>\n            <nav>\n                <a href=\"/\">Главная</a>\n            </nav>\n        </header>\n\n        <main>\n            ");
		jteOutput.setContext("main", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n        </main>\n        <footer>\n            <p>\n                <a href=\"https://github.com/AlekseyKurnakov\">Мой GitHub</a>\n            </p>\n        </footer>\n    </body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.get("content");
		BasePage page = (BasePage)params.getOrDefault("page", null);
		render(jteOutput, jteHtmlInterceptor, content, page);
	}
}
