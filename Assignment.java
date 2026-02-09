package com.selenium.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class Assignment {

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        // Task 1: Open Website
        openWebsite("https://www.demoblaze.com");

        // Task 2: User Registration
        userRegistration("miin21413", "miin3214");

        // Task 3: User Login
        userLogin("miin21413", "miin3214");

        // Task 4: Select a product category and specific product
        selectCategoryAndProduct("Laptops", "MacBook air"); // <-- change product as needed

        // Task 5: Close Browser
        closeBrowser();
    }

    // Task 1: Open Website
    public static void openWebsite(String url) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        System.out.println("Website title: " + driver.getTitle());
    }

    
    // Task 2: User Registration
    public static void userRegistration(String username, String password) throws InterruptedException {
        driver.findElement(By.id("signin2")).click(); // open registration modal
        Thread.sleep(1000);
        driver.findElement(By.id("sign-username")).sendKeys(username);
        driver.findElement(By.id("sign-password")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();

        // Handle alert
        Thread.sleep(2000);
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("Registration alert: " + alert.getText());
            alert.accept();
        } catch (Exception e) {
            System.out.println("No alert appeared after registration.");
        }
        System.out.println("Registration attempted for username: " + username);
    }

    // Task 3: User Login
    public static void userLogin(String username, String password) throws InterruptedException {
        driver.findElement(By.id("login2")).click(); // open login modal
        Thread.sleep(1000);
        driver.findElement(By.id("loginusername")).sendKeys(username);
        driver.findElement(By.id("loginpassword")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        Thread.sleep(3000); // wait for login
        try {
            String welcomeText = driver.findElement(By.id("nameofuser")).getText();
            System.out.println("Login successful: " + welcomeText);
        } catch (Exception e) {
            System.out.println("Login failed or username not displayed.");
        }
    }

    // Task 4: Select a category and search for a specific product
    public static void selectCategoryAndProduct(String category, String productName) throws InterruptedException {
        // Click category link
        driver.findElement(By.linkText(category)).click();
        Thread.sleep(2000);

        // Find all products in that category
        List<WebElement> products = driver.findElements(By.cssSelector(".card-title a"));
        boolean found = false;

        for (WebElement product : products) {
            if (product.getText().equalsIgnoreCase(productName)) {
                product.click();
                System.out.println("Selected product: " + productName + " from category: " + category);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Product '" + productName + "' not found in category: " + category);
        }

        Thread.sleep(3000);
    }

    // Task 5: Close Browser
    public static void closeBrowser() {
        driver.quit();
        System.out.println("Browser closed.");
    }
}
