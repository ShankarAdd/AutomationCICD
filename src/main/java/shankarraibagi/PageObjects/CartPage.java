package shankarraibagi.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shankarraibagi.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	
	@FindBy(xpath = "//li /button[.='Checkout']")
	WebElement checkOutButton;
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> productTitles;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public Boolean verifyProductDisplay(String productName) {
		Boolean match =  productTitles.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckOutPage checkOutBut(){
		checkOutButton.click();
		return new CheckOutPage(driver);
	}
}
