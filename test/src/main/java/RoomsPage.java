//--
import org.openqa.selenium.*;
//--
//--
//--
import org.openqa.selenium.chrome.ChromeDriver;

//--
import org.openqa.selenium.interactions.Actions;
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
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RoomsPage {

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

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        WebElement calendarCheckInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("check-in")));
        // Verifică dacă butonul este vizibil
        Assert.assertTrue(calendarCheckInButton.isDisplayed(), "Check in calendar button is not displayed");

        calendarCheckInButton.click();

        WebElement calendarCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".calendar-popup.s-field.s-separator.visible")));
        Assert.assertTrue(calendarCheckIn.isDisplayed(), "Calendar did not open as expected");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testSelectorCheckOutButton() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        WebElement calendarCheckOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("check-out")));
        // Verifică dacă butonul este vizibil
        Assert.assertTrue(calendarCheckOutButton.isDisplayed(), "Check in calendar button is not displayed");

        calendarCheckOutButton.click();

        WebElement calendarCheckOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".calendar-popup.s-field.s-separator.visible")));
        Assert.assertTrue(calendarCheckOut.isDisplayed(), "Calendar did not open as expected");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testAdultsCounter(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        //Check if the Adults counter button exists
        WebElement adultsNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
        Assert.assertTrue(adultsNumber.isDisplayed(), "Adults number is not displayed");

        //Check if by default the counter displays "1"

        String actualNumberOfAdults = adultsNumber.getText();
        String expectedNumberOfAdults = "1";
        System.out.println("Actual number of adults is: " + actualNumberOfAdults);
        Assert.assertEquals(actualNumberOfAdults,expectedNumberOfAdults,"The number of adults is not set by default to 1!");

        //Check if you can decrement to less than one adult
        WebElement decrementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("down")));
        decrementButton.click();
        String actualNumberOfAdultsAfterDecrement = adultsNumber.getText();
        System.out.println("Actual number of adults after decrement is: " + actualNumberOfAdultsAfterDecrement);
        Assert.assertEquals(actualNumberOfAdultsAfterDecrement,expectedNumberOfAdults,"The number of adults is decrement to less 1!");

        //Check if you can increment the Adults number
        WebElement incrementButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("up")));
        int numberOfClicksIncrement = 2;
        // Clic pe butonul de incrementare
        for (int i = 0; i < numberOfClicksIncrement; i++) {
            incrementButton.click();
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
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

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

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
    public void testSearchButtonValidInput(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        //Check if the Search button is displayed
        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search.s-button")));
        // Verifică dacă este vizibil
        Assert.assertTrue(searchButton.isDisplayed(), "Search button is not displayed");

        //Enter valid data into Check In, Check Out, Adults & Kids

        //for check in
        WebElement calendarCheckInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("check-in")));
        Assert.assertTrue(calendarCheckInButton.isDisplayed(), "Check in calendar button is not displayed");

        calendarCheckInButton.click();

        WebElement calendarCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".calendar-popup.s-field.s-separator.visible")));
        Assert.assertTrue(calendarCheckIn.isDisplayed(), "Calendar did not open as expected");

       //click on a chosen date
        //se schimba xpath!!!!!(de pe data)
        WebElement checkInDayButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='21, Wednesday August 2024']")));
        Assert.assertTrue(checkInDayButton.isDisplayed(), "Check in calendar day button is not displayed");
        checkInDayButton.click();

        WebElement checkInDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        String actualValueOfCheckInDate = checkInDate.getText();
        System.out.println("The check in day is: " + actualValueOfCheckInDate);

        String expectedValueOfCheckInDate = "21 Aug 2024";
        System.out.println("Expected Check in date is: " + expectedValueOfCheckInDate);

        Assert.assertEquals(actualValueOfCheckInDate, expectedValueOfCheckInDate,"Check in date date does not corespond!");

        //for check out

        WebElement calendarCheckOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div")));
        Assert.assertTrue(calendarCheckOut.isDisplayed(), "Calendar did not open as expected");

        //click on a chosen date
        //se schimba xpath(data de pe calendar)
        WebElement checkOutDay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div/div[2]/table/tbody/tr[5]/td[5]/button")));
        Assert.assertTrue(checkOutDay.isDisplayed(), "Check out calendar day button is not displayed");
        checkOutDay.click();

        //WebElement checkOutDayButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='29, Thursday August 2024']")));
       //Assert.assertTrue(checkOutDayButton.isDisplayed(), "Check out calendar day button is not displayed");
        //checkOutDayButton.click();

        WebElement checkOutDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_out-value")));
        String actualValueOfCheckOutDate = checkOutDate.getText();
        System.out.println("The check out date is: " + actualValueOfCheckOutDate);

        String expectedValueOfCheckOutDate = "29 Aug 2024";
        System.out.println("Expected Check out date is: " + expectedValueOfCheckOutDate);

        Assert.assertEquals(actualValueOfCheckOutDate, expectedValueOfCheckOutDate,"Check out date does not corespond!");

        //for number of adults

        WebElement adultsNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
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
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/%3FcheckIn%3D1724198400000%26checkOut%3D1724889600000%26adults%3D2%26children%3D1";
        Assert.assertEquals(expectedRoomsPageURL, actualRoomsPageURL, "The Rooms page does not load!");
    }

    @Test
    public void testSearchButtonInvalidInput() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        //Check if the Search button is displayed
        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search.s-button")));
        // Verifică dacă este vizibil
        Assert.assertTrue(searchButton.isDisplayed(), "Search button is not displayed");

        //Entor no data
        //Click on search button
        searchButton.click();

        WebElement calendarCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".calendar-popup.s-field.s-separator.visible")));
        Assert.assertTrue(calendarCheckIn.isDisplayed(), "Calendar did not open as expected");

        //Enter invalid data into Check In, Check Out, Adults & Kids(just check in date)

        //for check in

        //click on a chosen date
        //se schimba xpath!!!!!(de pe data)
        WebElement checkInDayButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='21, Wednesday August 2024']")));
        Assert.assertTrue(checkInDayButton.isDisplayed(), "Check in calendar day button is not displayed");
        checkInDayButton.click();

        WebElement checkInDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        String actualValueOfCheckInDate = checkInDate.getText();
        System.out.println("The check in day is: " + actualValueOfCheckInDate);

        String expectedValueOfCheckInDate = "21 Aug 2024";
        System.out.println("Expected Check in date is: " + expectedValueOfCheckInDate);

        Assert.assertEquals(actualValueOfCheckInDate, expectedValueOfCheckInDate,"Check in date date does not corespond!");

        //Click on search button again
        searchButton.click();

        WebElement calendarCheckOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div")));
        Assert.assertTrue(calendarCheckOut.isDisplayed(), "Calendar did not open as expected");

    }

    @Test
    public void testClearButton(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        //Check if the Search button is displayed
        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search.s-button")));
        // Verifică dacă este vizibil
        Assert.assertTrue(searchButton.isDisplayed(), "Search button is not displayed");

        //Enter valid data into Check In, Check Out, Adults & Kids

        //for check in
        WebElement calendarCheckInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("check-in")));
        Assert.assertTrue(calendarCheckInButton.isDisplayed(), "Check in calendar button is not displayed");

        calendarCheckInButton.click();

        WebElement calendarCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".calendar-popup.s-field.s-separator.visible")));
        Assert.assertTrue(calendarCheckIn.isDisplayed(), "Calendar did not open as expected");

        //click on a chosen date
        //se schimba xpath!!!!!(de pe data)
        WebElement checkInDayButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='21, Wednesday August 2024']")));
        Assert.assertTrue(checkInDayButton.isDisplayed(), "Check in calendar day button is not displayed");
        checkInDayButton.click();

        WebElement checkInDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        String actualValueOfCheckInDate = checkInDate.getText();
        System.out.println("The check in day is: " + actualValueOfCheckInDate);

        String expectedValueOfCheckInDate = "21 Aug 2024";
        System.out.println("Expected Check in date is: " + expectedValueOfCheckInDate);

        Assert.assertEquals(actualValueOfCheckInDate, expectedValueOfCheckInDate,"Check in date date does not corespond!");

        //for check out

        WebElement calendarCheckOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div")));
        Assert.assertTrue(calendarCheckOut.isDisplayed(), "Calendar did not open as expected");

        //click on a chosen date
        //se schimba xpath(data de pe calendar)
        WebElement checkOutDay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div/div[2]/table/tbody/tr[5]/td[5]/button")));
        Assert.assertTrue(checkOutDay.isDisplayed(), "Check out calendar day button is not displayed");
        checkOutDay.click();

        //WebElement checkOutDayButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='29, Thursday August 2024']")));
        //Assert.assertTrue(checkOutDayButton.isDisplayed(), "Check out calendar day button is not displayed");
        //checkOutDayButton.click();

        WebElement checkOutDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_out-value")));
        String actualValueOfCheckOutDate = checkOutDate.getText();
        System.out.println("The check out date is: " + actualValueOfCheckOutDate);

        String expectedValueOfCheckOutDate = "29 Aug 2024";
        System.out.println("Expected Check out date is: " + expectedValueOfCheckOutDate);

        Assert.assertEquals(actualValueOfCheckOutDate, expectedValueOfCheckOutDate,"Check out date does not corespond!");

        //for number of adults

        WebElement adultsNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
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
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/%3FcheckIn%3D1724198400000%26checkOut%3D1724889600000%26adults%3D2%26children%3D1";
        Assert.assertEquals(expectedRoomsPageURL, actualRoomsPageURL, "The Rooms page does not load!");

        //Check if the Clear button exists
        WebElement clearButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".back.s-button-color")));
        Assert.assertTrue(clearButton.isDisplayed(), "Clear button is not displayed!");

        //Click on the clear button
        clearButton.click();

        //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        // Așteaptă ca URL-ul să se schimbe în cel așteptat
        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms"));

        System.out.println("URL of the Rooms page after clear is: " + driver.getCurrentUrl());
        String actualRoomsPageURLAfterClear = driver.getCurrentUrl();
        String expectedRoomsPageURLAfterClear = "https://ancabota09.wixsite.com/intern/rooms/rooms";
        Assert.assertEquals(actualRoomsPageURLAfterClear, expectedRoomsPageURLAfterClear,  "The Rooms page does not load!");

    }

    @Test
    public void testStandardSuiteButtonName (){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);


        //Check if the Standard suite name button exists
        WebElement standardSuiteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[1]/div/div[2]/div[1]/h3/a/span")));
        Assert.assertTrue(standardSuiteButton.isDisplayed(), "Standard Suite button is not displayed");

        //Click on the Standard suite name button
        standardSuiteButton.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4"));

        System.out.println("URL of the Standard Suite room is : " + driver.getCurrentUrl());
        String actualRoomsPageURL = driver.getCurrentUrl();
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4";
        Assert.assertEquals(actualRoomsPageURL, expectedRoomsPageURL,  "The Standard Suite room page does not load!");


    }

    @Test
    public void testCottageButtonName (){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        //Check if the Cottage name button exists
        WebElement cottageButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[2]/div/div[2]/div[1]/h3/a/span")));
        Assert.assertTrue(cottageButton.isDisplayed(), "Standard Suite button is not displayed");

        //Click on the Cottage name button
        cottageButton.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/4e2820f3-0564-4bd0-9258-e7594d617297"));

        System.out.println("URL of the Standard Suite room is : " + driver.getCurrentUrl());
        String actualRoomsPageURL = driver.getCurrentUrl();
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/4e2820f3-0564-4bd0-9258-e7594d617297";
        Assert.assertEquals(actualRoomsPageURL, expectedRoomsPageURL,  "The Standard Suite room page does not load!");


    }

    @Test
    public void testClasicAppButtonName (){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        //Check if the Standard suite name button exists
        WebElement clasicAppButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[3]/div/div[2]/div[1]/h3/a/span")));
        Assert.assertTrue(clasicAppButton.isDisplayed(), "Standard Suite button is not displayed");

        //Click on the Standard suite name button
        clasicAppButton.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/1739582a-003e-49e7-a9e6-b6fdb55a9027"));

        System.out.println("URL of the Standard Suite room is : " + driver.getCurrentUrl());
        String actualRoomsPageURL = driver.getCurrentUrl();
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/1739582a-003e-49e7-a9e6-b6fdb55a9027";
        Assert.assertEquals(actualRoomsPageURL, expectedRoomsPageURL,  "The Standard Suite room page does not load!");

    }

    @Test
    public void testStandardSuiteButtonImage (){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);


        //Check if the Standard suite name button exists
        WebElement standardSuiteImageButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[1]/div/div[1]/img")));
        Assert.assertTrue(standardSuiteImageButton.isDisplayed(), "Standard Suite button is not displayed");

        //Click on the Standard suite name button
        standardSuiteImageButton.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4"));

        System.out.println("URL of the Standard Suite room is : " + driver.getCurrentUrl());
        String actualRoomsPageURL = driver.getCurrentUrl();
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4";
        Assert.assertEquals(actualRoomsPageURL, expectedRoomsPageURL,  "The Standard Suite room page does not load!");

    }

    @Test
    public void testCottageButtonImage (){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        //Check if the Cottage name button exists
        WebElement cottageImageButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[2]/div/div[1]/img")));
        Assert.assertTrue(cottageImageButton.isDisplayed(), "Standard Suite button is not displayed");

        //Click on the Cottage name button
        cottageImageButton.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/4e2820f3-0564-4bd0-9258-e7594d617297"));

        System.out.println("URL of the Standard Suite room is : " + driver.getCurrentUrl());
        String actualRoomsPageURL = driver.getCurrentUrl();
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/4e2820f3-0564-4bd0-9258-e7594d617297";
        Assert.assertEquals(actualRoomsPageURL, expectedRoomsPageURL,  "The Standard Suite room page does not load!");

    }

    @Test
    public void testClasicAppButtonImage (){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        //Check if the Standard suite name button exists
        WebElement clasicAppImageButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[3]/div/div[1]/img")));
        Assert.assertTrue(clasicAppImageButton.isDisplayed(), "Standard Suite button is not displayed");

        //Click on the Standard suite name button
        clasicAppImageButton.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/1739582a-003e-49e7-a9e6-b6fdb55a9027"));

        System.out.println("URL of the Standard Suite room is : " + driver.getCurrentUrl());
        String actualRoomsPageURL = driver.getCurrentUrl();
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/1739582a-003e-49e7-a9e6-b6fdb55a9027";
        Assert.assertEquals(actualRoomsPageURL, expectedRoomsPageURL,  "The Standard Suite room page does not load!");

    }

    @Test
    public void testStandardSuiteMoreInfoButton (){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);


        //Check if the Standard suite name button exists
        WebElement standardSuiteMoreInfoButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[1]/div/div[2]/div[4]/button/span")));
        Assert.assertTrue(standardSuiteMoreInfoButton.isDisplayed(), "Standard Suite button is not displayed");

        //Click on the Standard suite name button
        standardSuiteMoreInfoButton.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4"));

        System.out.println("URL of the Standard Suite room is : " + driver.getCurrentUrl());
        String actualRoomsPageURL = driver.getCurrentUrl();
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4";
        Assert.assertEquals(actualRoomsPageURL, expectedRoomsPageURL,  "The Standard Suite room page does not load!");

    }

    @Test
    public void testCottageMoreInfoButton (){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        //Check if the Cottage name button exists
        WebElement cottageMoreInfoButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[2]/div/div[2]/div[4]/button/span")));
        Assert.assertTrue(cottageMoreInfoButton.isDisplayed(), "Standard Suite button is not displayed");

        //Click on the Cottage name button
        cottageMoreInfoButton.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/4e2820f3-0564-4bd0-9258-e7594d617297"));

        System.out.println("URL of the Standard Suite room is : " + driver.getCurrentUrl());
        String actualRoomsPageURL = driver.getCurrentUrl();
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/4e2820f3-0564-4bd0-9258-e7594d617297";
        Assert.assertEquals(actualRoomsPageURL, expectedRoomsPageURL,  "The Standard Suite room page does not load!");

    }

    @Test
    public void testClasicAppMoreInfoButton(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        //Check if the Standard suite name button exists
        WebElement clasicAppMoreInfoButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[3]/div/div[2]/div[4]/button/span")));
        Assert.assertTrue(clasicAppMoreInfoButton.isDisplayed(), "Standard Suite button is not displayed");

        //Click on the Standard suite name button
        clasicAppMoreInfoButton.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/1739582a-003e-49e7-a9e6-b6fdb55a9027"));

        System.out.println("URL of the Standard Suite room is : " + driver.getCurrentUrl());
        String actualRoomsPageURL = driver.getCurrentUrl();
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/1739582a-003e-49e7-a9e6-b6fdb55a9027";
        Assert.assertEquals(actualRoomsPageURL, expectedRoomsPageURL,  "The Standard Suite room page does not load!");

    }

    @Test
    public void testBook(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);


        //Check if the Standard suite name button exists
        WebElement standardSuiteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[1]/div/div[2]/div[1]/h3/a/span")));
        Assert.assertTrue(standardSuiteButton.isDisplayed(), "Standard Suite button is not displayed");

        //Click on the Standard suite name button
        standardSuiteButton.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4"));

        System.out.println("URL of the Standard Suite room is : " + driver.getCurrentUrl());
        String actualRoomsPageURL = driver.getCurrentUrl();
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4";
        Assert.assertEquals(actualRoomsPageURL, expectedRoomsPageURL,  "The Standard Suite room page does not load!");

        //Select the Check In date
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframe);

        WebElement calendarCheckInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("check-in")));
        Assert.assertTrue(calendarCheckInButton.isDisplayed(), "Check in calendar button is not displayed");

        calendarCheckInButton.click();

        WebElement calendarCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".calendar-popup.s-field.s-separator.visible")));
        Assert.assertTrue(calendarCheckIn.isDisplayed(), "Calendar did not open as expected");

        //click on a chosen date
        //se schimba xpath!!!!!(de pe data)
        WebElement checkInDayButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='21, Wednesday August 2024']")));
        Assert.assertTrue(checkInDayButton.isDisplayed(), "Check in calendar day button is not displayed");
        checkInDayButton.click();

        WebElement checkInDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        String actualValueOfCheckInDate = checkInDate.getText();
        System.out.println("The check in day is: " + actualValueOfCheckInDate);

        String expectedValueOfCheckInDate = "21 Aug 2024";
        System.out.println("Expected Check in date is: " + expectedValueOfCheckInDate);

        Assert.assertEquals(actualValueOfCheckInDate, expectedValueOfCheckInDate,"Check in date date does not corespond!");

        //Select the Check Out date

        WebElement calendarCheckOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[2]/div/form/ul/li[2]/div[2]")));
        Assert.assertTrue(calendarCheckOut.isDisplayed(), "Calendar did not open as expected");

        //click on a chosen date
        //se schimba xpath(data de pe calendar)
        WebElement checkOutDay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[2]/div/form/ul/li[2]/div[2]/div/div[2]/table/tbody/tr[5]/td[5]/button/span")));
        Assert.assertTrue(checkOutDay.isDisplayed(), "Check out calendar day button is not displayed");
        checkOutDay.click();

        WebElement checkOutDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_out-value")));
        String actualValueOfCheckOutDate = checkOutDate.getText();
        System.out.println("The check out date is: " + actualValueOfCheckOutDate);

        String expectedValueOfCheckOutDate = "29 Aug 2024";
        System.out.println("Expected Check out date is: " + expectedValueOfCheckOutDate);

        Assert.assertEquals(actualValueOfCheckOutDate, expectedValueOfCheckOutDate,"Check out date does not corespond!");

        //Select Adults number

        WebElement adultsNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
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

        //Check if the Book Now button exists

        WebElement bookNowButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fancy-btn.s-button.button")));
        Assert.assertTrue(bookNowButton.isDisplayed(), "The Book Now button is not displayed!");

        //Click on the Book Now button
        bookNowButton.click();

        Actions actions = new Actions(driver);
        actions.moveToElement(bookNowButton).perform();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[tooltip]")));
        Assert.assertTrue(message.isDisplayed(),"The message is not displayed!");

        String tooltipText = message.getAttribute("tooltip");
        String expectedTooltipText = "Please contact the hotel directly to book your room.";
        System.out.println("The actual message is: " + tooltipText);

        Assert.assertEquals(tooltipText, expectedTooltipText, "The message is not correct!");
    }

    @Test
    public void testAdultsButton(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);


        //Check if the Standard suite name button exists
        WebElement standardSuiteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[1]/div/div[2]/div[1]/h3/a/span")));
        Assert.assertTrue(standardSuiteButton.isDisplayed(), "Standard Suite button is not displayed");

        //Click on the Standard suite name button
        standardSuiteButton.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4"));

        System.out.println("URL of the Standard Suite room is : " + driver.getCurrentUrl());
        String actualRoomsPageURL = driver.getCurrentUrl();
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4";
        Assert.assertEquals(actualRoomsPageURL, expectedRoomsPageURL,  "The Standard Suite room page does not load!");

        driver.switchTo().defaultContent();
        WebElement iframeRoomPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        driver.switchTo().frame(iframeRoomPage);

        //Increment the Adults counter to maximum

        WebElement maxAdultsNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"singleroom\"]/div[3]/div[2]/div[1]/div/ul/li[1]/span[2]")));
        Assert.assertTrue(maxAdultsNumber.isDisplayed(),"The maximum capacity of the rooms is not displayed!");
        String maxAdultsText = maxAdultsNumber.getText();

        WebElement adultsNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
        Assert.assertTrue(adultsNumber.isDisplayed(), "Adults number is not displayed");

        WebElement incrementAdultsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/a[1]")));
        int numberOfClicksIncrementAdults = Integer.parseInt(maxAdultsText.trim());

        // Derulează la element folosind JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", incrementAdultsButton);

        // Așteaptă puțin pentru ca scroll-ul să se finalizeze
        try {
            Thread.sleep(1000); // Poți ajusta timpul de așteptare după cum e nevoie
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Clic pe butonul de incrementare
        for (int i = 0; i < numberOfClicksIncrementAdults; i++) {
            incrementAdultsButton.click();
        }

        String actualNumberOfAdultsAfterIncrement = adultsNumber.getText();
        String expectedNumberOfAdultsAfterIncrement = String.valueOf(1 + numberOfClicksIncrementAdults);
        System.out.println("Actual number of adults after increment is: " + actualNumberOfAdultsAfterIncrement);
        System.out.println("Expected number of adults after increment is: " + expectedNumberOfAdultsAfterIncrement);
        Assert.assertNotEquals(actualNumberOfAdultsAfterIncrement, expectedNumberOfAdultsAfterIncrement, "The number of adults after increment does not match the expected value!");

    }

    @Test
    public void testPoliciesButton(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);


        //Check if the Standard suite name button exists
        WebElement standardSuiteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[1]/div/div[2]/div[1]/h3/a/span")));
        Assert.assertTrue(standardSuiteButton.isDisplayed(), "Standard Suite button is not displayed");

        //Click on the Standard suite name button
        standardSuiteButton.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4"));

        System.out.println("URL of the Standard Suite room is : " + driver.getCurrentUrl());
        String actualRoomsPageURL = driver.getCurrentUrl();
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4";
        Assert.assertEquals(actualRoomsPageURL, expectedRoomsPageURL,  "The Standard Suite room page does not load!");

        driver.switchTo().defaultContent();
        WebElement iframeRoomPage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        driver.switchTo().frame(iframeRoomPage);

        //Validate that the Read Our Policies button exists

        WebElement policiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("policies")));
        Assert.assertTrue(policiesButton.isDisplayed(),"The policies button is not displayed!");

        //Click on the Read Our Policies button
        policiesButton.click();

        // Switch to the new tab
        String initialWindowHandle = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(initialWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        System.out.println("URL of the Policies page is: " + driver.getCurrentUrl());
        String actualURL = driver.getCurrentUrl();
        String urlPattern = "https://hotels\\.wixapps\\.net/index\\.html\\?pageId=crop&compId=i6klgqap_0&viewerCompId=i6klgqap_0&siteRevision=3&viewMode=site&deviceType=desktop&locale=en&tz=Europe%2FVaduz&regionalLanguage=en&width=980&height=788.*";

        Assert.assertTrue(actualURL.matches(urlPattern), "The wrong page is loaded! URL doesn't match the expected pattern.");

    }

    @Test
    public void testSearchOneNight(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search.s-button")));

        //Enter valid data into Check In, Check Out

        //for check in
        WebElement calendarCheckInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("check-in")));
        Assert.assertTrue(calendarCheckInButton.isDisplayed(), "Check in calendar button is not displayed");

        calendarCheckInButton.click();

        WebElement calendarCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".calendar-popup.s-field.s-separator.visible")));
        Assert.assertTrue(calendarCheckIn.isDisplayed(), "Calendar did not open as expected");

        //click on a chosen date
        //se schimba xpath!!!!!(de pe data)
        WebElement checkInDayButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='21, Wednesday August 2024']")));
        Assert.assertTrue(checkInDayButton.isDisplayed(), "Check in calendar day button is not displayed");
        checkInDayButton.click();

        WebElement checkInDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        String actualValueOfCheckInDate = checkInDate.getText();
        System.out.println("The check in day is: " + actualValueOfCheckInDate);

        String expectedValueOfCheckInDate = "21 Aug 2024";
        System.out.println("Expected Check in date is: " + expectedValueOfCheckInDate);

        Assert.assertEquals(actualValueOfCheckInDate, expectedValueOfCheckInDate,"Check in date date does not corespond!");

        //for check out

        WebElement calendarCheckOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div")));
        Assert.assertTrue(calendarCheckOut.isDisplayed(), "Calendar did not open as expected");

        //click on a chosen date
        //se schimba xpath(data de pe calendar)
        WebElement checkOutDay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div/div[2]/table/tbody/tr[4]/td[5]/button/span")));
        Assert.assertTrue(checkOutDay.isDisplayed(), "Check out calendar day button is not displayed");
        checkOutDay.click();

        WebElement checkOutDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".placeholder.s-field")));
        String actualValueOfCheckOutDate = checkOutDate.getText();
        System.out.println("The check out date is: " + actualValueOfCheckOutDate);

        String expectedValueOfCheckOutDate = "22 Aug 2024";
        System.out.println("Expected Check out date is: " + expectedValueOfCheckOutDate);

        Assert.assertEquals(actualValueOfCheckOutDate, expectedValueOfCheckOutDate,"Check out date does not corespond!");

        //Select 2 Adult

        WebElement adultsNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
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

        //Select 0 Kids

        WebElement kidsNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"children\"]/span[1]")));
        // Verifică dacă este vizibil
        Assert.assertTrue(kidsNumber.isDisplayed(), "Kids number is not displayed");

        WebElement incrementKidsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"children\"]/a[1]")));
        int numberOfClicksIncrementKids = 0;
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
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/%3FcheckIn%3D1724198400000%26checkOut%3D1724457600000%26adults%3D1%26children%3D0";
        Assert.assertEquals(expectedRoomsPageURL, actualRoomsPageURL, "The Rooms page does not load!");
    }

    @Test
    public void testSearch7Adults(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search.s-button")));
        //Assert.assertTrue(searchButton.isDisplayed(), "Search button is not displayed");

        //Enter valid data into Check In, Check Out

        //for check in
        WebElement calendarCheckInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("check-in")));
        Assert.assertTrue(calendarCheckInButton.isDisplayed(), "Check in calendar button is not displayed");

        calendarCheckInButton.click();

        WebElement calendarCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".calendar-popup.s-field.s-separator.visible")));
        Assert.assertTrue(calendarCheckIn.isDisplayed(), "Calendar did not open as expected");

        //click on a chosen date
        //se schimba xpath!!!!!(de pe data)
        WebElement checkInDayButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='21, Wednesday August 2024']")));
        Assert.assertTrue(checkInDayButton.isDisplayed(), "Check in calendar day button is not displayed");
        checkInDayButton.click();

        WebElement checkInDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        String actualValueOfCheckInDate = checkInDate.getText();
        System.out.println("The check in day is: " + actualValueOfCheckInDate);

        String expectedValueOfCheckInDate = "21 Aug 2024";
        System.out.println("Expected Check in date is: " + expectedValueOfCheckInDate);

        Assert.assertEquals(actualValueOfCheckInDate, expectedValueOfCheckInDate,"Check in date date does not corespond!");

        //for check out

        WebElement calendarCheckOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div")));
        Assert.assertTrue(calendarCheckOut.isDisplayed(), "Calendar did not open as expected");

        //click on a chosen date
        //se schimba xpath(data de pe calendar)
        WebElement checkOutDay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div/div[2]/table/tbody/tr[5]/td[5]/button")));
        Assert.assertTrue(checkOutDay.isDisplayed(), "Check out calendar day button is not displayed");
        checkOutDay.click();

        WebElement checkOutDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_out-value")));
        String actualValueOfCheckOutDate = checkOutDate.getText();
        System.out.println("The check out date is: " + actualValueOfCheckOutDate);

        String expectedValueOfCheckOutDate = "29 Aug 2024";
        System.out.println("Expected Check out date is: " + expectedValueOfCheckOutDate);

        Assert.assertEquals(actualValueOfCheckOutDate, expectedValueOfCheckOutDate,"Check out date does not corespond!");

        //Select 7 adults

        WebElement adultsNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
        Assert.assertTrue(adultsNumber.isDisplayed(), "Adults number is not displayed");

        WebElement incrementAdultsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/a[1]")));
        int numberOfClicksIncrementAdults = 6;
        // Clic pe butonul de incrementare
        for (int i = 0; i < numberOfClicksIncrementAdults; i++) {
            incrementAdultsButton.click();
        }

        String actualNumberOfAdultsAfterIncrement = adultsNumber.getText();
        String expectedNumberOfAdultsAfterIncrement = String.valueOf(1 + numberOfClicksIncrementAdults);
        System.out.println("Actual number of adults after increment is: " + actualNumberOfAdultsAfterIncrement);
        System.out.println("Expected number of adults after increment is: " + expectedNumberOfAdultsAfterIncrement);
        Assert.assertEquals(actualNumberOfAdultsAfterIncrement, expectedNumberOfAdultsAfterIncrement, "The number of adults after increment does not match the expected value!");

        //Click on search button
        searchButton.click();
        System.out.println("URL of the Rooms page is: " + driver.getCurrentUrl());
        String actualRoomsPageURL = driver.getCurrentUrl();
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/%3FcheckIn%3D1724198400000%26checkOut%3D1724889600000%26adults%3D7%26children%3D0";
        Assert.assertEquals(expectedRoomsPageURL, actualRoomsPageURL, "The Rooms page does not load!");
    }

    @Test
    public void testAdultsAndKids(){

        //2 adults and 2 kids

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search.s-button")));

        //Enter valid data into Check In, Check Out

        //for check in
        WebElement calendarCheckInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("check-in")));
        Assert.assertTrue(calendarCheckInButton.isDisplayed(), "Check in calendar button is not displayed");

        calendarCheckInButton.click();

        WebElement calendarCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".calendar-popup.s-field.s-separator.visible")));
        Assert.assertTrue(calendarCheckIn.isDisplayed(), "Calendar did not open as expected");

        //click on a chosen date
        //se schimba xpath!!!!!(de pe data)
        WebElement checkInDayButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='21, Wednesday August 2024']")));
        Assert.assertTrue(checkInDayButton.isDisplayed(), "Check in calendar day button is not displayed");
        checkInDayButton.click();

        WebElement checkInDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        String actualValueOfCheckInDate = checkInDate.getText();
        System.out.println("The check in day is: " + actualValueOfCheckInDate);

        String expectedValueOfCheckInDate = "21 Aug 2024";
        System.out.println("Expected Check in date is: " + expectedValueOfCheckInDate);

        Assert.assertEquals(actualValueOfCheckInDate, expectedValueOfCheckInDate,"Check in date date does not corespond!");

        //for check out

        WebElement calendarCheckOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div")));
        Assert.assertTrue(calendarCheckOut.isDisplayed(), "Calendar did not open as expected");

        //click on a chosen date
        //se schimba xpath(data de pe calendar)
        WebElement checkOutDay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div/div[2]/table/tbody/tr[5]/td[5]/button")));
        Assert.assertTrue(checkOutDay.isDisplayed(), "Check out calendar day button is not displayed");
        checkOutDay.click();

        WebElement checkOutDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_out-value")));
        String actualValueOfCheckOutDate = checkOutDate.getText();
        System.out.println("The check out date is: " + actualValueOfCheckOutDate);

        String expectedValueOfCheckOutDate = "29 Aug 2024";
        System.out.println("Expected Check out date is: " + expectedValueOfCheckOutDate);

        Assert.assertEquals(actualValueOfCheckOutDate, expectedValueOfCheckOutDate,"Check out date does not corespond!");

        //Select 2 Adult

        WebElement adultsNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
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

        //Select 2 Kids

        WebElement kidsNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"children\"]/span[1]")));
        // Verifică dacă este vizibil
        Assert.assertTrue(kidsNumber.isDisplayed(), "Kids number is not displayed");

        WebElement incrementKidsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"children\"]/a[1]")));
        int numberOfClicksIncrementKids = 2;
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
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/%3FcheckIn%3D1724198400000%26checkOut%3D1724889600000%26adults%3D2%26children%3D2";
        Assert.assertEquals(expectedRoomsPageURL, actualRoomsPageURL, "The Rooms page does not load!");
    }

    @Test
    public void testCorrectBookingDetails(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search.s-button")));

        //Enter valid data into Check In, Check Out

        //for check in
        WebElement calendarCheckInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("check-in")));
        Assert.assertTrue(calendarCheckInButton.isDisplayed(), "Check in calendar button is not displayed");

        calendarCheckInButton.click();

        WebElement calendarCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".calendar-popup.s-field.s-separator.visible")));
        Assert.assertTrue(calendarCheckIn.isDisplayed(), "Calendar did not open as expected");

        //click on a chosen date
        //se schimba xpath!!!!!(de pe data)
        WebElement checkInDayButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='21, Wednesday August 2024']")));
        Assert.assertTrue(checkInDayButton.isDisplayed(), "Check in calendar day button is not displayed");
        checkInDayButton.click();

        WebElement checkInDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        String actualValueOfCheckInDate = checkInDate.getText();
        System.out.println("The check in day is: " + actualValueOfCheckInDate);

        String expectedValueOfCheckInDate = "21 Aug 2024";
        System.out.println("Expected Check in date is: " + expectedValueOfCheckInDate);

        Assert.assertEquals(actualValueOfCheckInDate, expectedValueOfCheckInDate,"Check in date date does not corespond!");

        //for check out

        WebElement calendarCheckOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div")));
        Assert.assertTrue(calendarCheckOut.isDisplayed(), "Calendar did not open as expected");

        //click on a chosen date
        //se schimba xpath(data de pe calendar)
        WebElement checkOutDay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div/div[2]/table/tbody/tr[5]/td[5]/button")));
        Assert.assertTrue(checkOutDay.isDisplayed(), "Check out calendar day button is not displayed");
        checkOutDay.click();

        WebElement checkOutDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_out-value")));
        String actualValueOfCheckOutDate = checkOutDate.getText();
        System.out.println("The check out date is: " + actualValueOfCheckOutDate);

        String expectedValueOfCheckOutDate = "29 Aug 2024";
        System.out.println("Expected Check out date is: " + expectedValueOfCheckOutDate);

        Assert.assertEquals(actualValueOfCheckOutDate, expectedValueOfCheckOutDate,"Check out date does not corespond!");

        //Select valid Adults number(2Adults)

        WebElement adultsNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
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

        //Select valid Kids number(0 Kids)

        WebElement kidsNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"children\"]/span[1]")));
        // Verifică dacă este vizibil
        Assert.assertTrue(kidsNumber.isDisplayed(), "Kids number is not displayed");

        WebElement incrementKidsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"children\"]/a[1]")));
        int numberOfClicksIncrementKids = 0;
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
        System.out.println("URL of the Standard Suite page is: " + driver.getCurrentUrl());
        String actualRoomsPageURL = driver.getCurrentUrl();
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/%3FcheckIn%3D1724198400000%26checkOut%3D1724889600000%26adults%3D2%26children%3D0";
        Assert.assertEquals(expectedRoomsPageURL, actualRoomsPageURL, "The Rooms page does not load!");

        //Click on one of the rooms resulted
        //Check if the Standard suite name button exists

        WebElement standardSuiteImageButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div/div[2]/div/ul/li[1]/div/div[1]/img")));
        Assert.assertTrue(standardSuiteImageButton.isDisplayed(), "Standard Suite button is not displayed");

        //Click on the Standard suite name button
        standardSuiteImageButton.click();

        wait.until(ExpectedConditions.urlToBe("https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4%3FcheckIn%3D1724198400000%26checkOut%3D1724889600000%26adults%3D2%26children%3D0%26price%3D1"));

        System.out.println("URL of the Standard Suite room is : " + driver.getCurrentUrl());
        String actualStandardSuitePageURL = driver.getCurrentUrl();
        String expectedStandardSuitePageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/afda6ba1-efd1-4432-bd42-dd678bd4beb4%3FcheckIn%3D1724198400000%26checkOut%3D1724889600000%26adults%3D2%26children%3D0%26price%3D1";
        Assert.assertEquals(actualStandardSuitePageURL, expectedStandardSuitePageURL,  "The Standard Suite room page does not load!");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.switchTo().defaultContent();
        WebElement iframeSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6klgqap_0\"]/iframe")));
        driver.switchTo().frame(iframeSearch);

        WebElement roomSearchDataResults = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".widget.s-separator.s-background2.breakdown-widget")));
        Assert.assertTrue(roomSearchDataResults.isDisplayed(),"The search data is not displayed!");

        //Check the Check In date

        WebElement checkInResultDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        String actualValueOfCheckInResultDate = checkInResultDate.getText();
        System.out.println("The check in result day is: " + actualValueOfCheckInResultDate);

        String expectedValueOfCheckInResultDate = "21 Aug 2024";
        System.out.println("Expected Check in result date is: " + expectedValueOfCheckInResultDate);

        Assert.assertEquals(actualValueOfCheckInResultDate, expectedValueOfCheckInResultDate,"Check in result date is not correct!");

        //Check the Check Out date

        WebElement checkOutResultDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_out-value")));
        String actualValueOfCheckOutResultDate = checkOutResultDate.getText();
        System.out.println("The check out result day is: " + actualValueOfCheckOutResultDate);

        String expectedValueOfCheckOutResultDate = "29 Aug 2024";
        System.out.println("Expected Check out result date is: " + expectedValueOfCheckOutResultDate);

        Assert.assertEquals(actualValueOfCheckOutResultDate, expectedValueOfCheckOutResultDate,"Check out result date is not correct!");

        //Check the adults number

        WebElement resultNumberOfAdults = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
        String actualResultNumberOfAdults = resultNumberOfAdults.getText();
        System.out.println("The actual result number of adults is: " + actualResultNumberOfAdults);

        String expectedResultNumberOfAdults = "2";
        System.out.println("The expected result number of adults is: " + expectedResultNumberOfAdults);

        Assert.assertEquals(actualResultNumberOfAdults, expectedResultNumberOfAdults,"The number of adults resulted is not correct!");

        //Check the kids number

