package com.gmail.igorson090395.TabManager.Product;

import com.gmail.igorson090395.TabManager.Category;
import com.gmail.igorson090395.Utils.Core;
import org.openqa.selenium.By;

import java.util.HashMap;

import static com.gmail.igorson090395.TabManager.Product.Product.ComputersTech.*;

public class ProductDefaultImpl implements Product {
    public static Product currentLocation = null;
    private static HashMap<Product.ProductType, String> products = new HashMap<>();

    public ProductDefaultImpl(Category.Categories category) {
        switch (category) {
            case ELECTRONIC:
                break;
            case AUTO:
                break;
            case SPORT:
                break;
            case BEAUTY:
                break;
            case HOME_GOODS:
                break;
            case CHILDREN_GOODS:
                break;
            case COMPUTERS_TECH:
                products.put(TABLETS, "");
                products.put(LAPTOP, "//div[contains(@class, 'b-spy-visible_js_inited b-zone_js_inited')]//a[@title='Ноутбуки']");
                products.put(STATIONARY, "");
                products.put(MONOBLOCKS, "");
                products.put(INDUSTRIAL, "");
                break;
            case HOUSEHOLD_TECH:
                break;
            case BUILD_AND_REPAIR:
                break;
        }
    }

    @Override
    public void go(ProductType product) {
        if (currentLocation != product) {
            Core.waitFor(Core.getInstance().getDriver().findElement(By.xpath(products.get(product))), 5);
            Core.getInstance().getDriver().findElement(By.xpath(products.get(product))).click();
        }
    }
}
