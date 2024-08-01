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

public class TestHomePage {

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
    public void testBookNowButtonExists() {

        //implicit wait
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        //identify button with partial link text
        WebElement bookNowButton = driver.findElement(By.linkText("BOOK NOW"));
        bookNowButton.click();
        System.out.println("title of page is: " + driver.getTitle());
        String bookNowPage = driver.getTitle();
        String bookNowTitle ="BOOK NOW | Intern";
        Assert.assertEquals(bookNowPage, bookNowTitle, "The wrong page is loaded!");
    }
}
