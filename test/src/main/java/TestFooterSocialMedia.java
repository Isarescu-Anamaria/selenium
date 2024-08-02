import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestFooterSocialMedia {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    //Social media button

    // Facebook button
    @Test
    public void testFacebookButton(){
        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        //identify button with
        WebElement facebookButton = driver.findElement(By.xpath("//*[@id=\"img_0_i6rlbitx\"]/img"));
        boolean var = driver.findElement(By.xpath("//*[@id=\"img_0_i6rlbitx\"]/img")).isDisplayed();
        if (var) {
            System.out.println("Facebook button is displayed");
        }
        else{
            System.out.println("Facebook button is not displayed");
            Assert.fail();
        }
        facebookButton.click();
        // Switch to the new tab
        String initialWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(initialWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        System.out.println("URL of the Facebook page is: " + driver.getCurrentUrl());
        String facebookPageURL = driver.getCurrentUrl(); //actual URL
        String facebookURL ="https://www.facebook.com/wix"; //expected URL
        Assert.assertEquals(facebookPageURL, facebookURL, "The wrong page is loaded!");

        //return to the Home page(initial page)
        driver.close();
        driver.switchTo().window(initialWindowHandle);
        System.out.println("Returned to the initial page: " + driver.getCurrentUrl());
    }

    // Twitter/X button
    @Test
    public void testTwitterButton(){
        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        //identify button with xpath
        WebElement twitterButton = driver.findElement(By.xpath("//*[@id=\"img_1_i6rlbitx\"]/img"));
        boolean var = driver.findElement(By.xpath("//*[@id=\"img_1_i6rlbitx\"]/img")).isDisplayed();
        if (var) {
            System.out.println("Twitter button is displayed");
        }
        else{
            System.out.println("Twitter button is not displayed");
            Assert.fail();
        }
        twitterButton.click();
        // Switch to the new tab
        String initialWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(initialWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        System.out.println("URL of the Twitter/X page is: " + driver.getCurrentUrl());
        String twitterPageURL = driver.getCurrentUrl(); //actual URL
        String twitterURL ="https://x.com/wix"; //expected URL
        Assert.assertEquals(twitterPageURL, twitterURL, "The wrong page is loaded!");

        //return to the Home page(initial page)
        driver.close();
        driver.switchTo().window(initialWindowHandle);
        System.out.println("Returned to the initial page: " + driver.getCurrentUrl());
    }

    // Pinterest button
    @Test
    public void testPinterestButton(){
        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        //identify button with
        WebElement pinterestButton = driver.findElement(By.xpath("//*[@id=\"img_2_i6rlbitx\"]/img"));
        boolean var = driver.findElement(By.xpath("//*[@id=\"img_2_i6rlbitx\"]/img")).isDisplayed();
        if (var) {
            System.out.println("Pinterest button is displayed");
        }
        else{
            System.out.println("Pinterest button is not displayed");
            Assert.fail();
        }
        pinterestButton.click();
        // Switch to the new tab
        String initialWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(initialWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        System.out.println("URL of the Pinterest page is: " + driver.getCurrentUrl());
        String pinterestPageURL = driver.getCurrentUrl(); //actual URL
        String pinterestURL ="https://www.pinterest.com/wixcom/"; //expected URL
        Assert.assertEquals(pinterestPageURL, pinterestURL, "The wrong page is loaded!");

        //return to the Home page(initial page)
        driver.close();
        driver.switchTo().window(initialWindowHandle);
        System.out.println("Returned to the initial page: " + driver.getCurrentUrl());
    }
}
