package shankarnrraibagi.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import shankarraibagi.PageObjects.CartPage;
import shankarraibagi.PageObjects.CheckOutPage;
import shankarraibagi.PageObjects.ConfirmationPage;
import shankarraibagi.PageObjects.LandingPage;
import shankarraibagi.PageObjects.ProductCatalog;
import shankarraibagi.SeleniumFramework.TestComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalog prod;
	public ConfirmationPage confirmMessage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = lunchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String userName,String password){
		prod = page.loginApplication(userName, password);
	}
	@When("^I added product (.+) to Cart$")
	public void I_added_product_to_Cart(String productName) throws InterruptedException {
		List<WebElement> products = prod.getListOfProducts();

		prod.addToCart(productName);
	}
	@When("^checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) throws InterruptedException {
		CartPage cartPage = prod.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(productName);

		Assert.assertTrue(match);
		
		Thread.sleep(4000);

		CheckOutPage checkOut = cartPage.checkOutBut();

		checkOut.selectCountry("India");

		confirmMessage = checkOut.submit();
	}
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string) {
		String message = confirmMessage.getConfirmationMessage();

		Assert.assertTrue(message.equalsIgnoreCase(string));
		driver.close();
	}
	@Then("{string} message is displayed")
	public void error_message_is_displayed(String string) {
		Assert.assertEquals(string, page.getErrorMessage()); 
		driver.close();
	}
}
