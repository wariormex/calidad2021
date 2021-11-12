package com.anahuac.calidad.funcionales;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import java.io.File;

public class FacebookTest {
	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  JavascriptExecutor js;
	  @Before
	  public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    baseUrl = "https://www.facebook.com/";
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    js = (JavascriptExecutor) driver;
	  }

	  @Test
	  public void testLogInIncorrecto() throws Exception {
	    driver.get("https://www.facebook.com/");
	    //driver.findElement(By.name("q")).click();
	    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div/div/div[2]/div/div[1]/form/div[1]/div[1]/input")).clear();
	    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div/div/div[2]/div/div[1]/form/div[1]/div[1]/input")).sendKeys("prueba@gmail.com");
	    
	    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div/div/div[2]/div/div[1]/form/div[1]/div[2]/div/input")).clear();
	    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div/div/div[2]/div/div[1]/form/div[1]/div[2]/div/input")).sendKeys("test");
	    
	    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div/div/div[2]/div/div[1]/form/div[2]/button")).sendKeys(Keys.ENTER);

	    driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[2]/div[2]")).click();
	    String tag = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div[2]/div[2]/form/div/div[2]/div[2]")).getText();
	    assertThat("The password you’ve entered is incorrect. Forgot Password?", is(tag));
	  }
	  
	  @Test
	  public void testLogInCorrecto() throws Exception {
	    driver.get("https://www.facebook.com/");
	    //driver.findElement(By.name("q")).click();
	    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div/div/div[2]/div/div[1]/form/div[1]/div[1]/input")).clear();
	    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div/div/div[2]/div/div[1]/form/div[1]/div[1]/input")).sendKeys("-");
	    
	    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div/div/div[2]/div/div[1]/form/div[1]/div[2]/div/input")).clear();
	    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div/div/div[2]/div/div[1]/form/div[1]/div[2]/div/input")).sendKeys("-");
	    
	    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div/div/div[2]/div/div[1]/form/div[2]/button")).sendKeys(Keys.ENTER);

	    driver.findElement(By.xpath("//*[@id=\"loginform\"]/div[2]/div[2]")).click();
	    String tag = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[3]/div/div/div[1]/div[1]/div/div[1]/div/div/div[1]/div/div/div[1]/ul/li/div/a/div[1]/div[2]/div/div/div/div/span/span")).getText();
	    assertThat("Fausto Guerrero Burguete", is(tag));
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
}
