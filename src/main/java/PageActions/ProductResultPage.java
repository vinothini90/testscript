package PageActions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import commonmethods.ActionSelenium;

public class ProductResultPage {
	static List<WebElement> list;

	public static void isSearchedProductPresent(WebDriver driver, String text) {
		By results = By.xpath("//*[contains(text(),'" + text + "')]");
		list = driver.findElements(results);
		Assert.assertTrue(list.size() > 0);
	}

	public static void clickonFirstResult(WebDriver driver, String text) {
		By firstProduct = By.xpath("//span[contains(text(),'" + text + "')][contains(@class,'a-size-medium')]");
		ActionSelenium.click(driver, firstProduct);
		ActionSelenium.switchToWindow(driver, "window1");
	}

	public static void verifyPageTitle(WebDriver driver, String text) {
		ActionSelenium.waitUtilAtNextPage(text, driver);
		Assert.assertTrue(ActionSelenium.getPageTitle(driver).equals(text));
	}

}
