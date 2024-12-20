package shankarraibagi.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shankarraibagi.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	WebDriver driver;
	
	@FindBy(xpath = "//li /button[.='Checkout']")
	WebElement checkOutButton;
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productListInOrder;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public Boolean verifyOrderDisplay(String productName) {
		Boolean match =  productListInOrder.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
		return match;
	}
}
