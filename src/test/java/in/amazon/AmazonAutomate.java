package in.amazon;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonAutomate {

	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
		options.addArguments("disable-notifications");
		options.addArguments("disable-popups");
		options.addArguments("start-maximized");
		WebDriver driver = new EdgeDriver(options);
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebElement dropDown = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
		WebElement all = driver.findElement(By.xpath("//span[@id='nav-search-label-id']"));
		System.out.println(driver.findElement(By.xpath("//span[@id='nav-search-label-id']")).isDisplayed());
		if(all.isDisplayed()) {
			dropDown.click();
		}
		TakesScreenshot tk = (TakesScreenshot)driver;
		File src = tk.getScreenshotAs(OutputType.FILE);
		File trg = new File("C:\\Users\\srika\\eclipse-workspace\\AutomateAmazon\\target\\amazon.png");
		FileUtils.copyFile(src, trg);
		Select s = new Select(dropDown);
		List<WebElement> option = s.getOptions();
		for(WebElement x : option) {
			System.out.println(x.getText());
			String s1 = "Electronics";
			if(s1.contains(x.getText())){
				s.selectByVisibleText("Electronics");
				}
			}
		WebElement e = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		Actions d = new Actions(driver);
		d.sendKeys(e, "Mobile").perform();
		Robot r = new Robot();
		for(int i=0; i<=4; i++) {
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
			}
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		WebElement down = driver.findElement(By.xpath("//span[text()='Previous']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", down);
//		driver.findElement(By.xpath("(//div[@data-component-type='s-search-result'])[last()]")).click();
		driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[30]")).click();
		String pWin = driver.getWindowHandle();
		Set<String> allWin = driver.getWindowHandles();
		for(String x : allWin) {
			if(!x.equals(pWin)) {
				driver.switchTo().window(x);	
			}
		}
//		List<String> l = new ArrayList<>();
//		l.addAll(allWin);
//		String x = l.get(1);
//		driver.switchTo().window(x);
		Thread.sleep(3000);
		File sc = tk.getScreenshotAs(OutputType.FILE);
		File  tr = new File("C:\\Users\\srika\\eclipse-workspace\\AutomateAmazon\\target\\amazon1.png");
		FileUtils.copyFile(sc, tr);
		driver.findElement(By.xpath("//input[@id='buy-now-button']")).click();
		Thread.sleep(3000);
		File source = tk.getScreenshotAs(OutputType.FILE);
		File  target = new File("C:\\Users\\srika\\eclipse-workspace\\AutomateAmazon\\target\\amazon2.png");
		FileUtils.copyFile(source, target);
		}}


