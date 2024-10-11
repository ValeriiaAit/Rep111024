package com.phonebook;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if(app.getUserHelper().isSignOutButtonPresent()){
        app.getUserHelper().logout();
        }
        app.driver.get("https://telranedu.web.app/login");
    }

    @Test
    public void loginExistedUserPositiveTest1() {
        app.getUserHelper().clickLoginLink();
//        fillInRegistrationForm(new User("lera2024@gmail.com", "Valeriia24@"));
        app.getUserHelper().fillInRegistrationForm(new User()
                .setEmail("lera2024@gmail.com")
                .setPassword("Valeriia24@"));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent());
    }

    @Test
    public void loginExistedUserPositiveTest2(ITestContext context) {
        String email = "lera2024@gmail.com";
        String password = "Valeriia24@";
        context.setAttribute("email", email);
        context.setAttribute("password", password);
        app.getUserHelper().login(email, password);
    }

    @Test
    public void loginNegativeWOEmailTest() {
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User()
//                .setEmail("lera2024@gmail.com")
                .setPassword("Valeriia24@"));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertEquals(app.getUserHelper().alertTextPresent(), "Wrong email or password");
        Assert.assertTrue(app.getContactHelper().isAlertPresent());
    }

    @AfterMethod
    public void postConditions() {
        try {
            app.getUserHelper().logout();
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
    }
}