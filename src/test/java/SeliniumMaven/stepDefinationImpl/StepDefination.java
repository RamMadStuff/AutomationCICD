package SeliniumMaven.stepDefinationImpl;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeliniumMaven.TestComponents.BaseTest;
import SeliniumMaven.pageobjects.CartPage;
import SeliniumMaven.pageobjects.CheckOutPage;
import SeliniumMaven.pageobjects.ConfirmationPage;
import SeliniumMaven.pageobjects.LandingPage;
import SeliniumMaven.pageobjects.ProductCatalogue;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefination extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce website")
	public void i_landed_on_Ecommerce_website() throws IOException
	{
		landingPage=launchApplication();
		
	}
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username , String password )
	
	{
		productCatalogue = landingPage.LoginApplication(username,password);
	}
	@When ("^I add the product (.+) from cart$")
	public void  i_add_the_product_from_cart(String productName)
	{
		List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addToCart(productName);
        System.out.println("added to cart");
	}
	@When ("^Checkout (.+) and submit the order$")  // try @And also
	public void Checkout_and_submit_the_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCartPage();
        System.out.println("git ot cart page");
        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
       CheckOutPage checkOutPage =cartPage.checkOut();
       confirmationPage=checkOutPage.SelectCountry("IND");
	}
	
	@Then ("{string} message is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page(String string)
	{
		String result = confirmationPage.conFirmation();
        System.out.println(result); 
        Assert.assertEquals(result,string);
        driver.close();
	}
	@Then("{string} message is displayed")
	public void message_is_displayed(String Error) {
		
		Assert.assertEquals(Error, landingPage.getErrorMessage());
		driver.close();
	}
	
	
	
	
	

}
