package shankarraibagi.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shankarraibagi.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	WebElement ele = driver.findElement(By.id("userName"));
	//It is same as below code
	@FindBy(id = "userEmail")
	WebElement email;
	
	@FindBy(id = "userPassword")
	WebElement password;
	
	@FindBy(id = "login")
	WebElement submitButtton;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalog loginApplication(String anythingFrom,String atRunTime) {
		email.sendKeys(anythingFrom);
		password.sendKeys(atRunTime);
		submitButtton.click();
		ProductCatalog prod = new ProductCatalog(driver);
		return prod;
	}
	public String getErrorMessage() {
		waitForElementToLocate(errorMessage);
		return errorMessage.getText();
	}
	public void landingPageUsingUrl() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
