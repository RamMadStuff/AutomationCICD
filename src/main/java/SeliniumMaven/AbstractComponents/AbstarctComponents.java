package SeliniumMaven.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeliniumMaven.pageobjects.CartPage;
import SeliniumMaven.pageobjects.OrderPage;

public class AbstarctComponents {
	WebDriver driver;
	
	public AbstarctComponents(WebDriver driver)
	{	
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement MyOrders;
	By toastMessage = By.cssSelector(".ng-animating");
	By cartPage = By.cssSelector("[routerlink*='cart']");
	
	public void waitForElementToAppear(By findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementToAppear(WebElement findBy)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	public void waitForWebElementToDissaapper(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	

	}
	public WebElement waitForWebElementToClikable(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.elementToBeClickable(findBy));
	

	}
	
	public CartPage goToCartPage()
	{
		waitForWebElementToDissaapper(toastMessage);
		WebElement cartHeader = waitForWebElementToClikable(cartPage);
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
		
	}
	public void waitForElementToDisappear(WebElement Ele)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.invisibilityOf(Ele));
	} 
	public 	OrderPage goToMyOrders()
	{
		MyOrders.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
		
	}
	
	
}
