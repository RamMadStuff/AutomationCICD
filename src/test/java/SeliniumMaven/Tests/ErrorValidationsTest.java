	package SeliniumMaven.Tests;
	


import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeliniumMaven.TestComponents.BaseTest;
import SeliniumMaven.TestComponents.Retry;



public class ErrorValidationsTest extends BaseTest {
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException
	{
		System.out.println(">>> Running loginErrorValidation <<<");
		String productName1 = "ADIDAS ORIGINAL";
		landingPage.LoginApplication("sairampadala@gmail.com", "Sairam@123");
		//Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		
		
        
	}

}
