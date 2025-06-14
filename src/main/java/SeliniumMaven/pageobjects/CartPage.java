package SeliniumMaven.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeliniumMaven.AbstractComponents.AbstarctComponents;

public class CartPage extends AbstarctComponents {

	WebDriver driver;
	@FindBy (css=".totalRow button")
	WebElement checkoutEle;
	@FindBy (css=".cartSection h3")
	List<WebElement> cartProducts;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public Boolean verifyProductDisplay(String productName)
	{
	
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckOutPage checkOut()
	{
		checkoutEle.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
		
		
	}

	
}
