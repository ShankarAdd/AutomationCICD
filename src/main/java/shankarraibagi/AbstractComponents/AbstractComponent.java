package shankarraibagi.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import shankarraibagi.PageObjects.CartPage;
import shankarraibagi.PageObjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
	@FindBy(css = "[routerlink='/dashboard/cart']")
	WebElement cartButton;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;
	
	public void waitForElementToLocate(By finBy) {
		WebDriverWait waitingByExplicit = new WebDriverWait(driver, Duration.ofSeconds(5));

		waitingByExplicit.until(ExpectedConditions.visibilityOfElementLocated(finBy));
	}
	
	public void waitForElementToDisapper(WebElement element) throws InterruptedException {
//		WebDriverWait waitingByExplicit = new WebDriverWait(driver, Duration.ofSeconds(2));
//		waitingByExplicit.until(ExpectedConditions.invisibilityOf(element));
		Thread.sleep(1000);
	}
	public void waitForElementToLocate(WebElement findBy) {
		WebDriverWait waitingByExplicit = new WebDriverWait(driver, Duration.ofSeconds(5));

		waitingByExplicit.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public CartPage goToCartPage() {
		cartButton.click();
		CartPage cartPage= new CartPage(driver);
		return cartPage;
	}
	public OrderPage goToOrdersPage() {
		orderHeader.click();
		OrderPage orderPage= new OrderPage(driver);
		return orderPage;
	}
}
