package com.practice.jpa.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FindOwnersPage extends PetClinicPageObject {

    public FindOwnersPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void isReady() {

    }

    public ListOfOwnersPage findOwners(String ownerLastName) {
        driver.findElement(By.id("lastName")).sendKeys(ownerLastName);

        WebElement findOwnerButton = driver.findElement(By.id("search-owner-form"))
            .findElement(By.tagName("button"));
        findOwnerButton.click();

        ListOfOwnersPage listOfOwnersPage = new ListOfOwnersPage(driver);
        listOfOwnersPage.isReady();
        return listOfOwnersPage;
    }

    @Override
    protected void visit(String url) {
        visit("/owners/find");
    }
}
