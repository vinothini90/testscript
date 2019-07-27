package PageActions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import commonmethods.ActionSelenium;

public class ProductDetailPage {
	static By addtocartbutton = By.xpath("//input[@title='Add to Shopping Cart']");

	public static void clickOnAddToCartButton(WebDriver driver) {
		ActionSelenium.click(driver, addtocartbutton);
	}

}
