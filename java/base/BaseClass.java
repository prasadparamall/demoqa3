package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;



import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.Paths;

public class BaseClass {
	
	public static WebDriver driver;
	public static FileInputStream fis;
	public static Properties pro;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public ExcelReader demoQA_excel1 = new ExcelReader(Paths.excel);

	
	
	
	
	public static void setup() throws Exception {
		
		fis = new FileInputStream(Paths.config);
		pro = new Properties();
		pro.load(fis);
		
		if(pro.getProperty("browser").equals("chrome")) {
			
			WebDriverManager.chromedriver().setup();

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");
			options.addArguments("--remote-allow-origins=*");

			driver = new ChromeDriver(options);
			log.info("launching chrome broswer");
			
		
		} else if(pro.getProperty("chrome").equals("chrome")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if(pro.getProperty("chrome").equals("chrome")){
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
	driver.manage().window().maximize();
	driver.get(pro.getProperty("url"));
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}

}
