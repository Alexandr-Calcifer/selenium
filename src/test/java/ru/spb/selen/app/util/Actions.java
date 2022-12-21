package ru.spb.selen.app.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static ru.spb.selen.app.util.Expectation.waitElement;

public class Actions {
    private final WebDriver webDriver;

    public Actions(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected void openPage(String url) {
        webDriver.get(url);
    }

    protected void inputText(By element, int seconds, String text) {
        waitElement(webDriver, element, seconds).sendKeys(text);
    }

    protected void click(By element, int seconds) {
        waitElement(webDriver, element, seconds).click();
    }

    public String getText(By element, int seconds) {
        return waitElement(webDriver, element, seconds).getText();
    }

    static class Locator {

        static By xpath(String locator) {
            return By.xpath(locator);
        }

        static By css(String locator) {
            return By.cssSelector(locator);
        }

        static By id(String locator) {
            return By.id(locator);
        }

        static By name(String locator) {
            return By.id(locator);
        }

        static By partialLinkText(String locator) {
            return By.partialLinkText(locator);
        }
    }
}
