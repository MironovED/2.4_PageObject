package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id='dashboard']");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
//    private ElementsCollection replenishCard = $$("[data-test-id='action-deposit'] .button__content");
    private SelenideElement amount = $("[data-test-id='amount'] .input__control");
    private SelenideElement fromCard = $("[data-test-id='from'] .input__control");
    private SelenideElement topUpButton = $("[data-test-id='action-transfer']");


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCardBalance(String id) {
        val text = cards.findBy(id(id)).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public DashboardPage transferMoney(String inCard, String value, String numberCard) {
        $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] [data-test-id='action-deposit']").click();
//        $(By.id(inCard)).find("[data-test-id='action-deposit']").click();
//        cards.findBy(id(inCard)).find("[.button__text]").click();
        amount.setValue(value);
        fromCard.setValue(numberCard);
        topUpButton.click();
        return new DashboardPage();
    }

}
