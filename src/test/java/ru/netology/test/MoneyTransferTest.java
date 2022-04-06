package ru.netology.test;



import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MoneyTransferTest {
    private String firstCard = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
    private String secondCard = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
    private String firstNumberCard = "5559_0000_0000_0001";
    private String secondNumberCard = "5559_0000_0000_0001";


    @Test
    public void should () {
        open("http://0.0.0.0:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage = new DashboardPage();
        dashboardPage.transferMoney(firstCard,"1000",secondNumberCard);

        dashboardPage.getCardBalance(firstCard);








    }



}
