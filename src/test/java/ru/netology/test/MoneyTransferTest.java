package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;
import ru.netology.page.MoneyTransferPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {


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

        var firstCardInfo = DataHelper.CardInfo.getFirstCardInfo();
        var secondCardInfo = DataHelper.CardInfo.getSecondCardInfo();
        int expectedBalanceFistCard = firstCardInfo.getBalance() + 2000;
        int expectedBalanceSecondCard = secondCardInfo.getBalance() - 2000;

        var moneyTransferPage = new MoneyTransferPage();
        moneyTransferPage.cardSelection(firstCardInfo.getIdCard());
        moneyTransferPage.transferMoney("2000", secondCardInfo.getNumberCard());

        var firstCardInfoAfter = DataHelper.CardInfo.getFirstCardInfo();
        var secondCardInfoAfter = DataHelper.CardInfo.getSecondCardInfo();
        int actualBalanceFistCard = firstCardInfoAfter.getBalance();
        int actualBalanceSecondCard = secondCardInfoAfter.getBalance();

        assertEquals(expectedBalanceFistCard, actualBalanceFistCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

    }

    @Test
    public void checkTransferToTheSecondCard() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var firstCardInfo = DataHelper.CardInfo.getFirstCardInfo();
        var secondCardInfo = DataHelper.CardInfo.getSecondCardInfo();
        int expectedBalanceFistCard = firstCardInfo.getBalance() - 4000;
        int expectedBalanceSecondCard = secondCardInfo.getBalance() + 4000;


        var moneyTransferPage = new MoneyTransferPage();
        moneyTransferPage.cardSelection(secondCardInfo.getIdCard());
        moneyTransferPage.transferMoney("4000", firstCardInfo.getNumberCard());

        var firstCardInfoAfter = DataHelper.CardInfo.getFirstCardInfo();
        var secondCardInfoAfter = DataHelper.CardInfo.getSecondCardInfo();
        int actualBalanceFistCard = firstCardInfoAfter.getBalance();
        int actualBalanceSecondCard = secondCardInfoAfter.getBalance();

        assertEquals(expectedBalanceFistCard, actualBalanceFistCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

    }

    @Test
    public void checkTransferWhenAmountMoreBalance() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var firstCardInfo = DataHelper.CardInfo.getFirstCardInfo();
        var secondCardInfo = DataHelper.CardInfo.getSecondCardInfo();
        int expectedBalanceFistCard = firstCardInfo.getBalance();
        int expectedBalanceSecondCard = secondCardInfo.getBalance();

        var moneyTransferPage = new MoneyTransferPage();
        moneyTransferPage.cardSelection(secondCardInfo.getIdCard());
        moneyTransferPage.transferMoney("20000", firstCardInfo.getNumberCard());

        var firstCardInfoAfter = DataHelper.CardInfo.getFirstCardInfo();
        var secondCardInfoAfter = DataHelper.CardInfo.getSecondCardInfo();
        int actualBalanceFistCard = firstCardInfoAfter.getBalance();
        int actualBalanceSecondCard = secondCardInfoAfter.getBalance();

        assertEquals(expectedBalanceFistCard, actualBalanceFistCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);

    }

}
