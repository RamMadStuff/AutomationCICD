	package SeliniumMaven.Tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeliniumMaven.TestComponents.BaseTest;
import SeliniumMaven.pageobjects.CartPage;
import SeliniumMaven.pageobjects.CheckOutPage;
import SeliniumMaven.pageobjects.ConfirmationPage;
import SeliniumMaven.pageobjects.LandingPage;
import SeliniumMaven.pageobjects.OrderPage;
import SeliniumMaven.pageobjects.ProductCatalogue;



public class SubmitOrderTest extends BaseTest {
	String lastProductOrdered;
	@Test(dataProvider="getData")
	public void placeOrderTest(HashMap<String,String>input) throws IOException
	{
		lastProductOrdered = input.get("productName");
		System.out.println("Trying to add product: " + input.get("productName"));
		
		ProductCatalogue productCatalogue = landingPage.LoginApplication(input.get("email"), input.get("password"));
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addToCart(input.get("productName"));
        System.out.println("added to cart");	
        CartPage cartPage = productCatalogue.goToCartPage();
        System.out.println("git ot cart page");
        Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);
       CheckOutPage checkOutPage =cartPage.checkOut();
       ConfirmationPage confirmationPage=checkOutPage.SelectCountry("IND");
       	String result = confirmationPage.conFirmation();
        System.out.println(result);
        Assert.assertEquals(result,"THANKYOU FOR THE ORDER.");
        
	}
	@Test(dependsOnMethods={"placeOrderTest"})
	public void CheckOrdersList()
	{
		ProductCatalogue productCatalogue = landingPage.LoginApplication("padala@gmail.com", "Padala@123");
		OrderPage orderpage = productCatalogue.goToMyOrders();
		Assert.assertTrue(orderpage.verifyOrderDisplay(lastProductOrdered));
		
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator +
                "java" + File.separator + "SeliniumMaven" + File.separator + "data" + File.separator + "PurchaseOrder.json";
  
  System.out.println("Loading JSON from path: " + path);
  
  File file = new File(path);
  System.out.println("File exists: " + file.exists());

  List<HashMap<String,String>> data = getJsonDataToMap(path);

  return new Object[][] { { data.get(0) }, { data.get(1) } };
  /*HashMap<String,String>map= new HashMap<>();
	map.put("email","sairampadala@gmail.com" );
	map.put("password","Sairam@123" );
	map.put("productName","ADIDAS ORIGINAL");
	
	HashMap<String,String>map1= new HashMap<>();
	map1.put("email","padala@gmail.com" );
	map1.put("password","Padala@123" );
	map1.put("productName","ZARA COAT 3");
	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\SeliniumMaven\\data\\PurchaseOrder.json");
	return new Object[][] {{data.get(0)},
							{data.get(1)}};*/
		}
	

}
