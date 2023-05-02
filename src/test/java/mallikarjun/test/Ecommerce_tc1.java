package mallikarjun.test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import mallikarjun.testUtils.AndroidBaseTest;

public class Ecommerce_tc1 extends AndroidBaseTest {
	
	@BeforeMethod(alwaysRun=true)
	public void preSetUp() {
	formPage.setActivity();
	}

	@Test
	public void fillForm()  {
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Mallikarjun");
		driver.hideKeyboard();
		driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		

	
	}
	
	@Test
	public void toastError() {
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String resText = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		Assert.assertEquals(resText, "Please enter your name");
		}

}
