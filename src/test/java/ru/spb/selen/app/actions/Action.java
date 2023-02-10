package ru.spb.selen.app.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static ru.spb.selen.app.actions.Expectation.waitElement;

public class Action {
    protected final WebDriver webDriver;

    public Action(WebDriver webDriver) {
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

    protected String getText(By element, int seconds) {
        return waitElement(webDriver, element, seconds).getText();
    }
}
