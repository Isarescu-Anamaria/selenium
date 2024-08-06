import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestFooterButtonsHomePage {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    //Contact mail button from the footer

    @Test
    public void testContactMailButton(){
        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        //identify button with

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement contactMailButton =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"i71ww6nk\"]/p[1]/a")));
        boolean var = driver.findElement(By.xpath("//*[@id=\"i71ww6nk\"]/p[1]/a")).isDisplayed();
        if (var) {
            System.out.println("Contact Mail button is displayed");
        }
        else{
            System.out.println("Contact Mail button is not displayed");
            Assert.fail();
        }
        contactMailButton.click();
        wait.until(driver -> true);

         //Verify the href attribute contains the mailto link
        String href = contactMailButton.getAttribute("href");
        Assert.assertTrue(href.startsWith("mailto:"), "The Contact link does not contain a 'mailto' link.");

         //Optionally, check the specific email address if needed
         Assert.assertTrue(href.contains("info@mysite.com"), "The 'Contact Us' link does not contain the expected email address.");
    }

    //Wix page button from the footer

    @Test
    public void testWixPageButton(){
        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        //identify button with
        WebElement wixPageButton = driver.findElement(By.xpath("//*[@id=\"i71wwqnj\"]/p[2]/span/a"));
        boolean var = driver.findElement(By.xpath("//*[@id=\"i71wwqnj\"]/p[2]/span/a")).isDisplayed();
        if (var) {
            System.out.println("Wix Page button is displayed");
        }
        else{
            System.out.println("Wix Page button is not displayed");
            Assert.fail();
        }
        wixPageButton.click();
        // Switch to the new tab
        String initialWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(initialWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        System.out.println("URL of the Wix page is: " + driver.getCurrentUrl());
        String wixPageURL = driver.getCurrentUrl(); //actual URL
        String wixURL = "https://www.wix.com/?utm_campaign=vir_created_with"; //expected URL
        Assert.assertEquals(wixPageURL, wixURL, "The wrong page is loaded!");

        //return to the Home page(initial page)
        driver.close();
        driver.switchTo().window(initialWindowHandle);
        System.out.println("Returned to the initial page: " + driver.getCurrentUrl());
    }

}