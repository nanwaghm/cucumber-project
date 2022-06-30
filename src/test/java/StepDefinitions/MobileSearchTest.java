package StepDefinitions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MobileSearchTest {

	public static WebDriver driver;
	
	
	@Given("user navigates to amazon and select mobile cataegory")
	public void user_navigates_to_amazon_and_select_mobile_cataegory() {
	
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\nandini.waghmare\\Downloads\\chromedriver.exe");
		this.driver = new ChromeDriver(); 
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		driver.manage().window().maximize();
		
		driver.get("https://www.amazon.in/");
		System.out.println(driver.getTitle());
		
		WebElement catgmobile = driver.findElement(By.xpath("//a[contains(text(),'Mobiles')]"));
		catgmobile.click();
		System.out.println("Navigated to mobile cataegory succesfully");
	}

	@Given("user sorts search product by high to low prices")
	public void user_sorts_search_product_by_high_to_low_prices() {
		WebElement sortopt = driver.findElement(By.xpath("//span[contains(text(),'Featured')]"));
		sortopt.click();
		System.out.println("Clicked to select Sort by option");
		
		
		WebElement sort = driver.findElement(By.xpath("//a[contains(text(),'Price: High to Low')]"));
		sort.click();
		System.out.println("Price: High to Low option is selected");
	}

	@Given("after selecting brand and entering range clicks Go button")
	public void after_selecting_brand_and_entering_range_clicks_go_button() {
		WebElement brand = driver.findElement(By.xpath("//i[@class='a-icon a-icon-checkbox']/following::span[contains(text(),'OnePlus')]"));
		brand.click();
		System.out.println("Selected brand");

		WebElement minprice = driver.findElement(By.xpath("//input[@id='low-price']"));
		minprice.sendKeys("20000");
		System.out.println("Entered Min Value");
		
		WebElement maxprice = driver.findElement(By.xpath("//input[@id='high-price']"));
		maxprice.sendKeys("60000");
		System.out.println("Entered Max Value");
		
		WebElement btngo = driver.findElement(By.xpath("(//input[@type='submit'])[2]"));
		btngo.click();
		System.out.println("Clicked Go");
	}

	@Then("verify product name and if sort works in decreasing mode")
	public void verify_product_name_and_if_sort_works_in_decreasing_mode() {
		
		By mySelector = By.cssSelector("div.s-title-instructions-style span");
		List<WebElement> myElements = driver.findElements(mySelector);
		for(int i=0; i<4; i++) {
		  System.out.println(myElements.get(i).getText());
		  Assert.assertTrue(myElements.get(i).getText().contains("Results"),"First search result for selected mobile brand is not as expected");
		}
		
		By price = By.cssSelector("span.a-price-whole");
		List<WebElement> mobprice = driver.findElements(price);
		for(int i=0; i<4; i++) {
		  System.out.println(mobprice.get(i).getText());
		  mobprice.get(i).getText().replace(",", "");
		  Assert.assertEquals(mobprice.get(i).getText().replace(",", ""), (mobprice.get(i).getText().replace(",", "")));
		  driver.close();
		  driver.quit();
		}	
	}
}


