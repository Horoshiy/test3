import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AlfaBankTests {
    @Test
    void archiveDepositsCountTest() {
        open("https://alfabank.ru/make-money/");
        $(by("title", "Вклады")).hover();
        $(byText("Депозиты")).closest("a").click();
        $(byText("Архивные счета и депозиты")).scrollTo().click();
        $(by("data-test-id", "tabs-list")).$(byText("Депозиты")).closest("button").click();
        $$(by("data-widget-name", "CatalogCard")).shouldHaveSize(5);
    }

    @Test
    void insuranceWithSiblingAndPreceedingAndClosestTest() {
        open("https://alfabank.ru/make-money/");
        SelenideElement element = $(byText("Страхование вкладов")).parent();
        element.sibling(0).shouldHave(text("Что такое вклад"));
        element.preceding(0).shouldHave(text("Описание"));
        element.closest("div").shouldHave(attribute("data-test-id", "tabs-list"));
    }
}
