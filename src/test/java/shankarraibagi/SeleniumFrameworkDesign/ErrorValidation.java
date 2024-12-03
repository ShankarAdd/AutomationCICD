package shankarraibagi.SeleniumFrameworkDesign;

import org.testng.Assert;
import org.testng.annotations.Test;

import shankarraibagi.SeleniumFramework.TestComponents.BaseTest;
import shankarraibagi.SeleniumFramework.TestComponents.Retry;

public class ErrorValidation extends BaseTest {
	
	@Test(groups = "ErrorHandling",retryAnalyzer = Retry.class)
	public void loginErrorValidation() {

		page.loginApplication("shankarnrraiagi@gmail.com", "Shankar007");
		
		Assert.assertEquals("Incorrect email or password.", page.getErrorMessage()); 
	}
}
