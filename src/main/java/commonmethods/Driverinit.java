package commonmethods;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;

public class Driverinit {

	public static WebDriver init(String Browser) {
		WebDriver driver = null;
		if (Browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/test/java/drivers/chromedriver_Win.exe");
			ChromeOptions chromeoptions = new ChromeOptions();
			// chromeoptions.setBinary("C:\\Program Files
			// (x86)\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
			chromeoptions.setPageLoadStrategy(PageLoadStrategy.NONE);
			chromeoptions.addArguments("start-maximized");
			chromeoptions.addArguments("enable-automation");
			// options.addArguments("--headless");
			chromeoptions.addArguments("--no-sandbox");
			chromeoptions.addArguments("--disable-infobars");
			chromeoptions.addArguments("--disable-dev-shm-usage");
			chromeoptions.addArguments("--disable-browser-side-navigation");
			chromeoptions.addArguments("--disable-gpu");
			chromeoptions.setAcceptInsecureCerts(true);
			driver = new ChromeDriver(chromeoptions);
			return driver;
		} else if (Browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src/test/java/drivers/geckodriver_Win.exe");
			ProfilesIni prof = new ProfilesIni();
			FirefoxProfile ffProfile = prof.getProfile("default");
			FirefoxOptions ffoptions = new FirefoxOptions();
			ffoptions.setProfile(ffProfile);
			ffoptions.setAcceptInsecureCerts(true);
			ffoptions.setCapability("marionette", true);
			ffoptions.setPageLoadStrategy(PageLoadStrategy.NONE);
			driver = new FirefoxDriver(ffoptions);
			return driver;
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;

	}

}
