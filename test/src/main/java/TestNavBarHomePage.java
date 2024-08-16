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

public class TestNavBarHomePage {

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
    public void testBookNowButton() {

        //implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        //identify button with partial link text
        WebElement bookNowButton = driver.findElement(By.linkText("BOOK NOW"));
        Assert.assertTrue(bookNowButton.isDisplayed(),"The book now button is not displayed");

        bookNowButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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
        //identify button with id
        WebElement homeButton = driver.findElement(By.id("i6kl732v0label"));

        Assert.assertTrue(homeButton.isDisplayed(),"The home button is not displayed");

        homeButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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
        //identify button with id
        WebElement exploreButton = driver.findElement(By.id("i6kl732v1label"));

        Assert.assertTrue(exploreButton.isDisplayed(),"The explore button is not displayed");

        exploreButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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

        //identify button with id
        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");

        roomsButton.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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

        Assert.assertTrue(contactButton.isDisplayed(),"The contact button is not displayed");

        contactButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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
        //identify button with partial link text
        WebElement pageTitleButton = driver.findElement(By.partialLinkText("HOME & AWAY"));

        Assert.assertTrue(pageTitleButton.isDisplayed(),"The page title button is not displayed");

        pageTitleButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

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