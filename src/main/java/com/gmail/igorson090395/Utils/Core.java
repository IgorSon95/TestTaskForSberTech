package com.gmail.igorson090395.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Core {
    private static Core instance = null;
    private static WebDriver driver = null;
    private HashMap<String, String> specialKeys = new HashMap<>();

    private Core() {
        List<String> properties = InOut.readFile(new File("properties.txt"));
        //initialize custom properties
        properties.forEach(entry -> specialKeys.put(entry.split("\\s*=\\s*")[0], entry.split("\\s*=\\s*")[1]));
        System.setProperty("webdriver.chrome.driver", specialKeys.get("driverPath"));
        driver = new ChromeDriver(new ChromeOptions().addArguments("--start-maximized"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static Core getInstance() {
        if (instance == null) {
            instance = new Core();
        }
        return instance;
    }

    public static void sleep(int timeoutInMillis) {
        try {
            Thread.sleep(timeoutInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitFor(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getInstance().getDriver(), timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public HashMap<String, String> getSpecialKeys() {
        return specialKeys;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
