package com.phonebook;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactsTests extends TestBase {
    private final String CONTACT_NAME = "TestName";

    @BeforeMethod
    public void preCondition() {
        app.getUserHelper().login("lera2024@gmail.com", "Valeriia24@");
    }

    @Test(invocationCount = 1, priority =  1)
    public void addContactPositiveTest() {
        app.getContactHelper().addNewContactPositiveData(CONTACT_NAME);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
    }

    @Test(priority = 2)
    public void addContactPositiveWODescriptionTest() {
        app.getContactHelper().addNewContactPositiveDataWODescription(CONTACT_NAME);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
    }

    @AfterMethod(enabled = false)
    public void postCondition(){
        app.getContactHelper().deleteOneContact();
    }
}
