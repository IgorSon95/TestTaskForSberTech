package com.gmail.igorson090395.Elements.Commons;

import com.gmail.igorson090395.Utils.Core;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CommonElements {
    private static CommonElements instance = null;

    private WebElement searchField;

    private CommonElements() {
        searchField = Core.getInstance().getDriver().findElement(By.cssSelector("#header-search"));
    }

    public static CommonElements getInstance() {
        if (instance == null) {
            instance = new CommonElements();
        }
        return instance;
    }

    public WebElement getSearchField() {
        return searchField;
    }
}
