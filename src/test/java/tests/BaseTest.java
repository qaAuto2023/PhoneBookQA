package tests;

import manager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;


public class BaseTest {
    Logger logger = LoggerFactory.getLogger(BaseTest.class);
    public static ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.tearDawn();
    }

    @BeforeMethod
    public void startLogger(Method method){
        logger.info("Start test ---> " + method.getName());
    }

    @AfterMethod
    public void end(){
        logger.info("=================================");
    }
}


