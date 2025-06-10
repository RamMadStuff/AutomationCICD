package SeliniumMaven.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeliniumMaven.AbstractComponents.AbstarctComponents;

public class ProductCatalogue extends AbstarctComponents {

		// TODO Auto-generted method stub
	WebDriver driver;
	
		public ProductCatalogue(WebDriver driver)
		{
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this); //-this is for @findBY because findby doesnot know about driver
		
		}

	//WebElement userMail = driver.findElement(By.id(("userEmail")));
		//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"))
	@FindBy(css=".mb-3")
	List<WebElement> products;
	@FindBy(css="[routerlink*='cart']")
	WebElement checkout;
	@FindBy(css=".ng-animating")
	WebElement spinner;
	@FindBy(css=".cartSection h3")
	List<WebElement> cartproducts;
	@FindBy(css="//*[@placeholder='Select Country']")
	WebElement country;
	@FindBy(css=".ta-item:nth-child(3)")
	WebElement selectcontry;
	@FindBy(css=".btnn.action__submit")
	WebElement submitlast;
	By productsBy=By.cssSelector(".mb-3");
	By addtocart = By.cssSelector(".btn.w-10.rounded");
	By toastMessage = By.cssSelector(".ng-animating");
	By cartcheckout = By.cssSelector(".cartSection h3");
	By checkoutbutton = By.cssSelector(".totalRow button");
	
	public List<WebElement> getProductList()
	
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	public WebElement getProductName(String productName)
	{
		for (WebElement product : products) {
            String productName1 = product.findElement(By.cssSelector("b")).getText();
            if (productName1.equalsIgnoreCase(productName)) {
                return product;
               
            }
        }
		return null;	
	}
	public void addToCart(String productName) {
	    WebElement prod = getProductName(productName);
	    if (prod != null) {
	        System.out.println("Adding product: " + productName);
	        prod.findElement(addtocart).click();
	        waitForElementToAppear(toastMessage);
	        waitForElementToDisappear(spinner);
	    } else {
	        System.out.println("Product not found: " + productName);
	        throw new RuntimeException("Product not found: " + productName);
	    }
	}
	public boolean isProductInCart(String productName) {
		checkout.click();
	    waitForElementToAppear(cartcheckout);
	    List<WebElement> products = cartproducts;
	    boolean match = products.stream()
	        .anyMatch(product -> product.getText().equalsIgnoreCase(productName));
	    return match;
	}
	public void checkOutButton(String contry)
	{
		country.sendKeys(contry);
		selectcontry.click();
		submitlast.click();
		
		
		
	}

	
	
	
	
	
	
	
}