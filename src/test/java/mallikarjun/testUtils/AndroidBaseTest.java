package mallikarjun.testUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import mallikarjun.pageObject.android.FormPage;
import mallikarjun.utils.AppiumUtils;

public class AndroidBaseTest extends AppiumUtils{

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;

	@BeforeClass(alwaysRun=true)
	public void configureAppium() throws IOException {

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\mallikarjun\\resource\\data.properties");
		Properties prop =new Properties();
		prop.load(fis);
		String ipAddress_prop = prop.getProperty("ipAddress");
		String ipAddress_mvn = System.getProperty("ipAddress");
		String ipAddress = ipAddress_mvn!=null ? ipAddress_mvn : ipAddress_prop;
		
		if(ipAddress_mvn!=null) {
			System.out.println("I'm maven");
		}else {
			System.out.println("I'm prop");
		}
		
		
		String port = prop.getProperty("port");
		
		
		
		// to start appium server
		service = startAppiumServer(ipAddress,Integer.parseInt(port));

		// To pass 2nd argument in AndroidDriver which is capabilities
		// we have to create UiAutomator2Options object and pass capabilities

		UiAutomator2Options options = new UiAutomator2Options();
		// which device
		options.setDeviceName(prop.getProperty("androidDeviceName"));
		options.setAutomationName("UIAutomator2");
		options.setPlatformName("ANDROID");

		// which app
		options.setApp(System.getProperty("user.dir")+ "\\src\\test\\java\\mallikarjun\\testResource\\General-Store.apk");
		options.setChromedriverExecutable(
				System.getProperty("user.dir") + "\\src\\test\\java\\mallikarjun\\testResource\\chromedriver.exe");

		// create AndroidDriver object
		//we can URL from service as shown below (little optimized) 
//		driver = new AndroidDriver(service.getUrl(), options);
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);

	}



	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}
