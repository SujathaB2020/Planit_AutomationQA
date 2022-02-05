package com.planit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Jupiter4 {
	WebDriver driver;
	double stuffedFrogPrice;
	double fluffyBunnyPrice;
	double valentineBearPrice;
	double grandTotal;

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

	// Buying 2 Stuffed Frog, 5 Fluffy Bunny, 3 Valentine Bear and storing price of
	// each product to compare at cart page
	@Test(priority = 0)
	public void buyProducts() {
		try {
			WebElement shop_btn = driver.findElement(By.xpath("//a[@href='#/shop']"));
			shop_btn.click();
			Thread.sleep(2000);
			WebElement stuffedFrogItem = driver
					.findElement(By.xpath("//h4[contains(text(),'Stuffed Frog')]/following-sibling::p/span[@class='product-price ng-binding']"));
			stuffedFrogPrice = Double.parseDouble(stuffedFrogItem.getText().substring(1));
			WebElement buyItem = driver
					.findElement(By.xpath("//h4[contains(text(),'Stuffed Frog')]/following-sibling::p/a"));
			buyItem.click();
			buyItem.click();

			WebElement fluffyBunnyItem = driver
					.findElement(By.xpath("//h4[contains(text(),'Fluffy Bunny')]/following-sibling::p/span[@class='product-price ng-binding']"));
			fluffyBunnyPrice = Double.parseDouble(fluffyBunnyItem.getText().substring(1));
			buyItem = driver.findElement(By.xpath("//h4[contains(text(),'Fluffy Bunny')]/following-sibling::p/a"));

			for (int i = 1; i <= 5; i++) {
				buyItem.click();
				Thread.sleep(1000);
			}

			WebElement valentineBear = driver
					.findElement(By.xpath("//h4[contains(text(),'Valentine Bear')]/following-sibling::p/span[@class='product-price ng-binding']"));
			valentineBearPrice = Double.parseDouble(valentineBear.getText().substring(1));
			buyItem = driver.findElement(By.xpath("//h4[contains(text(),'Valentine Bear')]/following-sibling::p/a"));

			for (int i = 1; i <= 3; i++) {
				buyItem.click();
				Thread.sleep(1000);
			}
			System.out.println("2 Stuffed Frog, 5 Fluffy Bunny and 3 Valentine Bear are added to the cart");
			// navigating to cart page
			driver.findElement(By.xpath("//a[@href='#/cart']")).click();
			Thread.sleep(2000);

		} catch (Exception ex) {
			Assert.assertTrue(false);
			//ex.printStackTrace();
		}

	}

	// @Cart page
	// verify each product price
	@Test(priority = 1)
	public void verifyProductPrice() {
		try {
			// verify stuffed frog price
			WebElement stuffedFrogItem_cart = driver
					.findElement(By.xpath("//td[contains(text(),'Stuffed Frog')]/following-sibling::td[1]"));
			double stuffedFrogPrice_cart = Double.parseDouble(stuffedFrogItem_cart.getText().substring(1));
			if (stuffedFrogPrice == stuffedFrogPrice_cart) {
				System.out.println("Stuffed Frog price is verified");
			} else {
				System.out.println("stuffed frog price verification is failed");
			}

			// verify Fluffy Bunny price
			WebElement fluffyBunnyItem_cart = driver
					.findElement(By.xpath("//td[contains(text(),'Fluffy Bunny')]/following-sibling::td[1]"));
			double fluffyBunnyPrice_cart = Double.parseDouble(fluffyBunnyItem_cart.getText().substring(1));
			if (fluffyBunnyPrice == fluffyBunnyPrice_cart) {
				System.out.println("Fluffy Bunny price is verified");
			} else {
				System.out.println("Fluffy Bunny price verification is failed");
			}

			// verify Valentine Bear price
			WebElement valentineBearItem_cart = driver
					.findElement(By.xpath("//td[contains(text(),'Valentine Bear')]/following-sibling::td[1]"));
			double valentineBearPrice_cart = Double.parseDouble(valentineBearItem_cart.getText().substring(1));
			if (valentineBearPrice == valentineBearPrice_cart) {
				System.out.println("Valentine Bear price is verified");
			} else {
				System.out.println("Valentine Bear price verification is failed");
			}
		} catch (Exception ex) {
			Assert.assertTrue(false);
			//ex.printStackTrace();
		}
	}

	// verify each product sub total
	// calculating sum of sub totals
	@Test(priority = 2)
	public void VerifyProductSubTotal() {
		try {
			// verify Stuffed Frog sub total
			int productQuantity = Integer.parseInt(
					driver.findElement(By.xpath("//tr[@ng-repeat='item in cart.items()']/td[contains(text(),'Stuffed Frog')]/parent::tr/td/input[@name='quantity']"))
							.getAttribute("value"));
			double productSubTotal = stuffedFrogPrice * productQuantity;
			double productSubTotal_cart = Double.parseDouble(
					(driver.findElement(By.xpath("//tr[@ng-repeat='item in cart.items()']/td[contains(text(),'Stuffed Frog')]/parent::tr/td[4]")))
							.getText().substring(1));
			grandTotal = productSubTotal_cart;
			Assert.assertEquals(productSubTotal_cart, productSubTotal, "Stuffed Frog sub total price is incorrect");

			// verify Fluffy Bunny sub total
			productQuantity = Integer.parseInt(
					driver.findElement(By.xpath("//tr[@ng-repeat='item in cart.items()']/td[contains(text(),'Fluffy Bunny')]/parent::tr/td/input[@name='quantity']"))
							.getAttribute("value"));
			productSubTotal = fluffyBunnyPrice * productQuantity;
			productSubTotal_cart = Double.parseDouble(
					(driver.findElement(By.xpath("//tr[@ng-repeat='item in cart.items()']/td[contains(text(),'Fluffy Bunny')]/parent::tr/td[4]")))
							.getText().substring(1));
			grandTotal += productSubTotal_cart;
			Assert.assertEquals(productSubTotal_cart, productSubTotal, "Fluffy Bunny sub total price is incorrect");

			// verify Valentine Bear sub total
			productQuantity = Integer.parseInt(driver
					.findElement(By.xpath("//tr[@ng-repeat='item in cart.items()']/td[contains(text(),'Valentine Bear')]/parent::tr/td/input[@name='quantity']"))
					.getAttribute("value"));
			productSubTotal = valentineBearPrice * productQuantity;
			productSubTotal_cart = Double.parseDouble(
					(driver.findElement(By.xpath("//tr[@ng-repeat='item in cart.items()']/td[contains(text(),'Valentine Bear')]/parent::tr/td[4]")))
							.getText().substring(1));
			grandTotal += productSubTotal_cart;
			Assert.assertEquals(productSubTotal_cart, productSubTotal, "Valentine Bear sub total price is incorrect");

			System.out.println("Sub total of each product is verified");
		} catch (Exception ex) {
			Assert.assertTrue(false);
			//ex.printStackTrace();
		}

	}

	// verify grand total of all products
	@Test(priority = 3)
	public void verifyTotal() {
		try {
			String grandTotalElement_cart = driver.findElement(By.xpath("//tfoot/tr/td/strong")).getText();
			double grandTotal_cart = Double.parseDouble(grandTotalElement_cart.replaceAll("[^0-9.]", ""));
			Assert.assertEquals(grandTotal_cart, grandTotal, "Grand total of all products is incorrect");
			System.out.println("Grand total of all products is verified");
		} catch (Exception ex) {
			Assert.assertTrue(false);
			//ex.printStackTrace();
		}
	}

	@AfterClass
	public void closeBrowser() {
		driver.close();
	}

}
