package com.practice.jpa.test;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddOwnerPage extends PetClinicPageObject {

    public AddOwnerPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isReady() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("add-owner-form"))
        );
    }

}
