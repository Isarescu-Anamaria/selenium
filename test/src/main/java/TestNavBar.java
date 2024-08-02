import java.util.concurrent.TimeUnit;
//--
import org.openqa.selenium.By;
//--
import org.openqa.selenium.WebDriver;
//--
import org.openqa.selenium.WebElement;
//--
import org.openqa.selenium.chrome.ChromeDriver;

//--
import org.testng.Assert;
//--
import org.testng.annotations.AfterClass;
//--
import org.testng.annotations.BeforeClass;
//--
import org.testng.annotations.Test;

public class TestNavBar {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }


    //BOOK NOW button
    //if exists test
    @Test
    public void testBookNowButtonExists() {

        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        //identify button with partial link text
        WebElement bookNowButton = driver.findElement(By.partialLinkText("BOOK NOW"));
        //check if element is visible
        boolean var = driver.findElement(By.partialLinkText("BOOK NOW")).isDisplayed();
        if (var) {
            System.out.println("BOOK NOW button is displayed");
        }
        else{
            System.out.println("BOOK NOW button is not displayed");
        }

    }

    @Test
    public void testBookNowButton() {

        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        //identify button with partial link text
        WebElement bookNowButton = driver.findElement(By.linkText("BOOK NOW"));
        boolean var = driver.findElement(By.partialLinkText("BOOK NOW")).isDisplayed();
        if (var) {
            System.out.println("BOOK NOW button is displayed");
            bookNowButton.click();
        }
        else{
            System.out.println("BOOK NOW button is not displayed");
            Assert.fail();
        }

        System.out.println("URL of the page is: " + driver.getCurrentUrl());
        String bookNowPageURL = driver.getCurrentUrl();
        String bookNowURL ="https://ancabota09.wixsite.com/intern/booknow";
        Assert.assertEquals(bookNowPageURL, bookNowURL, "The wrong page is loaded!");
    }

    //HOME button
    @Test
    public void testHomeButton(){
        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        //identify button with
        WebElement homeButton = driver.findElement(By.id("i6kl732v0label"));
        boolean var = driver.findElement(By.id("i6kl732v0label")).isDisplayed();
        if (var) {
            System.out.println("HOME button is displayed");
            homeButton.click();
        }
        else{
            System.out.println("HOME button is not displayed");
            Assert.fail();
        }
        System.out.println("URL of the page is: " + driver.getCurrentUrl());
        String homePageURL = driver.getCurrentUrl();
        String homeURL ="https://ancabota09.wixsite.com/intern";
        Assert.assertEquals(homePageURL, homeURL, "The wrong page is loaded!");
    }

    //EXPLORE button
    @Test
    public void testExploreButton(){
        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        //identify button with
        WebElement exploreButton = driver.findElement(By.id("i6kl732v1label"));
        boolean var = driver.findElement(By.id("i6kl732v1label")).isDisplayed();
        if (var) {
            System.out.println("EXPLORE button is displayed");
            exploreButton.click();
        }
        else{
            System.out.println("EXPLORE button is not displayed");
            Assert.fail();
        }
        System.out.println("URL of the Explore page is: " + driver.getCurrentUrl());
        String explorePageURL = driver.getCurrentUrl();
        String exploreURL ="https://ancabota09.wixsite.com/intern/explore";
        Assert.assertEquals(explorePageURL, exploreURL, "The wrong page is loaded!");
    }

    //ROOMS button
    @Test
    public void testRoomsButton(){
        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        //identify button with
        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        boolean var = driver.findElement(By.id("i6kl732v2label")).isDisplayed();
        if (var) {
            System.out.println("ROOMS button is displayed");
            roomsButton.click();
        }
        else{
            System.out.println("ROOMS button is not displayed");
            Assert.fail();
        }
        System.out.println("URL of the Rooms page is: " + driver.getCurrentUrl());
        String roomsPageURL = driver.getCurrentUrl();
        String roomsURL ="https://ancabota09.wixsite.com/intern/rooms";
        Assert.assertEquals(roomsPageURL, roomsURL, "The wrong page is loaded!");
    }

    //CONTACT button
    @Test
    public void testContactButton(){
        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        //identify button with
        WebElement contactButton = driver.findElement(By.id("i6kl732v3label"));
        boolean var = driver.findElement(By.id("i6kl732v3label")).isDisplayed();
        if (var) {
            System.out.println("CONTACT button is displayed");
            contactButton.click();
        }
        else{
            System.out.println("CONTACT button is not displayed");
            Assert.fail();
        }
        System.out.println("URL of the Contact page is: " + driver.getCurrentUrl());
        String contactPageURL = driver.getCurrentUrl();
        String contactURL ="https://ancabota09.wixsite.com/intern/contact";
        Assert.assertEquals(contactPageURL, contactURL, "The wrong page is loaded!");
    }

    //Page Title button
    @Test
    public void testPageTitleButton(){
        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        //identify button with
        WebElement pageTitleButton = driver.findElement(By.partialLinkText("HOME & AWAY"));
        //check if element is visible
        boolean var = driver.findElement(By.partialLinkText("HOME & AWAY")).isDisplayed();
        if (var) {
            System.out.println("HOME & AWAY button is displayed");
            pageTitleButton.click();
        }
        else{
            System.out.println("HOME & AWAY button is not displayed");
            Assert.fail();
        }

        String initialWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(initialWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        System.out.println("URL of the HOME & AWAY page is: " + driver.getCurrentUrl());
        String pageTitlePageURL = driver.getCurrentUrl();
        String pageTitleURL ="https://ancabota09.wixsite.com/intern";
        Assert.assertEquals(pageTitlePageURL, pageTitleURL, "The wrong page is loaded!");

        //return to the Home page(initial page)
        driver.close();
        driver.switchTo().window(initialWindowHandle);
        System.out.println("Returned to the initial page: " + driver.getCurrentUrl());
    }

}