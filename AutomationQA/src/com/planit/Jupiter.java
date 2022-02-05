package com.planit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Jupiter {
	WebDriver driver;

	// launching website
	@BeforeClass
	public void launchWebsite() {
		try {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\vinod\\OneDrive\\Desktop\\Pinky\\Testing Softwares\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("http://jupiter.cloud.planittesting.com");
			Thread.sleep(2000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	// navigate to contact page and validate fields
	@Test(priority = 0)
	public void contactPage() {
		try {
			WebElement contact_btn = driver.findElement(By.xpath("//a[@href='#/contact']"));
			contact_btn.click();
			Thread.sleep(2000);
			WebElement submit_btn = driver.findElement(By.xpath("//a[contains(text(), 'Submit')]"));
			submit_btn.click();
			Thread.sleep(1000);
			String actual_error = driver.findElement(By.id("forename-err")).getText();
			String expected_error = "Forename is required";
			System.out.println("Error message : " +actual_error);
			Assert.assertEquals(actual_error, expected_error);
			actual_error = driver.findElement(By.id("email-err")).getText();
			expected_error = "Email is required";
			System.out.println("Error message : " +actual_error);
			Assert.assertEquals(actual_error, expected_error);
			actual_error = driver.findElement(By.id("message-err")).getText();
			expected_error = "Message is required";
			System.out.println("Error message : " +actual_error);
			Assert.assertEquals(actual_error, expected_error);
			Thread.sleep(1000);
			driver.findElement(By.id("forename")).sendKeys("Lee");
			driver.findElement(By.id("email")).sendKeys("Lee@mail.com");
			driver.findElement(By.id("message")).sendKeys("Great!");

			System.out.println("Mandatory fields are populated");
			// verify errors are gone
			boolean Success_status = driver.findElements(By.id("forename-err")).size() == 0;
			Success_status = driver.findElements(By.id("email-err")).size() == 0;
			Success_status = driver.findElements(By.id("message-err")).size() == 0;

			if (Success_status) {
				System.out.println("Errors are gone");
			} else {
				System.out.println("Errors are not gone");
			}

		} catch (Exception ex) {
			// Assert.assertTrue(false);
			ex.printStackTrace();
		}

	}

	@AfterClass
	public void closeBrowser() {
		driver.close();
	}

}
