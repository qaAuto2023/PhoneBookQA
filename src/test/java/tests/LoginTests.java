package tests;

import manager.NgListener;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(NgListener.class)
public class LoginTests extends BaseTest {

    @BeforeMethod
    public void precondition(){
        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }

    @Test
    public void loginPositiveTest() {
        String email = "dima1@co.il";
        String password = "12345$Dima";
        User user = new User().withEmail(email).withPassword(password);
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillOpenRegistrationForm(user);
        app.getUser().click(By.xpath("//button[1]"));
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//*[text()='Sign Out']")));
    }

    @Test
    public void loginNegativeTestWrongEmail() {
        String email = "dima1co.il";
        String password = "12345$Dima";
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillOpenRegistrationForm(email, password);
        app.getUser().click(By.xpath("//button[1]"));
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

    @AfterMethod
    public void postCondition(){

    }
}
