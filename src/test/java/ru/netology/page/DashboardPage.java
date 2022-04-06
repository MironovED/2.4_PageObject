package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id='dashboard']");
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private ElementsCollection replenishCard = $$("[data-test-id='action-deposit']");
    private SelenideElement amount = $("[data-test-id='amount']");
    private SelenideElement fromCard = $("[data-test-id='from']");
    private SelenideElement topUpButton = $("[data-test-id='action-transfer']");


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCardBalance(String id) {
        val text = cards.findBy(text(id)).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public DashboardPage transferMoney(String inCard, String value, String outCard) {
        replenishCard.findBy(text(inCard)).click();
        amount.setValue(value);
        fromCard.setValue(outCard);
        topUpButton.click();
        return new DashboardPage();
    }

}
