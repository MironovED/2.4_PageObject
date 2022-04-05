package ru.netology.page;

import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        $("[data-test-id='login'] .input__control").val(info.getLogin());
        $("[data-test-id='password'] .input__control").val(info.getPassword());
        $(".button").click();
        return new VerificationPage();
    }

}
