package SeliniumMaven.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeliniumMaven.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDrivers() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\SeliniumMaven\\resources\\GlobalData.properties");
		
		prop.load(fis);
		String browseName=System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		//String browseName = prop.getProperty("browser");
		if(browseName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			if(browseName.contains("headless"))
			{
			options.addArguments("headless");
			}
		driver = new ChromeDriver(options);
		
		}
		else if (browseName.equalsIgnoreCase("edge"))
				{
			System.setProperty(
		            "webdriver.edge.driver",
		            "C:\\Users\\saira\\OneDrive\\Desktop\\Selenium\\msedgedriver.exe");
			driver = new EdgeDriver();
				}
		else if(browseName.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}

	

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));																
		driver.manage().window().maximize();
		return driver;
		
		
}
	public  List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException

	{
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//read string hashmap jacksondatbind
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
	}
	public String getScreenShot(String testCaseName) throws IOException
	{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file = new File(System.getProperty("user.dir")+"//reports//"+ testCaseName +".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir")+"//reports//"+ testCaseName +".png";
			
			
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver= initializeDrivers();
		landingPage = new LandingPage(driver);
        landingPage .goTo(); 
        return landingPage;
	}
	@AfterMethod(alwaysRun=true)
	public void closeTheBrowser()
	{
		driver.close();
	}
	}
