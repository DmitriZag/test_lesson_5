package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class JunitSearchTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
    }
    @Test
    void fillJunitSearchTest() {
        //Открыть страницу Selenide в GitHub
        open("/selenide/selenide");

        //Перейти в раздел Wiki проекта
        $("#wiki-tab").click();

        //Проверить, что в списке страниц есть страница SoftAssertions
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $("#wiki-pages-box").shouldHave(text("SoftAssertions")).shouldBe(visible);

        //Открыть страницу SoftAssertions, проверить, что внутри есть пример кода для JUnit5
        $(byText("SoftAssertions")).click();
        $(".markdown-body").shouldHave(text(
        "@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}")).shouldBe(visible);

    }
}
