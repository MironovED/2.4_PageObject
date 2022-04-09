package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.MoneyTransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
    private String firstCard = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
    private String secondCard = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
    private String firstNumberCard = "5559_0000_0000_0001";
    private String secondNumberCard = "5559_0000_0000_0002";


    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void checkTransferToTheFirstCard() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var balance = new DashboardPage();
        int expectedBalanceFistCard = balance.getCardBalance(firstCard) + 2000;
        int expectedBalanceSecondCard = balance.getCardBalance(secondCard) - 2000;

        var moneyTransferPage = new MoneyTransferPage();
        moneyTransferPage.cardSelection(firstCard);
        moneyTransferPage.transferMoney("2000", secondNumberCard);

        assertEquals(expectedBalanceFistCard, balance.getCardBalance(firstCard));
        assertEquals(expectedBalanceSecondCard, balance.getCardBalance(secondCard));

    }

    @Test
    public void checkTransferToTheSecondCard() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var balance = new DashboardPage();
        int expectedBalanceFistCard = balance.getCardBalance(firstCard) - 4000;
        int expectedBalanceSecondCard = balance.getCardBalance(secondCard) + 4000;


        var moneyTransferPage = new MoneyTransferPage();
        moneyTransferPage.cardSelection(secondCard);
        moneyTransferPage.transferMoney("4000", firstNumberCard);

        assertEquals(expectedBalanceFistCard, balance.getCardBalance(firstCard));
        assertEquals(expectedBalanceSecondCard, balance.getCardBalance(secondCard));

    }

    @Test
    public void checkTransferWhenAmountMoreBalance() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var balance = new DashboardPage();
        int expectedBalanceFistCard = balance.getCardBalance(firstCard);
        int expectedBalanceSecondCard = balance.getCardBalance(secondCard);

        var moneyTransferPage = new MoneyTransferPage();
        moneyTransferPage.cardSelection(secondCard);
        moneyTransferPage.transferMoney("20000", firstNumberCard);

        assertEquals(expectedBalanceFistCard, balance.getCardBalance(firstCard));
        assertEquals(expectedBalanceSecondCard, balance.getCardBalance(secondCard));

    }

}
