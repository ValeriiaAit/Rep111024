package com.phonebook;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTests extends TestBase {

    @Test
    public void createAccountPositiveTest1() {
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User()
                .setEmail("lera2024@gmail.com")
                .setPassword("Valeriia24@"));
        app.getUserHelper().clickRegistrationButton();
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent());
    }

    @Test
    public void createAccountPositiveTest2() {
        String email = "lera2024" + System.currentTimeMillis() +"@gmail.com";
        String password = "Valeriia24@";
        app.getUserHelper().register(email, password);
    }

    @Test
    public void createAccountLoginPositiveTest() {
        String email = "lera2024" + System.currentTimeMillis() +"@gmail.com";
        String password = "Valeriia24@";
        app.getUserHelper().register(email, password);
        app.getUserHelper().logout();
        app.getUserHelper().login(email, password);
    }

    @Test
    public void createAccountNegativeTest() {
        SoftAssert softAssert = new SoftAssert();
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User()
                .setEmail("lera2024@gmail.com")
                .setPassword("Valeriia24@"));
        app.getUserHelper().clickRegistrationButton();
        //Assert.assertFalse(isSignOutButtonPresent());
//        Assert.assertTrue(isAlertPresent());
//        Assert.assertTrue(isError409Present());
        softAssert.assertTrue(app.getUserHelper().isAlertPresent());
        softAssert.assertTrue(app.getUserHelper().isError409Present());
        softAssert.assertAll();
    }

}