//        WebElement resultNumberOfKids = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("")));
//        String actualResultNumberOfKids = resultNumberOfKids.getText();
//        System.out.println("The actual result number of kids is: " + actualResultNumberOfKids);
//
//        String expectedResultNumberOfKids = "0";
//        System.out.println("The expected result number of kids is: " + expectedResultNumberOfKids);
//
//        Assert.assertEquals(actualResultNumberOfKids, expectedResultNumberOfKids,"The number of kids resulted is not correct!");

        //Check the cost per night
        WebElement oneNightCost = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[1]/span[2]")));
        Assert.assertTrue(oneNightCost.isDisplayed(),"The cost is not displayed!");

        String oneNightCostString = oneNightCost.getText();
        // Elimină simbolurile de monedă și convertește în double
        double oneNightPrice = Double.parseDouble(oneNightCostString.replaceAll("[^\\d.]", "").trim());
        System.out.println("Cost per night: " + oneNightPrice);

        //Check total nights x cost per night (numarul de nopti)
        WebElement totalNightsAndCost = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[3]/table/tbody/tr[1]/td[1]")));
        Assert.assertTrue(totalNightsAndCost.isDisplayed(),"The total number of nights is not displayed!");

        String totalNightsAndCostString = totalNightsAndCost.getText();
        System.out.println("Total nights and cost string: " + totalNightsAndCostString);

        Assert.assertTrue(totalNightsAndCostString.contains(String.valueOf(oneNightCost)), "The cost per night is not found in the total nights and cost string!");

        //
        WebElement totalNights = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[3]/table/tbody/tr[1]/td[1]/span[2]")));
        Assert.assertTrue(totalNights.isDisplayed(),"The total number of nights is not displayed!");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

        LocalDate checkIn = LocalDate.parse(actualValueOfCheckInResultDate, formatter);
        LocalDate checkOut = LocalDate.parse(actualValueOfCheckOutResultDate, formatter);

        long expectedNights = ChronoUnit.DAYS.between(checkIn, checkOut);
        System.out.println("Total number of nights: " + expectedNights);

        String totalNightsString = totalNights.getText();
        long actualNights =  Long.parseLong(totalNightsString.trim());

        Assert.assertEquals(actualNights, expectedNights, "Number of nights is not correct!");

        //Check the Total amount of costs

        WebElement totalCost = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"singleroom\"]/div[2]/div[3]/table/tbody/tr[2]/td[2]")));
        Assert.assertTrue(totalCost.isDisplayed(),"The total cost is not displayed!");

        //String oneNightCostString = oneNightCost.getText();
        //double oneNightPrice = Double.parseDouble(oneNightCostString.replaceAll("[^\\d.]", "").trim());

        double expectedTotalCost = oneNightPrice * actualNights;
        System.out.println("Expected total cost: " + expectedTotalCost);

        String totalCostString = totalCost.getText();
        double actualTotalCost = Double.parseDouble(totalCostString.replaceAll("[^\\d.]", "").trim());

        Assert.assertEquals(actualTotalCost, expectedTotalCost, "The total cost is not correct!");

    }

    @Test
    public void testBookMoreThan180Days(){

        //for 181 days
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search.s-button")));

        // Get today's date and the date 181 days later
        LocalDate today = LocalDate.now();
        LocalDate checkOutDate = today.plusDays(181);

        // Calculate the number of months to move forward
        int monthsDifference = checkOutDate.getMonthValue() - today.getMonthValue()
                + 12 * (checkOutDate.getYear() - today.getYear());

        // Format the dates to match the format used by the calendar
        String formattedToday = today.getDayOfMonth() + ", " +
                today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " +
                today.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " +
                today.getYear();

        String formattedCheckOut = checkOutDate.getDayOfMonth() + ", " +
                checkOutDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " +
                checkOutDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " +
                checkOutDate.getYear();

        // For check-in (today)
        WebElement calendarCheckInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("check-in")));
        Assert.assertTrue(calendarCheckInButton.isDisplayed(), "Check in calendar button is not displayed");

        calendarCheckInButton.click();

        WebElement calendarCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".calendar-popup.s-field.s-separator.visible")));
        Assert.assertTrue(calendarCheckIn.isDisplayed(), "Calendar did not open as expected");

        // Construct the XPath using the formatted date for check-in
        WebElement checkInDayButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='" + formattedToday + "']")));
        Assert.assertTrue(checkInDayButton.isDisplayed(), "Check in calendar day button is not displayed");
        checkInDayButton.click();

        WebElement checkInDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        String actualValueOfCheckInDate = checkInDate.getText();
        System.out.println("The check in day is: " + actualValueOfCheckInDate);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        String expectedValueOfCheckInDate = today.format(outputFormatter);

        Assert.assertEquals(actualValueOfCheckInDate, expectedValueOfCheckInDate, "Check in date does not correspond!");

        // For check-out (181 days later)
        WebElement calendarCheckOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("check-out")));
        Assert.assertTrue(calendarCheckOutButton.isDisplayed(), "Check out calendar button is not displayed");

        //calendarCheckOutButton.click();

        WebElement nextMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div/nav/button[2]")));
        Assert.assertTrue(nextMonthButton.isDisplayed(),"The next month button in the calendar is not displayed!");

        WebElement calendarCheckOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".calendar-popup.s-field.s-separator.visible")));
        Assert.assertTrue(calendarCheckOut.isDisplayed(), "Calendar did not open as expected");

        // Click the nextMonthButton the required number of times
        for (int i = 0; i < monthsDifference; i++) {
            nextMonthButton.click();
        }

        // Construct the XPath using the formatted date for check-out
        WebElement checkOutDayButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='" + formattedCheckOut + "']")));
        Assert.assertTrue(checkOutDayButton.isDisplayed(), "Check out calendar day button is not displayed");
        checkOutDayButton.click();

        WebElement checkOutDateField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_out-value")));
        String actualValueOfCheckOutDate = checkOutDateField.getText();
        System.out.println("The check out date is: " + actualValueOfCheckOutDate);

        String expectedValueOfCheckOutDate = checkOutDate.format(outputFormatter);

        Assert.assertEquals(actualValueOfCheckOutDate, expectedValueOfCheckOutDate, "Check out date does not correspond!");

        //Click Search button
        searchButton.click();
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-danger.clearfix")));
        System.out.println("Thre error message is: " + errorMessage.getText());
        Assert.assertTrue(errorMessage.isDisplayed(),"The error message is not displayed!");

    }

    @Test
    public void testBook30Days(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search.s-button")));

        // Get today's date and the date 30 days later
        LocalDate today = LocalDate.now();
        LocalDate checkOutDate = today.plusDays(30);

        // Format the dates to match the format used by the calendar
        String formattedToday = today.getDayOfMonth() + ", " +
                today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " +
                today.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " +
                today.getYear();

        String formattedCheckOut = checkOutDate.getDayOfMonth() + ", " +
                checkOutDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " +
                checkOutDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " " +
                checkOutDate.getYear();

        // For check-in (today)
        WebElement calendarCheckInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("check-in")));
        Assert.assertTrue(calendarCheckInButton.isDisplayed(), "Check in calendar button is not displayed");

        calendarCheckInButton.click();

        WebElement calendarCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".calendar-popup.s-field.s-separator.visible")));
        Assert.assertTrue(calendarCheckIn.isDisplayed(), "Calendar did not open as expected");

        // Construct the XPath using the formatted date for check-in
        WebElement checkInDayButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='" + formattedToday + "']")));
        Assert.assertTrue(checkInDayButton.isDisplayed(), "Check in calendar day button is not displayed");
        checkInDayButton.click();

        WebElement checkInDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        String actualValueOfCheckInDate = checkInDate.getText();
        System.out.println("The check in day is: " + actualValueOfCheckInDate);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        String expectedValueOfCheckInDate = today.format(outputFormatter);

        Assert.assertEquals(actualValueOfCheckInDate, expectedValueOfCheckInDate, "Check in date does not correspond!");

        // For check-out (30 days later)
        WebElement calendarCheckOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("check-out")));
        Assert.assertTrue(calendarCheckOutButton.isDisplayed(), "Check out calendar button is not displayed");

        WebElement nextMonthButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div/nav/button[2]")));
        Assert.assertTrue(nextMonthButton.isDisplayed(),"The next month button in the calendar is not displayed!");

        WebElement calendarCheckOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".calendar-popup.s-field.s-separator.visible")));
        Assert.assertTrue(calendarCheckOut.isDisplayed(), "Calendar did not open as expected");

        //for 30 days click the nextMonthButton once
        nextMonthButton.click();
        //calendarCheckOutButton.click();

        // Construct the XPath using the formatted date for check-out
        WebElement checkOutDayButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='" + formattedCheckOut + "']")));
        Assert.assertTrue(checkOutDayButton.isDisplayed(), "Check out calendar day button is not displayed");
        checkOutDayButton.click();

        WebElement checkOutDateField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_out-value")));
        String actualValueOfCheckOutDate = checkOutDateField.getText();
        System.out.println("The check out date is: " + actualValueOfCheckOutDate);

        String expectedValueOfCheckOutDate = checkOutDate.format(outputFormatter);

        Assert.assertEquals(actualValueOfCheckOutDate, expectedValueOfCheckOutDate, "Check out date does not correspond!");

        //Click Search button
        searchButton.click();
        System.out.println("URL of the Search result page is: " + driver.getCurrentUrl());
        String actualRoomsPageURL = driver.getCurrentUrl();
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/%3FcheckIn%3D1723593600000%26checkOut%3D1726185600000%26adults%3D1%26children%3D0";
        Assert.assertEquals(expectedRoomsPageURL, actualRoomsPageURL, "The Rooms page does not load!");
    }

    @Test
    public void testHoverOverFacilities(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        //Hover on any amenities icon

        WebElement amenityElement = wait.until(ExpectedConditions.elementToBeClickable(By.className("amenity")));

        // Scroll the element into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", amenityElement);

        // Hover
        Actions actions = new Actions(driver);
        actions.moveToElement(amenityElement).build().perform();

        WebElement tooltipElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".amenity[tooltip]")));
        String actualTooltipText = tooltipElement.getAttribute("tooltip");
        String expectedTooltipText = "A/C"; // Înlocuiește cu textul așteptat
        System.out.println("Actual Tooltip text: " + actualTooltipText);
        Assert.assertEquals(actualTooltipText, expectedTooltipText, "The amenity text is not relevant!");

    }

    @Test
    public void testSearchAgainButton(){

        //search valid inputs

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement roomsButton = driver.findElement(By.id("i6kl732v2label"));
        Assert.assertTrue(roomsButton.isDisplayed(),"The Rooms button is not displayed!");
        roomsButton.click();

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nKphmK")));
        driver.switchTo().frame(iframe);

        //Check if the Search button is displayed
        WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search.s-button")));
        // Verifică dacă este vizibil
        Assert.assertTrue(searchButton.isDisplayed(), "Search button is not displayed");

        //Enter valid data into Check In, Check Out, Adults & Kids

        //for check in
        WebElement calendarCheckInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("check-in")));
        Assert.assertTrue(calendarCheckInButton.isDisplayed(), "Check in calendar button is not displayed");

        calendarCheckInButton.click();

        WebElement calendarCheckIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".calendar-popup.s-field.s-separator.visible")));
        Assert.assertTrue(calendarCheckIn.isDisplayed(), "Calendar did not open as expected");

        //click on a chosen date
        //se schimba xpath!!!!!(de pe data)
        WebElement checkInDayButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='21, Wednesday August 2024']")));
        Assert.assertTrue(checkInDayButton.isDisplayed(), "Check in calendar day button is not displayed");
        checkInDayButton.click();

        WebElement checkInDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_in-value")));
        String actualValueOfCheckInDate = checkInDate.getText();
        System.out.println("The check in day is: " + actualValueOfCheckInDate);

        String expectedValueOfCheckInDate = "21 Aug 2024";
        System.out.println("Expected Check in date is: " + expectedValueOfCheckInDate);

        Assert.assertEquals(actualValueOfCheckInDate, expectedValueOfCheckInDate,"Check in date date does not corespond!");

        //for check out

        WebElement calendarCheckOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div")));
        Assert.assertTrue(calendarCheckOut.isDisplayed(), "Calendar did not open as expected");

        //click on a chosen date
        //se schimba xpath(data de pe calendar)
        WebElement checkOutDay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div/div[2]/table/tbody/tr[5]/td[5]/button")));
        Assert.assertTrue(checkOutDay.isDisplayed(), "Check out calendar day button is not displayed");
        checkOutDay.click();

        WebElement checkOutDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_out-value")));
        String actualValueOfCheckOutDate = checkOutDate.getText();
        System.out.println("The check out date is: " + actualValueOfCheckOutDate);

        String expectedValueOfCheckOutDate = "29 Aug 2024";
        System.out.println("Expected Check out date is: " + expectedValueOfCheckOutDate);

        Assert.assertEquals(actualValueOfCheckOutDate, expectedValueOfCheckOutDate,"Check out date does not corespond!");

        //for number of adults

        WebElement adultsNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"adults\"]/span[1]")));
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
        String expectedRoomsPageURL = "https://ancabota09.wixsite.com/intern/rooms/rooms/%3FcheckIn%3D1724198400000%26checkOut%3D1724889600000%26adults%3D2%26children%3D1";
        Assert.assertEquals(expectedRoomsPageURL, actualRoomsPageURL, "The Rooms page does not load!");

        //Validate that Search Again button exists

        WebElement searchAgainButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".search-btn")));
        Assert.assertTrue(searchAgainButton.isDisplayed(),"The Search Again button is not displayed!");
        String actualSearchAgainButtonText = searchAgainButton.getText();
        String expectedSearchAgainButtonText = "Search Again";
        System.out.println("The actual text of the search again button is: " + actualSearchAgainButtonText);
        Assert.assertEquals(actualSearchAgainButtonText,expectedSearchAgainButtonText,"The search again button text does not corespond!");

        WebElement results = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".s-title.resultbar.s-separator")));
        String before = results.getText();

        //Change any of the booking details
        //change the checkout to 30 august

        WebElement checkOutCalendarButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"check-out\"]/div[1]")));
        Assert.assertTrue(checkOutCalendarButton.isDisplayed(),"The check out calendar button is not displayed!");
        checkOutCalendarButton.click();

        WebElement checkOutCalendar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".calendar-popup.s-field.s-separator.visible")));
        Assert.assertTrue(checkOutCalendar.isDisplayed(),"The check out calendar is not open!");

        WebElement newCheckOutDay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"hotel-container\"]/section/div/div/form/ul/li[2]/div[2]/div/div[2]/table/tbody/tr[5]/td[6]/button")));
        Assert.assertTrue(newCheckOutDay.isDisplayed(), "New Check out calendar day button is not displayed");
        newCheckOutDay.click();

        WebElement newCheckOutDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("check_out-value")));
        String actualValueOfNewCheckOutDate = newCheckOutDate.getText();
        System.out.println("The new check out date is: " + actualValueOfNewCheckOutDate);

        String expectedValueOfNewCheckOutDate = "30 Aug 2024";
        System.out.println("Expected New Check out date is: " + expectedValueOfNewCheckOutDate);

        Assert.assertEquals(actualValueOfNewCheckOutDate, expectedValueOfNewCheckOutDate,"New Check out date does not corespond!");

        //Click on Search Again button
        System.out.println("Before search again: " + before);

        searchAgainButton.click();

        String after = results.getText();

        System.out.println("After search again: " + after);

        Assert.assertNotEquals(before, after, "The results are the same!It should be different.");
        System.out.println("Page loaded with new search results!");

    }

}

