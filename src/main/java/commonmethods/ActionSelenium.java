package commonmethods;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class ActionSelenium {
	final static int WAITING_TIME = 30;
	static String mainwindow;

	public static void OpenBrowser(WebDriver driver, String url) {
		driver.get(url);
	}

	public static void Type(WebDriver driver, String value, By locator) {
		waitForElement(locator, driver);
		driver.findElement(locator).sendKeys(value);
	}

	public static void click(WebDriver driver, By locator) {
		waitForElement(locator, driver);
		driver.findElement(locator).click();
	}

	public static int validateResult(WebDriver driver, By locator) {
		waitForElement(locator, driver);
		List<WebElement> list = driver.findElements(locator);
		return list.size();

	}

	public static void waitForElement(final By by, WebDriver driver) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(WAITING_TIME, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.ignoring(ElementNotVisibleException.class).ignoring(TimeoutException.class)
				.ignoring(StaleElementReferenceException.class);
		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}
		});
	}

	public static void waitForStalenessOfElement(final By by, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, WAITING_TIME);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));

	}

	public static void waitForVisibleElement(final By by, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, WAITING_TIME);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public static void waitForClickableElement(final By by, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, WAITING_TIME);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public static void waitForElementDisappear(final By by, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, WAITING_TIME);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public static boolean waitUtilAtNextPage(String titlePage, WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, WAITING_TIME);
			wait.ignoring(TimeoutException.class).until(ExpectedConditions.titleContains(titlePage));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public static void switchToWindow(WebDriver driver, String window) throws NoSuchElementException {
		Set<String> set = driver.getWindowHandles();
		LinkedHashMap<String, String> tabs = new LinkedHashMap<String, String>();
		Iterator<String> itr = set.iterator();
		int i = 1;
		while (itr.hasNext()) {
			String child = itr.next();
			if (tabs.isEmpty()) {
				tabs.putIfAbsent("window0", mainwindow);
			} else if (!tabs.containsValue(child)) {
				tabs.putIfAbsent("window" + i, child);
				i++;
			}

		}
		if (!mainwindow.equalsIgnoreCase(tabs.get(window))) {
			driver.switchTo().window(tabs.get(window));
		}

	}

	public static void switchToMainWindow(WebDriver driver) {
		driver.switchTo().window(mainwindow);

	}

	public static void setMainWindow(String windowHandle) {
		mainwindow = windowHandle;

	}

	public static void openNewTab(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.open()");
	}

	public static String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public static void closeWebDriver(WebDriver driver) {
		driver.close();
	}

}
