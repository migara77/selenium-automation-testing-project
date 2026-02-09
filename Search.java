package com.selenium.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Search{

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        // Step 1: Open Daraz
        openWebsite("https://www.daraz.lk/");

        // Step 2: Search for MacBook
        searchProduct("MacBook");

        // Step 3: Close Browser
        closeBrowser();
    }

    // Open website
    public static void openWebsite(String url) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        System.out.println("Website title: " + driver.getTitle());
        Thread.sleep(3000); // wait for page load
    }

    // Search for a product
    public static void searchProduct(String productName) throws InterruptedException {
        // Close initial popup if it appears
        try {
            WebElement popupClose = driver.findElement(By.cssSelector(".close-btn"));
            popupClose.click();
            Thread.sleep(1000);
        } catch (Exception e) {
            // Popup not shown
        }

        // Find search input
        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.sendKeys(productName);
        searchBar.submit();
        Thread.sleep(5000); // wait for search results

        // Print first few product names from results
        List<WebElement> results = driver.findElements(By.cssSelector("div[data-qa-locator='product-item'] a[title]"));
        System.out.println("Search results for: " + productName);
        int count = 0;
        for (WebElement result : results) {
            System.out.println("- " + result.getAttribute("title"));
            count++;
            if (count >= 5) break; // print only first 5 results
        }
    }

    // Close browser
    public static void closeBrowser() {
        driver.quit();
        System.out.println("Browser closed.");
    }
}
