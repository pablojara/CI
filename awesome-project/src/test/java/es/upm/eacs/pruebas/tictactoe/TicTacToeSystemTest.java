package es.upm.eacs.pruebas.tictactoe;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TicTacToeSystemTest{

	protected RemoteWebDriver driverO;
	protected RemoteWebDriver driverX;
	private String url = "http://host.testcontainers.internal:8080";

	@Rule
    public BrowserWebDriverContainer chrome1 = new BrowserWebDriverContainer()
                     .withDesiredCapabilities(DesiredCapabilities.chrome())
                     .withRecordingMode(VncRecordingMode.RECORD_ALL, new File("./target/"));
    @Rule
    public BrowserWebDriverContainer chrome2 = new BrowserWebDriverContainer()
                     .withDesiredCapabilities(DesiredCapabilities.chrome());
	
	@BeforeClass
	public static void setupClass() {
		//WebDriverManager.chromedriver().setup();
		Testcontainers.exposeHostPorts(8080);
		WebApp.start();
		
	}
	@AfterClass
	public static void teardownClass() {
		WebApp.stop();
	}
	@Before
	public void setupTest() {
		driverO = chrome1.getWebDriver();
		driverX = chrome2.getWebDriver();
	}
	@After
	public void teardown() {
		if (driverO != null) {
			driverO.quit();
		}
		if (driverX != null) {
			driverX.quit();
		}
	}
	
	@Test
	public void GIVEN_twoPlayers_WHEN_firstOneStarts_THEN_Win() { 
		driverO.get(url);
		driverX.get(url);

		driverO.findElement(By.id("nickname")).sendKeys("Pablo");
		driverO.findElement(By.id("startBtn")).click();
		driverX.findElement(By.id("nickname")).sendKeys("Maria");
		driverX.findElement(By.id("startBtn")).click();

		driverO.findElement(By.id("cell-0")).click();
		driverX.findElement(By.id("cell-3")).click();
		
		driverO.findElement(By.id("cell-1")).click();
		driverX.findElement(By.id("cell-5")).click();
		
		driverO.findElement(By.id("cell-2")).click();

		String webMessage = driverX.switchTo().alert().getText();
		String message = "Pablo wins! Maria looses.";
		Assert.assertEquals(message, webMessage);
	}
	
	@Test
	public void GIVEN_twoPlayers_WHEN_firstOneStarts_THEN_Lose() { 
		driverO.get(url);
		driverX.get(url);

		driverO.findElement(By.id("nickname")).sendKeys("Pablo");
		driverO.findElement(By.id("startBtn")).click();
		driverX.findElement(By.id("nickname")).sendKeys("Maria");
		driverX.findElement(By.id("startBtn")).click();

		driverO.findElement(By.id("cell-0")).click();
		driverX.findElement(By.id("cell-3")).click();
		
		driverO.findElement(By.id("cell-7")).click();
		driverX.findElement(By.id("cell-4")).click();
		
		driverO.findElement(By.id("cell-2")).click();
		driverX.findElement(By.id("cell-5")).click();


		String webMessage = driverX.switchTo().alert().getText();
		String message = "Maria wins! Pablo looses.";
		Assert.assertEquals(message, webMessage);
	}
	
	@Test
	public void GIVEN_twoPlayers_WHEN_firstOneStarts_THEN_NobodyWin() { 
		driverO.get(url);
		driverX.get(url);

		driverO.findElement(By.id("nickname")).sendKeys("Pablo");
		driverO.findElement(By.id("startBtn")).click();
		driverX.findElement(By.id("nickname")).sendKeys("Maria");
		driverX.findElement(By.id("startBtn")).click();

		driverO.findElement(By.id("cell-0")).click();
		driverX.findElement(By.id("cell-1")).click();
		
		driverO.findElement(By.id("cell-2")).click();
		driverX.findElement(By.id("cell-5")).click();
		
		driverO.findElement(By.id("cell-3")).click();
		driverX.findElement(By.id("cell-6")).click();
		
		driverO.findElement(By.id("cell-4")).click();
		driverX.findElement(By.id("cell-8")).click();
		
		driverO.findElement(By.id("cell-7")).click();

		String webMessage = driverX.switchTo().alert().getText();
		String message = "Draw!";
		Assert.assertEquals(message, webMessage);
	}
}
