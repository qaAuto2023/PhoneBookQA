package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTest extends  BaseTest{

    @BeforeMethod
    public void precondition(){
        String email = "dima1@co.il";
        String password = "12345$Dima";
        User user = new User().withEmail(email).withPassword(password);
        if (!app.getUser().isLogged()) {
            app.getUser().login(user);
        }
    }

    @Test
    public void deleteOneContactTest(){
         int res = app.getContact().removeOneContact();
        Assert.assertEquals(res, -1);
    }

    @Test
    public void removeAllContacts(){
        app.getContact().removeAllContacts();
        Assert.assertTrue(app.getContact().isElementPresent(By.xpath("//div[@class='contact-item_card__2SOIM']")));
    }
}
