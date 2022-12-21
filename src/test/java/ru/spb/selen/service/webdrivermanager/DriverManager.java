package ru.spb.selen.service.webdrivermanager;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.net.MalformedURLException;
import java.net.URL;

@ContextConfiguration(classes = {BaseConfiguration.class})
@TestPropertySource(value = "classpath:app.properties")
public class DriverManager {

        private static String remote_url;
        private static String browserName;

        @Value("${hub.url:}")
        private void getRemote_url(String remote_url){
            DriverManager.remote_url = remote_url;
        }
        @Value("${browser.default:}")
        private void getBrowserName(String browserName){
            DriverManager.browserName = browserName;
        }
        private static ThreadLocal<RemoteWebDriver> threadLocal = new ThreadLocal<>();

        protected static void setupBrowser() throws MalformedURLException {
            switch (browserName) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    setRemoteWebDriverInThreadLocal(threadLocal, chromeOptions);
                    break;
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    setRemoteWebDriverInThreadLocal(threadLocal, firefoxOptions);
                    break;
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    setRemoteWebDriverInThreadLocal(threadLocal, edgeOptions);
                    break;
            }
        }

        private static void setRemoteWebDriverInThreadLocal(ThreadLocal<RemoteWebDriver> threadLocal,
                                                            MutableCapabilities browserOptions) throws MalformedURLException {
            threadLocal.set(new RemoteWebDriver(new URL(remote_url), browserOptions));
        }

        protected void closeDriver() {
            if (threadLocal.get() != null) {
                tearDownDriver();
            } else {
                throw new NullPointerException("Driver not found");
            }
        }

        protected WebDriver getWebDriver() {
            return threadLocal.get();
        }

        private void tearDownDriver() {
            getWebDriver().quit();
        }
}
