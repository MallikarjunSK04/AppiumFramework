package mallikarjun.pageObject.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mallikarjun.utils.AndroidActions;

public class CartPage extends AndroidActions{

	public AndroidDriver driver;
	
	public CartPage(AndroidDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver),this);
	}
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrices;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement displayedAmount;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement longPress;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/alertTitle")
	private WebElement term;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement okButton;
	
	@AndroidFindBy(className ="android.widget.CheckBox")
	private WebElement checkbox;
	
	@AndroidFindBy(id ="com.androidsample.generalstore:id/btnProceed")
	private WebElement submit;
	
	@AndroidFindBy(id ="com.androidsample.generalstore:id/toolbar_title")
	private WebElement cartTitel;
	
	public double getTotalProductPrice() {
		int count = productPrices.size();
		double totalPrice = 0;
		for (int i = 0; i < count; i++) {
			String priceText = productPrices.get(i).getText();
			double price = Double.parseDouble(priceText.substring(1));
			totalPrice = totalPrice + price;
		}
		return totalPrice;
		
	}
	
	public double getDisplayedAmount() {
		String amount = displayedAmount.getText();
		double k = totalAmountCal(amount.substring(1));
		waitTillElementAppearsOfAttributeContains(cartTitel, "text", "Cart",driver);
		return k;
	}
	

	public String acceptTermCondition() {
		longClick(longPress);
		String text = term.getText();
		okButton.click();
		return text;
		
	}
	
	public void submitOrder() {
		checkbox.click();
		submit.click();
	}
}
