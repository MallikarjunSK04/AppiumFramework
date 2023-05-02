package mallikarjun.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mallikarjun.pageObject.android.CartPage;
import mallikarjun.pageObject.android.ProductCatlogPage;
import mallikarjun.testUtils.AndroidBaseTest;

public class Ecommerce_tc4_Hybrid extends AndroidBaseTest {

	@Test(dataProvider = "getData",groups= {"smoke"})
	public void fillForm(HashMap<String,String> input) throws InterruptedException  {
		
		formPage.setNameField(input.get("name"));
		formPage.setCountry(input.get("country"));
		formPage.setGender(input.get("gender"));
		ProductCatlogPage productCatlogPage = formPage.submitForm();
		productCatlogPage.addItemToCartByIndex();
		productCatlogPage.addItemToCartByIndex();
		CartPage cartPage = productCatlogPage.goToCart();
		Thread.sleep(2000);
		double totalPrice = cartPage.getTotalProductPrice();
		double disTotalAmount = cartPage.getDisplayedAmount();
		Assert.assertEquals(totalPrice, disTotalAmount);
		String alertText = cartPage.acceptTermCondition();
		Assert.assertEquals(alertText, "Terms Of Conditions");
		cartPage.submitOrder();
		

		
	}
	@BeforeMethod(alwaysRun=true)
	public void preSetUp() {
	formPage.setActivity();
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		//C:\Users\Asus\eclipse-workspace\AppiumFrameWorkDesign\src\test\java\testData\ecommerce.json
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir") +"\\src\\test\\java\\mallikarjun\\testData\\ecommerce.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
