package com.gmail.igorson090395.TabManager;

import com.gmail.igorson090395.TabManager.Product.Product;

/**
 * В этом интерфейсе описываем все категории
 * Данный подход позволяет масштабировать будущую программу под разные типы вебгуя
 */
public interface Category {
    Product go(Categories category);

    void goToMainPage();

    enum Categories {
        ELECTRONIC,
        HOUSEHOLD_TECH,
        COMPUTERS_TECH,
        BUILD_AND_REPAIR,
        CHILDREN_GOODS,
        AUTO,
        HOME_GOODS,
        SPORT,
        BEAUTY
    }
}
