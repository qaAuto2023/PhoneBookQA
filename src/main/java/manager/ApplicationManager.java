package manager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
//    WebDriver wd;
    EventFiringWebDriver wd;
    HelperUser user;
    HelperContact contact;
    String browser;
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);


    public ApplicationManager(String browser) {
        this.browser = browser;
    }



    public HelperUser getUser() {
        return user;
    }

    public HelperContact getContact() {
        return contact;
    }

    public void init() {
//        wd = new ChromeDriver();
        if(browser.equals(BrowserType.CHROME)){
            wd = new EventFiringWebDriver(new ChromeDriver());
            logger.info("Tests start on Chrome");
        } else if(browser.equals(BrowserType.FIREFOX)){
            wd = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("Tests start on Firefox");
        }

        wd.register(new MyListener());
        wd.navigate().to("https://telranedu.web.app/home");
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        user = new HelperUser(wd);
        contact = new HelperContact(wd);
    }

    public void tearDawn() {
//        wd.quit();
    }
}
