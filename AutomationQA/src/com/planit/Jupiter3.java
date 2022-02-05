package com.planit;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Jupiter3 {
	WebDriver driver;

	// launching website
	@BeforeClass
	public void launchWebsite() throws InterruptedException {
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

	// Buying 2 Funny cows and 1 fluffy bunny
	@Test(priority = 0)
	public void shopPage() {
		try {
			WebElement shop_btn = driver.findElement(By.xpath("//a[@href='#/shop']"));
			shop_btn.click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			WebElement buyItem = driver
					.findElement(By.xpath("//h4[contains(text(),'Funny Cow')]/following-sibling::p/a"));
			buyItem.click();
			Thread.sleep(1000);
			buyItem.click();
			driver.findElement(By.xpath("//h4[contains(text(),'Fluffy Bunny')]/following-sibling::p/a")).click();
			System.out.println("2 Funny cows and 1 Fluffy bunny are added to the cart");
			// navigate to cart
			driver.findElement(By.xpath("//a[@href='#/cart']")).click();
			Thread.sleep(2000);
		} catch (Exception ex) {
			Assert.assertTrue(false);
			ex.printStackTrace();
		}
	}
	
	// verify products in the cart
	@Test(priority = 1)
	public void verifyCartItems() {
		try {
			
			//verify Funny Cow items in cart
			String productName_cart = driver.findElement(By.xpath("//tr[@ng-repeat='item in cart.items()']/td[contains(text(),'Funny Cow')]")).getText();
			Assert.assertEquals(productName_cart, "Funny Cow","Funny cow items are not added to cart");
			
			//verify Fluffy Bunny items in cart
			productName_cart = driver.findElement(By.xpath("//tr[@ng-repeat='item in cart.items()']/td[contains(text(),'Fluffy Bunny')]")).getText();
			Assert.assertEquals(productName_cart, "Fluffy Bunny","Fluffy Bunny items are not added to cart");
			
			// verify Funny Cow's quantity
			int productQuantity = Integer.parseInt(
					driver.findElement(By.xpath("//tr[@ng-repeat='item in cart.items()']/td[contains(text(),'Funny Cow')]/parent::tr/td/input[@name='quantity']"))
							.getAttribute("value"));
			Assert.assertEquals(productQuantity, 2,"Funny Cow's quantity is incorrect in the cart");
			
			//verify Fluffy Bunny's quantity
			productQuantity = Integer.parseInt(
					driver.findElement(By.xpath("//tr[@ng-repeat='item in cart.items()']/td[contains(text(),'Fluffy Bunny')]/parent::tr/td/input[@name='quantity']"))
							.getAttribute("value"));
			Assert.assertEquals(productQuantity, 1,"Fluffy Bunny's quantity is incorrect in the cart");
			
			System.out.println("Items are verified in the cart");
		}
		catch (Exception ex) {
			Assert.assertTrue(false);
			ex.printStackTrace();
		}
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.close();
	}
}
