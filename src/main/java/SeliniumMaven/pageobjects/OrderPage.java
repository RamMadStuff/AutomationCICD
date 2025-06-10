package SeliniumMaven.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeliniumMaven.AbstractComponents.AbstarctComponents;

public class OrderPage extends AbstarctComponents {

	WebDriver driver;
	@FindBy (css=".totalRow button")
	WebElement checkoutEle;
	@FindBy (css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public Boolean verifyOrderDisplay(String productName)
	{
		System.out.println("Looking for order with product name: " + productName);
		Boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		
		return match;
	}

	

}
