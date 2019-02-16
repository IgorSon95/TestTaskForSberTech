package com.gmail.igorson090395.TabManager;

import com.gmail.igorson090395.TabManager.Product.Product;
import com.gmail.igorson090395.TabManager.Product.ProductDefaultImpl;
import com.gmail.igorson090395.Utils.Core;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;

import static com.gmail.igorson090395.TabManager.Category.Categories.*;

public class CategoryDefaultImpl implements Category {
    private static CategoryDefaultImpl instance = null;
    private static HashMap<Categories, Product> entries = new HashMap<>();
    private static HashMap<Categories, String> categoriesXPaths = new HashMap<>();
    private Categories currentLocation = null;

    private CategoryDefaultImpl() {
        categoriesXPaths.put(ELECTRONIC, "");
        categoriesXPaths.put(HOUSEHOLD_TECH, "");
        categoriesXPaths.put(COMPUTERS_TECH, "//div[@class='n-w-tabs__horizontal-tabs n-adaptive-layout']//a[contains(@data-bem, 'Компьютерная техника')]");
        categoriesXPaths.put(BUILD_AND_REPAIR, "");
        categoriesXPaths.put(CHILDREN_GOODS, "");
        categoriesXPaths.put(AUTO, "");
        categoriesXPaths.put(HOME_GOODS, "");
        categoriesXPaths.put(SPORT, "");
        categoriesXPaths.put(BEAUTY, "");
    }

    public static CategoryDefaultImpl getInstance() {
        if (instance == null) {
            instance = new CategoryDefaultImpl();
        }
        return instance;
    }

    @Override
    public Product go(Categories category) {
        if (!entries.containsKey(category)) {
            entries.put(category, new ProductDefaultImpl(category));
        }
        Core.waitFor(Core.getInstance().getDriver().findElement(By.xpath(categoriesXPaths.get(category))), 5);
        Actions action = new Actions(Core.getInstance().getDriver());
        if (currentLocation != category) {
            action.moveToElement(Core.getInstance().getDriver().findElement(By.xpath(categoriesXPaths.get(category)))).build().perform();
            currentLocation = category;
            ProductDefaultImpl.currentLocation = null;
        }
        return entries.get(category);
    }

    @Override
    public void goToMainPage() {
        currentLocation = null;
        ProductDefaultImpl.currentLocation = null;
        Core.getInstance().getDriver().findElement(By.xpath("//a[contains(@class, 'logo_part_market')]")).click();
    }
}
