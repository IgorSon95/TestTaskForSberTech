package com.gmail.igorson090395.TabManager.Product;

/**
 * В этом интерфейсе описываем ВСЕ продукты которые можно рассмотреть
 */
public interface Product {
    void go(ProductType product);

    interface ProductType {}

    enum Electronic implements ProductType {

    }

    enum HouseholdTech implements ProductType {

    }

    enum ComputersTech implements ProductType {
        TABLETS,
        LAPTOP,
        STATIONARY,
        MONOBLOCKS,
        INDUSTRIAL
    }
}
