package shankarraibagi.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import shankarraibagi.PageObjects.LandingPage;

public class StandAloneTest {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();

		String productName = "ZARA COAT 3";

		WebDriver driver = new EdgeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		LandingPage page = new LandingPage(driver);

		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("shankarnrraibagi@gmail.com");

		driver.findElement(By.id("userPassword")).sendKeys("Shankar@007");

		driver.findElement(By.id("login")).click();

		WebDriverWait waitingByExplicit = new WebDriverWait(driver, Duration.ofSeconds(8));

		waitingByExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement product1 = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		product1.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		waitingByExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

		waitingByExplicit.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container"))));

		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		boolean matching = cartProducts.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(matching);

		JavascriptExecutor js = (JavascriptExecutor)driver;

		js.executeScript("window.scrollBy(0,1200)");

		waitingByExplicit.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));
		// sometimes it is working and sometimes it is not working due to of chrome
		// versions
		driver.findElement(By.xpath("//li /button[.='Checkout']")).click();

		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("India");

		waitingByExplicit.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Select Country']")));

		List<WebElement> list = driver.findElements(By.cssSelector("button[class*='ta-item']"));

		for (WebElement webElement : list) {
			if (webElement.getText().equalsIgnoreCase("India")) {
				webElement.click();
				break;

			}
		}
		
		js.executeScript("window.scrollBy(0,4000)");
		
		waitingByExplicit.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[class*='btnn action__submit ng-star-inserted']")));
		
		driver.findElement(By.cssSelector(".btnn.action__submit")).click();
		
		String message = driver.findElement(By.className("hero-primary")).getText();
		
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
	}
}
