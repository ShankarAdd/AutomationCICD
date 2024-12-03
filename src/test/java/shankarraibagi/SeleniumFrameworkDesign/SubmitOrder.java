package shankarraibagi.SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import shankarraibagi.PageObjects.CartPage;
import shankarraibagi.PageObjects.CheckOutPage;
import shankarraibagi.PageObjects.ConfirmationPage;
import shankarraibagi.PageObjects.LandingPage;
import shankarraibagi.PageObjects.OrderPage;
import shankarraibagi.PageObjects.ProductCatalog;
import shankarraibagi.SeleniumFramework.TestComponents.BaseTest;

public class SubmitOrder  extends BaseTest{
	
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider = "getData",groups = "Purchase")
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

		String country = "India";

		ProductCatalog prod = page.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = prod.getListOfProducts();

		prod.addToCart(input.get("product"));

		CartPage cartPage = prod.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("product"));

		Assert.assertTrue(match);

		Thread.sleep(4000);

		CheckOutPage checkOut = cartPage.checkOutBut();

		checkOut.selectCountry(country);

		ConfirmationPage confirmMessage = checkOut.submit();

		String message = confirmMessage.getConfirmationMessage();

		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
	}
	
	@Test(dependsOnMethods = "submitOrder")
	public void orderHistoryTest() {
		ProductCatalog prod = page.loginApplication("shankarnrraibagi@gmail.com", "Shankar@007");
		OrderPage orderPage = prod.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		/*
		 * HashMap<String, String> map= new HashMap<String, String>();
		 * map.put("email","shankarnrraibagi@gmail.com");
		 * map.put("password","Shankar@007"); map.put("product","ZARA COAT 3");
		 * 
		 * HashMap<String, String> map1= new HashMap<String, String>();
		 * map.put("email","anshika@gmail.com"); map.put("password","Iamking@007");
		 * map.put("product","ADIDAS ORIGINAL");
		 */		
		List<HashMap<String, String>> data=  getDataReader(System.getProperty("user.dir")+"\\src\\test\\java\\shankarraibagi\\SeleniumFrameworkDesign\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}};
	}
}
