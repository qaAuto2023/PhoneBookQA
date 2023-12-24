package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseTest {

    @BeforeMethod
    public void precondition() {
        if (app.getUser().isLogged()) {
            app.getUser().logout();
        }
    }

    @Test
    public void loginPositiveTest() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        String email = "abc_" + i + "@def.org";
        String password = "$Abcdef12345";
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillOpenRegistrationForm(email, password);
        app.getUser().click(By.xpath("//button[2]"));
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//a[@href='/add']")));
    }

    @Test
    public void registrationNegativeTestWrongEmail() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        String email = "abc_" + i + "def.com";
        String password = "$Abcdef12345";
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillOpenRegistrationForm(email, password);
        app.getUser().click(By.xpath("//button[2]"));
        Assert.assertTrue(app.getUser().isErrorMessageFormat());
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

    @Test
    public void registrationNegativeTestWrongPassword() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        String email = "abc_" + i + "@def.com";
        String password = "Abcdef12345";
        app.getUser().openLoginRegistrationForm();
        app.getUser().fillOpenRegistrationForm(email, password);
        app.getUser().click(By.xpath("//button[2]"));
        Assert.assertTrue(app.getUser().isErrorMessageFormat());
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

    @AfterMethod
    public void tearDown() {
//        wb.quit();
    }
}
