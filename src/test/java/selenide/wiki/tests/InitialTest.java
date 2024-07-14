package selenide.wiki.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class InitialTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
    }

    @Test
    void shouldHaveJUnitExampleTest() {
        open("/selenide/selenide");

        $("#wiki-tab").click();

        $(".Layout-sidebar").$(".wiki-rightbar").$("#wiki-pages-box")
                .$(".js-wiki-sidebar-toggle-display").$("[data-filterable-for='wiki-pages-filter']")
                .$("[type='button']").click();

        $(".Layout-sidebar").$(".wiki-rightbar").$("#wiki-pages-box")
                .$(".js-wiki-sidebar-toggle-display").$("[data-filterable-for='wiki-pages-filter']")
                .$("[href='/selenide/selenide/wiki/SoftAssertions']").shouldHave(text("SoftAssertions")).click();

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