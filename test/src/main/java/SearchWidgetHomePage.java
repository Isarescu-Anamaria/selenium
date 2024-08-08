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
        //se schimba today xpath!!!!!(de pe data)
        WebElement today = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div/div[2]/table/tbody/tr[2]/td[5]/button/span")));
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
        //se schimba tomorrow xpath(data de pe calendar)
        WebElement tomorrow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div/div[2]/table/tbody/tr[2]/td[6]/button/span")));
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

    @Test
    public void testAdultsCounter(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        //Check if by default the counter displays "1"

        WebElement adultsNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
        // Verifică dacă este vizibil
        Assert.assertTrue(adultsNumber.isDisplayed(), "Adults number is not displayed");

        String actualNumberOfAdults = adultsNumber.getText();
        String expectedNumberOfAdults = "1";
        System.out.println("Actual number of adults is: " + actualNumberOfAdults);
        Assert.assertEquals(actualNumberOfAdults,expectedNumberOfAdults,"The number of adults is not set by default to 1!");

        //Check if you can decrement to less than one adult
        WebElement decrementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/a[2]")));
        decrementButton.click();
        String actualNumberOfAdultsAfterDecrement = adultsNumber.getText();
        System.out.println("Actual number of adults after decrement is: " + actualNumberOfAdultsAfterDecrement);
        Assert.assertEquals(actualNumberOfAdultsAfterDecrement,expectedNumberOfAdults,"The number of adults is decrement to less 1!");

        //Check if you can increment the Adults number
        WebElement incrementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/a[1]")));
        int numberOfClicksIncrement = 2;
        // Clic pe butonul de incrementare
        for (int i = 0; i < numberOfClicksIncrement; i++) {
            incrementButton.click();
        }

        String actualNumberOfAdultsAfterIncrement = adultsNumber.getText();
        String expectedNumberOfAdultsAfterIncrement = String.valueOf(1 + numberOfClicksIncrement);
        System.out.println("Actual number of adults after increment is: " + actualNumberOfAdultsAfterIncrement);
        System.out.println("Expected number of adults after increment is: " + expectedNumberOfAdultsAfterIncrement);
        Assert.assertEquals(actualNumberOfAdultsAfterIncrement, expectedNumberOfAdultsAfterIncrement, "The number of adults after increment does not match the expected value!");

        //Check if you can decrement the Adults number
        int numberOfClicksDecrement = 1;
        // Clic pe butonul de incrementare
        for (int i = 0; i < numberOfClicksDecrement; i++) {
            decrementButton.click();
        }

        String actualNumberOfAdultsAfterSecondDecrement = adultsNumber.getText();
        String expectedNumberOfAdultsAfterSecondDecrement = String.valueOf(1 + numberOfClicksDecrement);
        System.out.println("Actual number of adults after the second decrement is: " + actualNumberOfAdultsAfterSecondDecrement);
        System.out.println("Expected number of adults after the second decrement is: " + expectedNumberOfAdultsAfterSecondDecrement);
        Assert.assertEquals(actualNumberOfAdultsAfterSecondDecrement, expectedNumberOfAdultsAfterSecondDecrement, "The number of adults after the second decrement does not match the expected value!");
    }

    @Test
    public void testKidsCounter(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        //Check if by default the counter displays "0"

        WebElement kidsNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"children\"]/span[1]")));
        // Verifică dacă este vizibil
        Assert.assertTrue(kidsNumber.isDisplayed(), "Kids number is not displayed");

        String actualNumberOfKids = kidsNumber.getText();
        String expectedNumberOfKids = "0";
        System.out.println("Actual number of kids is: " + actualNumberOfKids);
        Assert.assertEquals(actualNumberOfKids,expectedNumberOfKids,"The number of kids is not set by default to 0!");

        //Check if you can decrement to less than zero kids
        WebElement decrementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"children\"]/a[2]")));
        decrementButton.click();
        String actualNumberOfKidsAfterDecrement = kidsNumber.getText();
        System.out.println("Actual number of kids after decrement is: " + actualNumberOfKidsAfterDecrement);
        Assert.assertEquals(actualNumberOfKidsAfterDecrement,expectedNumberOfKids,"The number of kids is decrement to less 0!");

        //Check if you can increment the Kids number
        WebElement incrementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"children\"]/a[1]")));
        int numberOfClicksIncrement = 2;
        // Clic pe butonul de incrementare
        for (int i = 0; i < numberOfClicksIncrement; i++) {
            incrementButton.click();
        }

        String actualNumberOfKidsAfterIncrement = kidsNumber.getText();
        String expectedNumberOfKidsAfterIncrement = String.valueOf(0 + numberOfClicksIncrement);
        System.out.println("Actual number of kids after increment is: " + actualNumberOfKidsAfterIncrement);
        System.out.println("Expected number of kids after increment is: " + expectedNumberOfKidsAfterIncrement);
        Assert.assertEquals(actualNumberOfKidsAfterIncrement, expectedNumberOfKidsAfterIncrement, "The number of kids after increment does not match the expected value!");

        //Check if you can decrement the Kids number
        int numberOfClicksDecrement = 1;
        // Clic pe butonul de incrementare
        for (int i = 0; i < numberOfClicksDecrement; i++) {
            decrementButton.click();
        }

        String actualNumberOfKidsAfterSecondDecrement = kidsNumber.getText();
        String expectedNumberOfKidsAfterSecondDecrement = String.valueOf(0 + numberOfClicksDecrement);
        System.out.println("Actual number of kids after the second decrement is: " + actualNumberOfKidsAfterSecondDecrement);
        System.out.println("Expected number of kids after the second decrement is: " + expectedNumberOfKidsAfterSecondDecrement);
        Assert.assertEquals(actualNumberOfKidsAfterSecondDecrement, expectedNumberOfKidsAfterSecondDecrement, "The number of kids after the second decrement does not match the expected value!");
    }

    @Test
    public void testSearchButton(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        //Check if the Search button is displayed
        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"search-widget\"]/form/ul/li[6]/button/span")));
        // Verifică dacă este vizibil
        Assert.assertTrue(searchButton.isDisplayed(), "Search button is not displayed");

        //Enter valid data into Check In, Check Out, Adults & Kids

        //for check in
        WebElement calendarCheckInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-in\"]/span[2]")));
       // Assert.assertTrue(calendarCheckInButton.isDisplayed(), "Check in calendar button is not displayed");

        calendarCheckInButton.click();
        driver.switchTo().defaultContent();

        WebElement iframeCheckInCalendar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(iframeCheckInCalendar);

        //WebElement calendarCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div")));
        //Assert.assertTrue(calendarCheckIn.isDisplayed(), "Calendar did not open as expected");

        //click on a chosen date
        //se schimba xpath!!!!!(de pe data)
        WebElement checkInDay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div/div[2]/table/tbody/tr[4]/td[4]/button/span")));
        //Assert.assertTrue(checkInDay.isDisplayed(), "Check in calendar day button is not displayed");
        checkInDay.click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframe);

        WebElement checkInDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-in-value")));
        String actualValueOfCheckInDate = checkInDate.getText();
        System.out.println("The check in day is: " + actualValueOfCheckInDate);

        String expectedValueOfCheckInDate = "21 Aug 2024";
        System.out.println("Expected Check in date is: " + expectedValueOfCheckInDate);

        Assert.assertEquals(actualValueOfCheckInDate, expectedValueOfCheckInDate,"Check in date date does not corespond!");

        //for check out
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframe);

        WebElement calendarCheckOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-out\"]/span[2]")));
        //Assert.assertTrue(calendarCheckOutButton.isDisplayed(), "Check out calendar button is not displayed");

        calendarCheckOutButton.click();
        driver.switchTo().defaultContent();

        WebElement iframeCheckOutCalendar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("U73P_q")));
        driver.switchTo().frame(iframeCheckOutCalendar);

        //WebElement calendarCheckOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div")));
        //Assert.assertTrue(calendarCheckOut.isDisplayed(), "Calendar did not open as expected");

        //click on a chosen date
        //se schimba xpath(data de pe calendar)
        WebElement checkOutDay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div/div[2]/table/tbody/tr[5]/td[5]/button/span")));
        //Assert.assertTrue(checkOutDay.isDisplayed(), "Check out calendar day button is not displayed");
        checkOutDay.click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframe);

        WebElement checkOutDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check-out-value")));
        String actualValueOfCheckOutDate = checkOutDate.getText();
        System.out.println("The check out date is: " + actualValueOfCheckOutDate);

        String expectedValueOfCheckOutDate = "29 Aug 2024";
        System.out.println("Expected Check out date is: " + expectedValueOfCheckOutDate);

        Assert.assertEquals(actualValueOfCheckOutDate, expectedValueOfCheckOutDate,"Check out date does not corespond!");

        //for number of adults
        WebElement adultsNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
        // Verifică dacă este vizibil
        Assert.assertTrue(adultsNumber.isDisplayed(), "Adults number is not displayed");

        WebElement incrementAdultsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/a[1]")));
        int numberOfClicksIncrementAdults = 1;
        // Clic pe butonul de incrementare
        for (int i = 0; i < numberOfClicksIncrementAdults; i++) {
            incrementAdultsButton.click();
        }

        String actualNumberOfAdultsAfterIncrement = adultsNumber.getText();
        String expectedNumberOfAdultsAfterIncrement = String.valueOf(1 + numberOfClicksIncrementAdults);
        System.out.println("Actual number of adults after increment is: " + actualNumberOfAdultsAfterIncrement);
        System.out.println("Expected number of adults after increment is: " + expectedNumberOfAdultsAfterIncrement);
        Assert.assertEquals(actualNumberOfAdultsAfterIncrement, expectedNumberOfAdultsAfterIncrement, "The number of adults after increment does not match the expected value!");

        //for number of kids
        WebElement kidsNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"children\"]/span[1]")));
        // Verifică dacă este vizibil
        Assert.assertTrue(kidsNumber.isDisplayed(), "Kids number is not displayed");

        WebElement incrementKidsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"children\"]/a[1]")));
        int numberOfClicksIncrementKids = 1;
        // Clic pe butonul de incrementare
        for (int i = 0; i < numberOfClicksIncrementKids; i++) {
            incrementKidsButton.click();
        }

        String actualNumberOfKidsAfterIncrement = kidsNumber.getText();
        String expectedNumberOfKidsAfterIncrement = String.valueOf(0 + numberOfClicksIncrementKids);
        System.out.println("Actual number of kids after increment is: " + actualNumberOfKidsAfterIncrement);
        System.out.println("Expected number of kids after increment is: " + expectedNumberOfKidsAfterIncrement);
        Assert.assertEquals(actualNumberOfKidsAfterIncrement, expectedNumberOfKidsAfterIncrement, "The number of kids after increment does not match the expected value!");

        //Click on search button
        searchButton.click();
        System.out.println("URL of the Rooms page is: " + driver.getCurrentUrl());
        String actualRoomsPageURL = driver.getCurrentUrl();
        String expectedRoomsPageURL ="https://ancabota09.wixsite.com/intern/rooms/%3Fadults%3D2%26checkIn%3D1724198400000%26checkOut%3D1724889600000%26children%3D1";
        Assert.assertEquals(actualRoomsPageURL, expectedRoomsPageURL, "The Rooms page does not load!");
    }

}
