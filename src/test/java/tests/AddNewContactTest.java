package tests;

import models.Contact;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewContactTest extends BaseTest {
    @BeforeMethod
    public void precondition(){
        String email = "dima1@co.il";
        String password = "12345$Dima";
        User user = new User().withEmail(email).withPassword(password);
        if (!app.getUser().isLogged()) {
           app.getUser().login(user);
        }
    }

    @Test(invocationCount = 5)
    public void addNewContactPositive() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        Contact contact = Contact.builder().name("Joe").lastName("Doe").phone("123456" + i)
                .email("Joe" + i + "@mail.com").address("Haifa").description("Best friend").build();
        app.getContact().openContactForm();
        app.getContact().fillContactForm(contact);
        app.getContact().submitContactForm();
        Assert.assertEquals(contact.getPhone(), app.getContact().
                getText(By.xpath("//div[@class='contact-item_card__2SOIM'][last()]//h3")));
    }
}
