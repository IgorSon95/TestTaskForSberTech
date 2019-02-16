package com.gmail.igorson090395.Elements.Laptops;

import com.gmail.igorson090395.Elements.Commons.CommonElements;
import com.gmail.igorson090395.Utils.Core;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Laptops {
    private final static int DISTANCE = 500;

    private static Laptops instance = null;
    private WebElement priceFromTextField;
    private WebElement priceToTextField;

    private Laptops() {
        priceFromTextField = Core.getInstance().getDriver().findElement(By.cssSelector("#glpricefrom"));
        priceToTextField = Core.getInstance().getDriver().findElement(By.cssSelector("#glpriceto"));
    }

    public static Laptops getInstance() {
        if (instance == null) {
            instance = new Laptops();
        }
        return instance;
    }

    public void sortWithProperties(LaptopPropertiesBuilder builder) {
        if (builder.getPrice().length() > 0) {
            fillField(priceFromTextField, builder.getPrice().replaceAll("\\s*-.*", ""));
            fillField(priceToTextField, builder.getPrice().replaceAll(".*-\\s*", ""));
        }
        scroll(DISTANCE);
        for (int i = 0; i < builder.getBrands().size(); i++) {
            new Actions(Core.getInstance().getDriver())
                    .moveToElement(Core.getInstance().getDriver().findElement(By.xpath("//span[text()='" + builder.getBrands().get(i) + "']/../../input")), 2, 2)
                    .click()
                    .perform();
            waitLoading();
        }
        scroll(-DISTANCE);
    }

    public List<WebElement> getGoodsList() {
        return Core.getInstance().getDriver().findElements(By.xpath("//div[contains(@class, 'n-snippet-list')]/div"));
    }

    public void search(String firstProductTitleInList) {
        new Actions(Core.getInstance().getDriver())
                .click(CommonElements.getInstance().getSearchField())
                .sendKeys(firstProductTitleInList, Keys.ENTER)
                .perform();
    }

    private void fillField(WebElement field, String keys) {
        if (keys.length() > 0) {
            field.click();
            field.sendKeys(keys);
            waitLoading();
        }
    }

    private void scroll(int distance) {
        ((JavascriptExecutor) Core.getInstance().getDriver()).executeScript("window.scrollBy(0, " + distance + ")");
    }

    private void waitLoading() {
        Core.sleep(500);
        WebDriverWait wait = new WebDriverWait(Core.getInstance().getDriver(), 5);
        wait.until(ExpectedConditions.attributeContains(Core.getInstance().getDriver().findElement(By.xpath("//div[contains(@class, 'content preloadable')]")), "style", "auto"));
    }

    public String getProductTitle(WebElement product) {
        return product.findElement(By.xpath("//div[contains(@class, 'header')]/div[contains(@class, 'title')]/a")).getAttribute("title");
    }
}