package com.practice.jpa.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

public class SystemTest {

    @Test
    void firstSeleniumTest() {
        WebDriver browser = new SafariDriver();

        browser.get("http://localhost:8080");
        WebElement welcomeHeader = browser.findElement(By.tagName("h2"));

        assertThat(welcomeHeader.getText())
            .isEqualTo("Welcome");

        browser.close();
    }
}
