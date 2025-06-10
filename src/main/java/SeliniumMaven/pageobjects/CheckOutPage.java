package SeliniumMaven.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {
	WebDriver driver;
	@FindBy (xpath="//*[@placeholder='Select Country']")
	WebElement selectCountry;
	@FindBy (css=".ta-item:nth-child(3)")
	WebElement selectOption;
	@FindBy (css=".btnn.action__submit")
	WebElement selectSubmit;
	@FindBy (css=".hero-primary")
	WebElement result;
	
	
	public CheckOutPage(WebDriver driver) {
	
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public ConfirmationPage SelectCountry(String country)
	{
		selectCountry.sendKeys(country);;
		selectOption.click();
		selectSubmit.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
	
}
