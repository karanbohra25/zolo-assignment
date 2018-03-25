import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TestCase1 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C://Users//yashp//Desktop//Karan//chromedriver_win32//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://52.201.90.154:9002");
		driver.findElement(By.xpath("//*[@id='navbar']/ul/li[3]/a")).click();
		// Login to ZoloStays
		WebElement uID = driver
				.findElement(By
						.xpath("//*[@id='signin']/div/div[2]/div[2]/form/div[1]/input"));
		uID.sendKeys("7777777032");
		WebElement pwd = driver
				.findElement(By
						.xpath("//*[@id='signin']/div/div[2]/div[2]/form/div[2]/input"));
		pwd.sendKeys("123456");
		// As the DOM takes time to load, induce a mandatory wait and create an
		// Action class to move to the SignIn button and click
		Thread.sleep(3000);
		WebElement loginButton = driver
				.findElement(By
						.xpath("//*[@id='signin']/div/div[2]/div[2]/form/div[4]/div/input"));

		Actions action = new Actions(driver);
		action.click(loginButton).build().perform();

		// Search Electronic and select the location
		WebElement searchProperty = driver.findElement(By
				.xpath("//*[@id='searchBar']"));
		searchProperty.sendKeys("Electronic");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement option = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By
						.xpath("//*[@id='ui-id-1']/li[3]/div")));
		option.click();

		// Select Budget, Sharing and Type
		Select selectBudget = new Select(
				driver.findElement(By
						.xpath("//*[@id='content']/div/div[1]/div/form/div/div[1]/select")));
		selectBudget.selectByIndex(1);
		Select selectPref = new Select(
				driver.findElement(By
						.xpath("//*[@id='content']/div/div[1]/div/form/div/div[2]/select")));
		selectPref.selectByIndex(2);
		Select selectPGType = new Select(
				driver.findElement(By
						.xpath("//*[@id='content']/div/div[1]/div/form/div/div[3]/select")));
		selectPGType.selectByIndex(1);
		WebElement selectedPG = driver
				.findElement(By
						.xpath("//*[@id='583849856193f53870e51342']/a/div[2]/div[1]/h2"));
		action.moveToElement(selectedPG).click().build().perform();
		String pgName = "Zolo-Goodfellas";

		// Assert the correct PG is selected
		Assert.assertTrue(driver.getCurrentUrl().contains(pgName));

		// Buttons take longer to load/become active, hence use Action class to
		// locate buttons and perfrom click
		WebElement scheduleAVisit = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By
						.xpath("//*[@id='content']/div/div[3]/div[2]/div[1]/div[1]/a")));
		action.moveToElement(scheduleAVisit).click().build().perform();

		// Select date and time
		WebElement selectDate = driver
				.findElement(By
						.xpath("//*[@id='content']/div[3]/div[2]/div/div/div[2]/div/div[1]/div/form/div[1]/div/input"));
		selectDate.sendKeys("05");
		Select selecTime = new Select(
				driver.findElement(By
						.xpath("//*[@id='content']/div[3]/div[2]/div/div/div[2]/div/div[1]/div/form/div[2]/p[2]/select")));
		selecTime.selectByIndex(4);
		WebElement submit = driver
				.findElement(By
						.xpath("//*[@id='content']/div[3]/div[2]/div/div/div[2]/div/div[2]/div"));
		action.moveToElement(submit).click().build().perform();

		// Get success message
		String successMsg = driver.findElement(
				By.xpath("//*[contains(@id,'noty')]")).getText();
		System.out.println(successMsg);
		driver.findElement(
				By.xpath("//*[@id='header']/div[3]/a[2]/div[1]/span[1]"))
				.click();
		driver.findElement(By.xpath("//*[@id='header']/div[3]/div/ul/li[13]/a"))
				.click();
		driver.close();
	}

}
