package SeliniumMaven.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SeliniumMaven.pageobjects.LandingPage;

public class AddtoCart {
	public static void main(String[] args)
	{
		String productName1 = "ADIDAS ORIGINAL";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		//PageObjects pageobject = new PageObjects(driver);
		//LandingPage pageObjects = new LandingPage(driver);
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client/");
        driver.findElement(By.id(("userEmail"))).sendKeys("sairampadala@gmail.com");
        driver.findElement(By.id(("userPassword"))).sendKeys("Sairam@123");
        driver.findElement(By.id(("login"))).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        
        //stream code
       /* List<WebElement> products= driver.findElements( By.cssSelector(".mb-3"));
        WebElement prod = products.stream().filter(product-> 
       product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);*/
        
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = null;

        for (WebElement product : products) {
            String productName = product.findElement(By.cssSelector("b")).getText();
            if (productName.equals("ADIDAS ORIGINAL")) {
                prod = product;
                break;
            }
        }

        prod.findElement(By.cssSelector(".btn.w-10.rounded")).click();
        System.out.println("Clicked checkout button.");
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating"))); another way
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); 
        
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        System.out.println("Clicked checkout button.");
        
      
        //wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".subtotal.cf.ng-star-inserted button")));
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
        Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName1));
        Assert.assertTrue(match);
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));
        System.out.println("Attempting to click checkout button...");
        checkoutButton.click();
        System.out.println("Clicked checkout button.");
        driver.findElement(By.xpath("//*[@placeholder='Select Country']")).sendKeys("ind");
        driver.findElement(By.cssSelector(".ta-item:nth-child(3)")).click();
        driver.findElement(By.cssSelector(".btnn.action__submit")).click();
        String result = driver.findElement(By.cssSelector(".hero-primary")).getText();
        System.out.println(result);
        Assert.assertTrue(result.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();
        
        

        
        
        


        
        
        
	}

}
