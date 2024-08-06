//--
import org.openqa.selenium.By;
//--
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
//--
import org.openqa.selenium.WebElement;
//--
import org.openqa.selenium.chrome.ChromeDriver;

//--
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//--
import org.testng.annotations.AfterClass;
//--
import org.testng.annotations.BeforeClass;
//--
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CheckInCalendar {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void testSelectorButton() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);
        WebElement calendarCheckInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]")));

        // Verifică dacă butonul este vizibil
        Assert.assertTrue(calendarCheckInButton.isDisplayed(), "Check in calendar button is not displayed");

        calendarCheckInButton.click();
        //JavascriptExecutor jse = (JavascriptExecutor)driver;
        //jse.executeScript("window.scrollBy(0,250)");
    }
}
