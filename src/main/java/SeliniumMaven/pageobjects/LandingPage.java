package SeliniumMaven.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeliniumMaven.AbstractComponents.AbstarctComponents;

public class LandingPage extends AbstarctComponents {

		// TODO Auto-generted method stub
	WebDriver driver;
	
		public LandingPage(WebDriver driver)
		{
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this); //-this is for @findBY because findby doesnot know about driver
		
		}

	//WebElement userMail = driver.findElement(By.id(("userEmail")));
	@FindBy(id="userEmail")
	WebElement userMail;
	
	@FindBy(id="userPassword")	
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	@FindBy(css=".ng-star-inserted")
	WebElement getError;
	//ng-tns-c4-1 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error
	
	//div[@aria-label='Incorrect email or password.']
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public ProductCatalogue LoginApplication(String email, String enterpassword)
	{
		
		userMail.sendKeys(email);
		password.sendKeys(enterpassword);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	public String getErrorMessage()
	{
		waitForWebElementToAppear(getError);
		return getError.getText();
		
	}
	
	
	
	
	
	
}