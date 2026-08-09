package gg.jte.generated.ondemand.layout;
import gg.jte.Content;
public final class JtepageGenerated {
	public static final String JTE_NAME = "layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,18,18,18,18,27,27,27,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content) {
		jteOutput.writeContent("\n<!doctype html>\n<html lang=\"ru\">\n    <head>\n        <meta charset=\"UTF-8\">\n        <title>Hexlet Javalin</title>\n    </head>\n    <body>\n        <header>\n            <h1>Домашний чат Курнаковых</h1>\n            <nav>\n                <a href=\"/\">Главная</a>\n            </nav>\n        </header>\n\n        <main>\n            ");
		jteOutput.setContext("main", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n        </main>\n\n        <footer>\n            <p>\n                <a href=\"https://github.com/AlekseyKurnakov\">Мой GitHub</a>\n            </p>\n        </footer>\n    </body>\n</html>");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.get("content");
		render(jteOutput, jteHtmlInterceptor, content);
	}
}
