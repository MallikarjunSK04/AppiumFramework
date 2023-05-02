package mallikarjun.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumUtils {
	public AppiumDriverLocalService service;

//	public AppiumDriver driver;
//
//	public AppiumUtils(AppiumDriver driver) {
//		this.driver = driver;
//	}

	public double totalAmountCal(String priceText) {

		double price = Double.parseDouble(priceText.substring(1));
		return price;
	}
	
	public void waitTillElementAppearsOfAttributeContains(WebElement ele,String attribute,String value,AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeContains(ele, attribute, value));
	}
	
	public List<HashMap<String, String>> getJsonData(String jsonPath) throws IOException {
		
		File scr = new File(jsonPath);
		String jsonContent = FileUtils.readFileToString(scr, StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
		
	}
	
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
		service = new AppiumServiceBuilder()
				.withAppiumJS(
						new File("C:\\Users\\Asus\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(ipAddress).usingPort(port).build();
		// service.start();
		return service;
	}
	
	public String getScreenshotPath(String testName,AppiumDriver driver) throws IOException {
		File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir")+"//report//"+testName+".png";
		FileUtils.copyFile(scr, new File(destinationPath));
		return destinationPath;
	}

}
