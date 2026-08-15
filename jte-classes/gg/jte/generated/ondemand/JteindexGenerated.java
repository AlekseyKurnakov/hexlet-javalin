package gg.jte.generated.ondemand;
import org.example.hexlet.util.NamedRoutes;
import org.example.hexlet.dto.MainPage;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,6,6,8,8,8,8,8,8,8,8,8,11,11,12,12,12,12,12,12,12,12,12,13,13,15,15,17,17,19,19,20,20,20,22,22,23,23,23,23,23,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, MainPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n        <h2>Главная страница</h2>\n        <a");
				var __jte_html_attribute_0 = NamedRoutes.usersPath();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" href=\"");
					jteOutput.setContext("a", "href");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("a", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">Все пользователи</a>\n        <p>Добро пожаловать на сайт.</p>\n\n        ");
				if (page.getCurrentUser() == null) {
					jteOutput.writeContent("\n            <a");
					var __jte_html_attribute_1 = NamedRoutes.buildSessionsPath();
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
						jteOutput.writeContent(" href=\"");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(__jte_html_attribute_1);
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">Войти</a>\n        ");
				}
				jteOutput.writeContent("\n\n        ");
				if (!page.isVisited()) {
					jteOutput.writeContent("\n            Это сообщение показывается только один раз. Если вы хотите увидеть его снова, сотрите куки\n        ");
				}
				jteOutput.writeContent("\n\n        ");
				if (page.getCurrentUser() != null) {
					jteOutput.writeContent("\n            Добро пожаловать, ");
					jteOutput.setContext("html", null);
					jteOutput.writeUserContent(page.getCurrentUser());
					jteOutput.writeContent(".\n            Чтобы разлогиниться, удалите куку JSESSIONID из браузера\n        ");
				}
				jteOutput.writeContent("\n    ");
			}
		}, null);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		MainPage page = (MainPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
