package ru.spb.selen.app.testsconfig;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spb.selen.app.pages.MainPage;
import ru.spb.selen.service.webdrivermanager.DriverManager;

import java.net.MalformedURLException;

import static ru.spb.selen.service.webdrivermanager.DriverManager.getWebDriver;

public class MainPageTestConfig {

    private Logger logger = LogManager.getLogger(MainPageTestConfig.class);
    protected MainPage mainPage;

    @BeforeAll
    void init() {
        logger.info("----------------------------------------------------------------------------------------------");
        logger.info("Кейсы главной страницы");
        logger.info("----------------------------------------------------------------------------------------------");
        try {
            DriverManager.setupBrowser();
        } catch (MalformedURLException e) {
            logger.error("Не удалось инициализировать webDriver", e.fillInStackTrace());
        }
        mainPage = new MainPage(getWebDriver());
    }

    @AfterAll
    void resourcesClose() {
        if(getWebDriver() != null) DriverManager.closeDriver();
    }
}
