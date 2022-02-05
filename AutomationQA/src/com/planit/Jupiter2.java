package com.planit;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class Jupiter2 {
	WebDriver driver;

	@BeforeClass
	public void launchWebsite() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\vinod\\OneDrive\\Desktop\\Pinky\\Testing Softwares\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	// verifying contact page with data
	@Test(invocationCount = 5)
	public void contactPage() throws InterruptedException {
		driver.get("http://jupiter.cloud.planittesting.com");
		Thread.sleep(2000);
		WebElement contact_btn = driver.findElement(By.xpath("//a[@href='#/contact']"));
		contact_btn.click();
		Thread.sleep(3000);
		driver.findElement(By.id("forename")).sendKeys("Lee");
		driver.findElement(By.id("surname")).sendKeys("John");
		driver.findElement(By.id("email")).sendKeys("Lee@mail.com");
		driver.findElement(By.id("telephone")).sendKeys("12345678");
		driver.findElement(By.id("message")).sendKeys("Great!");
		System.out.println("All contact form fields are populated");
		WebElement submit_btn = driver.findElement(By.xpath("//div[@class='form-actions']/a"));
		submit_btn.click();
		System.out.println("Submitted form");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success']")));
		boolean Success_status = driver.findElement(By.xpath("//div[@class='alert alert-success']")).isEnabled();
		if (Success_status) {
			String Success_msg = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
			System.out.println("Successful submission message is :" + Success_msg);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass
	public void closeBrowser() {
		driver.close();
	}

}
