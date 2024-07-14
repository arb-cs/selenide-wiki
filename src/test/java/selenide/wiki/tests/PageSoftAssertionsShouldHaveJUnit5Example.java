package selenide.wiki.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PageSoftAssertionsShouldHaveJUnit5Example {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void shouldHaveJUnitExampleTest() {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-pages-filter").setValue("SoftAssertions").shouldBe(Condition.visible);
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        $("#wiki-body").shouldHave(text("""
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                @Test
                void test() {
                Configuration.assertionMode = SOFT;
                open("page.html");
                $("#first").should(visible).click();
                $("#second").should(visible).click();
                """));
    }
}