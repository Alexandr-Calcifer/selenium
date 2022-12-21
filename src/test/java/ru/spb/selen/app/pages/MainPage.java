package ru.spb.selen.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.spb.selen.app.util.Actions;

import static org.openqa.selenium.By.xpath;

public class MainPage extends Actions {

    private String pageUrl = "https://www.selenium.dev/selenium/web/web-form.html";
    private By textInput = xpath("//*[@id='my-text-id']");
    private By submitBtn = xpath("//button[@type='submit']");
    private By message = xpath("//*[@id='message']");

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public MainPage open() {
        openPage(pageUrl);
        return this;
    }

    public MainPage inputText(String text) {
        inputText(textInput, 10, text);
        return this;
    }

    public MainPage clickSubmitBtn() {
        click(submitBtn, 10);
        return this;
    }

    public String getText(){
        return getText(message, 10);
    }

}
