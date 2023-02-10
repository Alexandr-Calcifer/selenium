package ru.spb.selen.app.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class Expectation {

    public static WebElement getWebElementAfterWaitBy(WebDriver webDriver, int seconds,
                                                      Function<? super WebDriver, WebElement> condition) {
        return new WebDriverWait(webDriver, Duration.ofSeconds(seconds)).until(condition);
    }

    public static WebElement waitElement(WebDriver webDriver, By elementBy, int seconds) {
        return new WebDriverWait(webDriver, Duration.ofSeconds(seconds))
                .until(driver -> driver.findElement(elementBy));
    }
}
