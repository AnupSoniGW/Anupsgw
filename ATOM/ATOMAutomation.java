package com.org.ATOMProject;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ATOMAutomation {
	WebDriver driver;
	WebDriverWait wait;
	Alert alert;
	

	@BeforeSuite
	public void setup() {
		// Initialize ChromeDriver
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@Test(priority = 0)
	public void loginWithGoogle() throws InterruptedException {

		driver.get("https://atom.dev.dmclinical.com/auth/login");

		// Click on the Login with Google button
		WebElement loginWithGoogleButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@tabindex='1' and text()= ' DM User Login ']")));
		loginWithGoogleButton.click();

		// Switch to the Google sign-in popup window
		String mainWindowHandle = driver.getWindowHandle();
		for (String windowHandle : driver.getWindowHandles()) {
			if (!windowHandle.equals(mainWindowHandle)) {
				driver.switchTo().window(windowHandle);
				break;
			}
		}

		// Enter email and click Next
		WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("identifierId")));
		emailInput.sendKeys("anups.gw@dmclinical.com");
		driver.findElement(By.id("identifierNext")).click();

		// Enter password and click Next
		WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Passwd")));
		passwordInput.sendKeys("AnUP$7852");
		driver.findElement(By.id("passwordNext")).click();

		Thread.sleep(1000);

		driver.switchTo().window(mainWindowHandle);

		WebElement welcomePopup = driver.findElement(By.xpath("//div/button[@class='btn btn-outline-dark']"));
		welcomePopup.click();

	}

	@Test(priority = 1)
	public void CreateLead() throws InterruptedException {

		WebElement leads = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Leads")));
		leads.click();

		Thread.sleep(8000);

		WebElement userManagement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("User Management")));
		userManagement.click();

		leads.click();

		WebElement createLead = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/a[@class='btn btn-outline-primary']")));
		createLead.click();

		WebElement leadName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name-column")));
		leadName.sendKeys("Miranda Olsan", Keys.TAB);

		WebElement externalReferenceID = driver.findElement(By.id("external-reference-id-column"));
		externalReferenceID.sendKeys("MO00001", Keys.TAB);

		Actions actions = new Actions(driver);

		WebElement sourceDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div/span[@class='ng-arrow-wrapper'])[1]")));
		actions.moveToElement(sourceDropdown).click().perform();

		// Click on the source option "Anup"
		WebElement sourceOption =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Anup")));
		actions.moveToElement(sourceOption).click().perform();

		// Print the selected option
		System.out.println("Selected option: " + sourceOption.getText());

		WebElement therapeauticAreaDropdown = driver
				.findElement(By.xpath("(//div[@class='ng-placeholder' and text() = 'Select your choice...'])[2]"));
		therapeauticAreaDropdown.click();

		WebElement therapeauticAreaOptions = driver.findElement(By.linkText("Allergy/Immunology"));

		Select select1 = new Select(therapeauticAreaOptions);

		select1.selectByIndex(0);
		WebElement selectedOption1 = select1.getFirstSelectedOption();
		System.out.println("Selected option: " + selectedOption1.getText());

		WebElement pbmcRequired = driver.findElement(By.id("pbmcRequired"));
		pbmcRequired.click();

		WebElement otherInformationOption = driver
				.findElement(By.xpath("//div/button[@type='button']/span[text()='Other Information']"));
		otherInformationOption.click();

	}

	@Test(enabled = false)
	public void addUserAdmin() throws InterruptedException {

	}

//	@AfterTest
//	public void tearDown() {
//		driver.quit();
//	}

}
