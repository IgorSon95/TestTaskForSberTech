package com.gmail.igorson090395.TabManager;

public class TabManager {
    private static Category impl = null;

    public static Category tabManager() {
        return CategoryDefaultImpl.getInstance();
    }
}
