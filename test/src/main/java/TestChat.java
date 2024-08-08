import java.time.Duration;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//--
import org.testng.annotations.AfterClass;
//--
import org.testng.annotations.BeforeClass;
//--
import org.testng.annotations.Test;

public class TestChat {
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
    public void testChatButton() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().frame(iframe);

        //Validate that the Chat button exists
        WebElement chat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div")));
        Assert.assertTrue(chat.isDisplayed(), "Chat button is not displayed");

        //Click on the Chat button
        chat.click();
        WebElement chatTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[1]/div/div/h2")));
        Assert.assertTrue(chatTitle.isDisplayed(), "The chat does not opened!");
        String title = chat.getText();
        System.out.println("The chat title is: " + title);

        WebElement textBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/textarea")));
        Assert.assertTrue(textBox.isDisplayed(), "Chat text box is not displayed.The chat does not opened!");

        // Trimite un text de test în textBox
        textBox.sendKeys("Test Message");

        String textBoxMessage = textBox.getAttribute("value");
        System.out.println("The message in the text box is: " + textBoxMessage);

//        Thread thread = null;
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

    }
}
