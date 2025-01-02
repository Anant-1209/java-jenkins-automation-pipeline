package com.example.automation;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import io.github.bonigarcia.wdm.WebDriverManager;

class LoginAutomationTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Set implicit wait for the entire test
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    void testLogin() {
        try {
            // Using a real website for testing (replace with your actual test website)
            driver.get("https://opensource-demo.orangehrmlive.com/");

            // Wait for username field and enter credentials
            WebElement usernameField = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.name("username")));
            usernameField.sendKeys("Admin");

            // Find and fill password
            WebElement passwordField = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.name("password")));
            passwordField.sendKeys("admin123");

            // Find and click login button
            WebElement loginButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
            loginButton.click();

            // Wait for the dashboard page to load and verify login
            wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.className("oxd-topbar-header")));

            // Verify we're on the dashboard

            String currentUrl = driver.getCurrentUrl();
            assertTrue(currentUrl.contains("/dashboard"), "Login failed: Not redirected to dashboard");

        } catch (Exception e) {
            System.err.println("Test failed with error: " + e.getMessage());
            throw e;
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
