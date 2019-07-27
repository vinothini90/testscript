package test.PageObjectModel;

import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import PageActions.LandingPageActions;
import PageActions.ProductDetailPage;
import PageActions.ProductResultPage;
import PageActions.ShoppingCartPage;
import commonmethods.Driverinit;
import commonmethods.ReadExcel;

public class TestScriptPOM {

	WebDriver driver;
	String url = "https://www.amazon.in/";
	ArrayList<String> value;

	@BeforeTest
	@Parameters("browser")
	public void init(@Optional("chrome") String Browser) throws IOException {
		driver = Driverinit.init(Browser);
		value = ReadExcel.readData("src/test/java/resources", "Test.xlsx", "Mobile");
	}

	@Test
	public void searchProduct() {
		for (String input : value) {
			LandingPageActions.openLandingPage(driver, url);
			LandingPageActions.verifyPageTitle(driver,
					"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
			LandingPageActions.searchItem(driver, input);
			ProductResultPage.verifyPageTitle(driver, "Amazon.in: "+input);
			ProductResultPage.isSearchedProductPresent(driver, input);
			ProductResultPage.clickonFirstResult(driver, input);
			ProductDetailPage.clickOnAddToCartButton(driver);
			ShoppingCartPage.clickOnProceedToBuyButton(driver);
			ShoppingCartPage.closeCurrentTab(driver);
		}

	}

	@AfterTest
	public void shutDown() {
		driver.quit();
	}

}
