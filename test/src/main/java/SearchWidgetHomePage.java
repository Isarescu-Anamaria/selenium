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

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.TimeUnit;

public class SearchWidgetHomePage {
    private WebDriver driver;
    private java.time.format.DateTimeFormatter DateTimeFormatter;
    private Thread thread;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    @Test
    public void testSelectorCheckInButton() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);
        WebElement calendarCheckInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]/span[2]")));
        // Verifică dacă butonul este vizibil
        Assert.assertTrue(calendarCheckInButton.isDisplayed(), "Check in calendar button is not displayed");

        calendarCheckInButton.click();
        driver.switchTo().defaultContent();

        WebElement iframeCheckInCalendar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(iframeCheckInCalendar);

        WebElement calendarCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div")));
        Assert.assertTrue(calendarCheckIn.isDisplayed(), "Calendar did not open as expected");

        //click on a day before
        WebElement dayBefore = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div/div[2]/table/tbody/tr[2]/td[2]/button/span")));
        // Verifică dacă butonul este vizibil
        Assert.assertTrue(dayBefore.isDisplayed(), "Check in calendar day before button is not displayed");
        dayBefore.click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframe);

        WebElement beforeDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"check-in\"]/span[2]")));
        String actualValueOfDayBefore = beforeDate.getText();
        System.out.println("The past day is: " + actualValueOfDayBefore);
        String expectedValueOfDayBefore = "DD MM YYYY";
        Assert.assertEquals(actualValueOfDayBefore, expectedValueOfDayBefore,"Before date does not corespond!");

        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframeCheckInCalendar);

        //click on today
        WebElement today = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div/div[2]/table/tbody/tr[2]/td[4]/button/span")));
        // Verifică dacă butonul este vizibil
        Assert.assertTrue(today.isDisplayed(), "Check in calendar today button is not displayed");
        today.click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframe);
        WebElement todayDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-in-value")));
        String actualValueOfToday = todayDate.getText();
        System.out.println("Today is: " + actualValueOfToday);

        LocalDate nowDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        String expectedValueOfToday = nowDate.format(formatter);
        System.out.println("Expected Today date is: " + expectedValueOfToday);

        Assert.assertEquals(actualValueOfToday, expectedValueOfToday,"Today date does not corespond!");
    }

    @Test
    public void testCheckInNavigation() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);
        WebElement calendarCheckInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]/span[2]")));
        // Verifică dacă butonul este vizibil
        Assert.assertTrue(calendarCheckInButton.isDisplayed(), "Check in calendar button is not displayed");

        calendarCheckInButton.click();
        driver.switchTo().defaultContent();

        WebElement iframeCheckInCalendar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(iframeCheckInCalendar);

        WebElement calendarCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div")));
        Assert.assertTrue(calendarCheckIn.isDisplayed(), "Calendar did not open as expected");

        //forward months test
        WebElement checkInForwardButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("navigate-right")));
        //checkInForwardButton.click();
        int numberOfClicks = 3;
        WebElement month = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div/div[1]")));
        // Verifică dacă elementul este vizibil
        //Assert.assertTrue(month.isDisplayed(), "The month is not displayed");

        // Calculează luna corectă
        LocalDate today = LocalDate.now();
        LocalDate futureMonth = today.with(TemporalAdjusters.firstDayOfMonth()).plusMonths(numberOfClicks);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        String expectedForwardMonth = futureMonth.format(formatter);
        System.out.println("Expected Forward Month is: " + expectedForwardMonth);

        // Clic pe butonul de navigare înainte
        for (int i = 0; i < numberOfClicks; i++) {
            checkInForwardButton.click();
        }

        String actualForwardMonth = month.getText();
        System.out.println("Actual Forward month is: " + actualForwardMonth);

        Assert.assertEquals(actualForwardMonth, expectedForwardMonth,"Forward month does not corespond!");


        //backward months test
        WebElement checkInBackwardButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("navigate-left")));
        //checkInBackwardButton.click();

        // Calculează luna corectă
        LocalDate pastMonth = today.with(TemporalAdjusters.firstDayOfMonth()).minusMonths(numberOfClicks);

        String expectedBackwardMonth = pastMonth.format(formatter);
        System.out.println("Expected Backward Month is: " + expectedBackwardMonth);
        int numberOfClicksBackward = numberOfClicks + 3;
        // Clic pe butonul de navigare înapoi
        for (int i = 0; i < numberOfClicksBackward; i++) {
           checkInBackwardButton.click();
        }

        String actualBackwardMonth = month.getText();
        System.out.println("Actual Backward month is: " + actualBackwardMonth);

        Assert.assertEquals(actualBackwardMonth, expectedBackwardMonth,"Backward month does not corespond!");
    }

    @Test
    public void testSelectorCheckOutButton() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);
        WebElement calendarCheckOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-out\"]/span[2]")));
        // Verifică dacă butonul este vizibil
        Assert.assertTrue(calendarCheckOutButton.isDisplayed(), "Check out calendar button is not displayed");

        calendarCheckOutButton.click();
        driver.switchTo().defaultContent();

        WebElement iframeCheckOutCalendar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(iframeCheckOutCalendar);

        WebElement calendarCheckOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div")));
        Assert.assertTrue(calendarCheckOut.isDisplayed(), "Calendar did not open as expected");

        //click on a day before
        WebElement dayBefore = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div/div[2]/table/tbody/tr[2]/td[2]/button/span")));
        // Verifică dacă butonul este vizibil
        Assert.assertTrue(dayBefore.isDisplayed(), "Check out calendar day before button is not displayed");
        dayBefore.click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframe);

        WebElement beforeDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"check-out\"]/span[2]")));
        String actualValueOfDayBefore = beforeDate.getText();
        System.out.println("The past day is: " + actualValueOfDayBefore);
        String expectedValueOfDayBefore = "DD MM YYYY";
        Assert.assertEquals(actualValueOfDayBefore, expectedValueOfDayBefore,"Before date does not corespond!");

        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframeCheckOutCalendar);

        //click on tomorrow
        WebElement tomorrow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div/div[2]/table/tbody/tr[2]/td[5]/button/span")));
        // Verifică dacă butonul este vizibil
        Assert.assertTrue(tomorrow.isDisplayed(), "Check out calendar tomorrow button is not displayed");
        tomorrow.click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframe);

        WebElement tomorrowDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"check-out\"]/span[2]")));
        String actualValueOfTomorrow = tomorrowDate.getText();
        System.out.println("Tomorrow is: " + actualValueOfTomorrow);

        LocalDate nowDate = LocalDate.now().plusDays(1);;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        String expectedValueOfTomorrow = nowDate.format(formatter);
        System.out.println("Expected Tomorrow date is: " + expectedValueOfTomorrow);

        Assert.assertEquals(actualValueOfTomorrow, expectedValueOfTomorrow,"Tomorrow date does not corespond!");
    }

    @Test
    public void testCheckOutNavigation() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        WebElement calendarCheckOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-out\"]/span[2]")));
        // Verifică dacă butonul este vizibil
        Assert.assertTrue(calendarCheckOutButton.isDisplayed(), "Check out calendar button is not displayed");

        calendarCheckOutButton.click();
        driver.switchTo().defaultContent();

        WebElement iframeCheckOutCalendar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(iframeCheckOutCalendar);

        WebElement calendarCheckOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div")));
        Assert.assertTrue(calendarCheckOut.isDisplayed(), "Calendar did not open as expected");

        //forward months test
        WebElement checkOutForwardButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("navigate-right")));
        //checkOutForwardButton.click();
        int numberOfClicks = 3;
        WebElement month = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/div/div[1]")));
        // Verifică dacă elementul este vizibil
        //Assert.assertTrue(month.isDisplayed(), "The month is not displayed");

        // Calculează luna corectă
        LocalDate today = LocalDate.now();
        LocalDate futureMonth = today.with(TemporalAdjusters.firstDayOfMonth()).plusMonths(numberOfClicks);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        String expectedForwardMonth = futureMonth.format(formatter);
        System.out.println("Expected Forward Month is: " + expectedForwardMonth);

        // Clic pe butonul de navigare înainte
        for (int i = 0; i < numberOfClicks; i++) {
            checkOutForwardButton.click();
        }

        String actualForwardMonth = month.getText();
        System.out.println("Actual Forward month is: " + actualForwardMonth);

        Assert.assertEquals(actualForwardMonth, expectedForwardMonth,"Forward month does not corespond!");


        //backward months test
        WebElement checkOutBackwardButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("navigate-left")));
        //checkInBackwardButton.click();

        // Calculează luna corectă
        LocalDate pastMonth = today.with(TemporalAdjusters.firstDayOfMonth()).minusMonths(numberOfClicks);

        String expectedBackwardMonth = pastMonth.format(formatter);
        System.out.println("Expected Backward Month is: " + expectedBackwardMonth);
        int numberOfClicksBackward = numberOfClicks + 3;
        // Clic pe butonul de navigare înapoi
        for (int i = 0; i < numberOfClicksBackward; i++) {
            checkOutBackwardButton.click();
        }

        String actualBackwardMonth = month.getText();
        System.out.println("Actual Backward month is: " + actualBackwardMonth);

        Assert.assertEquals(actualBackwardMonth, expectedBackwardMonth,"Backward month does not corespond!");
    }

}
