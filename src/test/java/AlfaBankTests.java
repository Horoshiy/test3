import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AlfaBankTests {

    @BeforeEach
    void prepare() {
        open("https://alfabank.ru/make-money/");
    }

    @Test
    void archiveDepositsCountTest() {
        $(byAttribute("title", "Вклады")).hover();
        $(byText("Депозиты")).closest("a").click();
        $(byText("Архивные счета и депозиты")).scrollTo().click();
        $(byAttribute("data-test-id", "tabs-list")).$(byText("Депозиты")).closest("button").click();
        $$(byAttribute("data-widget-name", "CatalogCard")).shouldHave(CollectionCondition.size(5));
    }

    @Test
    void insuranceWithSiblingTest() {
        SelenideElement element = $(byText("Страхование вкладов")).parent();
        element.sibling(0).shouldHave(text("Что такое вклад"));
        element.preceding(0).shouldHave(text("Описание"));
        element.closest("div").shouldHave(attribute("data-test-id", "tabs-list"));
    }
}
