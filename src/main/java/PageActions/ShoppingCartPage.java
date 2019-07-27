package PageActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import commonmethods.ActionSelenium;

public class ShoppingCartPage {
	static By proceedtobuybutton = By.xpath("//*[contains(text(),'Proceed to Buy')]");

	public static void clickOnProceedToBuyButton(WebDriver driver) {
		ActionSelenium.click(driver, proceedtobuybutton);
	}

	public static void closeCurrentTab(WebDriver driver) {
		ActionSelenium.closeWebDriver(driver);
		ActionSelenium.switchToMainWindow(driver);
		
	}

}
