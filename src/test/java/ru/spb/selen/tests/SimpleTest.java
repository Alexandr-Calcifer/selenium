package ru.spb.selen.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.spb.selen.app.pages.MainPage;
import ru.spb.selen.service.webdrivermanager.DriverManager;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.spb.selen.app.util.Expectation.waitElement;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimpleTest extends DriverManager {

    private MainPage mainPage;

    @BeforeAll
    void initDriver() throws MalformedURLException {
        setupBrowser();
        mainPage = new MainPage(getWebDriver());
    }

    @AfterAll
    void quit() {
        if(getWebDriver() != null) closeDriver();
    }

    @Test
    void eightComponents() {
        System.out.println("Start test");
        mainPage.open();

        String title = getWebDriver().getTitle();
        assertEquals("Web form", title);

        mainPage.inputText("Selenium");
        mainPage.clickSubmitBtn();

        WebElement message = waitElement(getWebDriver(), By.xpath("//*[@id='message']"), 10);
        String value = mainPage.getText();
        assertEquals("Received!", value);
        System.out.println("End test");
    }
}
