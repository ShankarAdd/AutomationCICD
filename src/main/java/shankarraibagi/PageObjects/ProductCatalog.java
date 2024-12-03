package shankarraibagi.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shankarraibagi.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent{

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	// It is same as below code
	@FindBy(css =".mb-3")
	List<WebElement> products;
	
	@FindBy(css =".ng-animating")
	WebElement spinner;
	
	By productsBy =By.cssSelector(".mb-3");
	By addToCart =By.cssSelector(".card-body button:last-of-type");
	By toastMessage =By.cssSelector("#toast-container");
	
	public List<WebElement> getListOfProducts() {
		waitForElementToLocate(productsBy);
		return products;
	}
	public WebElement getProductByName(String productName) {
		WebElement product1 = getListOfProducts().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return product1;
	}
	public void addToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToLocate(toastMessage);
		waitForElementToDisapper(spinner);
	}
	
}
