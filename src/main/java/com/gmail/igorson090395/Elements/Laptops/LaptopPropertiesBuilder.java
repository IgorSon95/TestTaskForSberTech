package com.gmail.igorson090395.Elements.Laptops;

import java.util.Arrays;
import java.util.List;

public class LaptopPropertiesBuilder {
    //price in format 'FROM-TO'
    private String price = "";
    private List<String> brands;
    /**
     * Далее нужно описать каждый параметр для выбора ноутбука, т.к. в задании они не используются этот
     * момент опущу.
     */
    private List<String> monitorSize;
    private List<String> types;

    public LaptopPropertiesBuilder withPrice(String price) {
        this.price = price;
        return this;
    }

    public LaptopPropertiesBuilder withBrands(String... brands) {
        this.brands = Arrays.asList(brands);
        return this;
    }

    String getPrice() {
        return price;
    }

    List<String> getBrands() {
        return brands;
    }

    List<String> getMonitorSize() {
        return monitorSize;
    }

    List<String> getTypes() {
        return types;
    }
}
