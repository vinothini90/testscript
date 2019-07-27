package PageActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import commonmethods.ActionSelenium;

public class LandingPageActions {
	static By searchBoxLandingPage = By.xpath("//input[@id='twotabsearchtextbox']");
	static By searchButton = By.xpath("//input[@value='Go']");

	public static void openLandingPage(WebDriver driver, String url) {
		ActionSelenium.OpenBrowser(driver, url);
		ActionSelenium.setMainWindow(driver.getWindowHandle());
	}

	public static void searchItem(WebDriver driver, String text) {
		ActionSelenium.Type(driver, text, searchBoxLandingPage);
		ActionSelenium.click(driver, searchButton);
	}

	public static void verifyPageTitle(WebDriver driver, String text) {
		ActionSelenium.waitUtilAtNextPage(text, driver);
		Assert.assertTrue(ActionSelenium.getPageTitle(driver).equals(text));
	}

}
