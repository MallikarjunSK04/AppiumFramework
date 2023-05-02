package mallikarjun.pageObject.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mallikarjun.utils.AndroidActions;

public class FormPage extends AndroidActions{
	
	public AndroidDriver driver;
	
	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(this.driver),this);
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private WebElement femaleOption;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
	private WebElement maleOption;
	
	
	@AndroidFindBy(className ="android.widget.Spinner")
	private WebElement countrySelection;
	
	@AndroidFindBy(id="android:id/text1")
	private WebElement countries;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement submitButton;
	
	

	public void setNameField(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}
	
	public void setGender(String gender) {
		
		if(gender.contains("female")) {
			femaleOption.click();
		}else {
			maleOption.click();
		}
	}
	
	
	
	public void setCountry(String countryName){
		
	   countrySelection.click();
	//	driver.findElement(By.id("android:id/text1")).click();
		scrollGestureToText(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
	}

	public ProductCatlogPage submitForm() {
		submitButton.click();
		return new ProductCatlogPage(driver);
	}
	
	public void setActivity() {
		Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.SplashActivity");
		driver.startActivity(activity);
	}
}
