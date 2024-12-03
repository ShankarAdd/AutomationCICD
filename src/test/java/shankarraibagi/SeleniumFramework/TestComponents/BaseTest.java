package shankarraibagi.SeleniumFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import shankarraibagi.PageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	
	public LandingPage page;

	public WebDriver initializer() throws IOException {

		Properties properties = new Properties();
		 
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\shankarraibagi\\resources\\GlobalData.properties");
		
		properties.load(fs);
		
		String browserName =System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");
//		 properties.getProperty("browser");


		if (browserName.contains("chrome")) {
			ChromeOptions  options = new ChromeOptions();
			
			WebDriverManager.chromedriver().setup();
			
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
		}
		else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} 
		else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().window().maximize();
		return driver;
	}
	public List<HashMap<String, String>> getDataReader(String filePath) throws IOException {
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//string to hashmap(jackson databind) which helps to convert string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}
	
	public String getScreenShots(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user,dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user,dir")+"//reports//"+testCaseName+".png";
	}
	@BeforeMethod(alwaysRun = true)
	public LandingPage lunchApplication() throws IOException {
		driver = initializer();
		
		page = new LandingPage(driver);

		page.landingPageUsingUrl();
		
		return page;
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	
}
