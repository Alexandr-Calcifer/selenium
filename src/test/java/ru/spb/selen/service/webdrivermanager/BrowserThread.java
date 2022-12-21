package ru.spb.selen.service.webdrivermanager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BrowserThread {
    private static ThreadLocal<RemoteWebDriver> threadLocal = new ThreadLocal<>();
    private static String remote_url = "http://localhost:4444/wd/hub";

    @BeforeAll
    public static void start() throws MalformedURLException {
        System.out.println("=======Starting junit 5 tests========");
    }

    public static void setupBrowser(String browserName) throws MalformedURLException {
        if(browserName.equalsIgnoreCase("chrome")) {
            System.out.println("Inside Chrome");
            ChromeOptions chromeOptions = new ChromeOptions();
//            chromeOptions.setCapability("browserName", "chrome");
            threadLocal.set(new RemoteWebDriver(new URL(remote_url), chromeOptions));
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            System.out.println("Inside Firefox");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
//            firefoxOptions.setCapability("browserName", "Firefox");
            threadLocal.set(new RemoteWebDriver(new URL(remote_url), firefoxOptions));
        }
        else if (browserName.equalsIgnoreCase("edge")) {
            System.out.println("Inside Edge");
            EdgeOptions edgeOptions = new EdgeOptions();
            threadLocal.set(new RemoteWebDriver(new URL(remote_url), edgeOptions));
        }
    }

    @AfterEach
    public void closeDrivers() {
        if (threadLocal.get() != null) {
            tearDownDriver();
        } else {
            throw new NullPointerException("Driver not found");
        }
    }

    protected static WebDriver getDriver() {
        return threadLocal.get();
    }

    private static void tearDownDriver() {
        getDriver().quit();
    }

    protected static Stream<Arguments> browser() {
//        return Arrays.stream(args).map(Arguments::arguments);
        return Stream.of(
                arguments("Chrome"),
                arguments("Edge"),
                arguments("Firefox")
        );
    }
}
