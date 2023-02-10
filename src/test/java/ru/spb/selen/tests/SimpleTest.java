package ru.spb.selen.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.spb.selen.app.testsconfig.MainPageTestConfig;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimpleTest extends MainPageTestConfig {

    @Test
    void eightComponents() {
        String title = mainPage.open().getTitle();
        assertEquals("Web form", title);
        mainPage
            .inputTextInTextInputField("Selenium")
            .clickSubmitBtn();
        String message = mainPage.getTextMessage();
        assertEquals("Received!", message);
    }
}
