package com.phonebook;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountPositiveTests extends TestBase {

    @Test
    public void createAccountPositiveTest1() {
        // click on Login link //a[.='LOGIN']
        app.getUserHelper().clickLoginLink();
        // enter email in input By.name("email")
        app.getUserHelper().type(By.name("email"), "admin_admin_20242@gmail.com");
        // enter password in input By.name("password")
        app.getUserHelper().type(By.name("password"),"Password1@");
        // click on registration button By.name("registration")
        app.getUserHelper().clickRegistrationButton();
        // Assert that button //button[.='Sign Out'] is present
        Assert.assertTrue(app.getUserHelper().isElementPresent(By.xpath("//button[.='Sign Out']")));
    }

    @Test
    public void createAccountPositiveTest2() {
        app.getUserHelper().register("lera2024@gmail.com", "Valeriia24@");
    }

    @Test
    public void createAccountLoginPositiveTest() {
        app.getUserHelper().register("lera2024@gmail.com", "Valeriia24@");
        app.getUserHelper().logout();
        app.getUserHelper().login("lera2024@gmail.com", "Valeriia24@");
    }
}
