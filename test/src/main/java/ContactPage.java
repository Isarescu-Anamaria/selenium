import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
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

public class ContactPage {

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
    public void testContactTextBlock() {

        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement contactButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("i6kl732v3label")));

        contactButton.click();

        // Verifică dacă URL-ul curent este cel așteptat
        String expectedURL = "https://ancabota09.wixsite.com/intern/contact";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "The wrong page is loaded!");
        System.out.println("URL of the Contact page is: " + actualURL);

        //Validate that the Contact Us block exists
        WebElement contactBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"bgLayers_mediajg145x8s13\"]/div[1]")));
        Assert.assertTrue(contactBlock.isDisplayed(), "The contact block does not exists!");

        WebElement contactText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6ly3ckc_0\"]")));
        String expectedText = "If you have any questions, please contact us by telephone or email and we'll get back to you as soon as possible.\n" +
                "We look forward to hearing from you.";
        String actualText = contactText.getText();
        System.out.println("The actual text is: " + actualText);
        Assert.assertEquals(actualText, expectedText, "The wrong text is loaded!");
    }

    @Test
    public void testFullScreenMap(){

        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern/contact");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //scroll down
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,350)");

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mapContainer_i6lyzjsh\"]/iframe")));
        driver.switchTo().frame(iframe);

        //Validate that the full screen button exists
        WebElement fullScreenButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".gm-fullscreen-control")));
        Assert.assertTrue(fullScreenButton.isDisplayed(), "The full screen button is not displayed!");

        //Click on the full screen button on the map
        fullScreenButton.click();

        Boolean isFullscreen = (Boolean) jse.executeScript("return document.fullscreenElement != null;");
        Assert.assertTrue(isFullscreen, "The map is not displayed in fullscreen mode");

        driver.switchTo().defaultContent();

    }
    @Test
    public void testMovingMap(){

        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern/contact");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Press the mouse's left button and move through the map
        // Scroll down
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,350)");

        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mapContainer_i6lyzjsh\"]/iframe")));
        driver.switchTo().frame(iframe);

        WebElement fullScreenButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".gm-fullscreen-control")));
        Assert.assertTrue(fullScreenButton.isDisplayed(), "The full screen button is not displayed!");

        fullScreenButton.click();

        Actions actions = new Actions(driver);

        WebElement map = driver.findElement(By.xpath("//*[@id=\"map_canvas\"]/div/div[3]/div[1]/div[1]"));
        String initialPosition = map.getCssValue("transform");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Drag the map in various directions (up, down, left, right)
        actions.clickAndHold().moveByOffset(0, 250).release().perform(); // Drag down
        actions.clickAndHold().moveByOffset(0, -250).release().perform(); // Drag up
        actions.clickAndHold().moveByOffset(250, 0).release().perform(); // Drag right
        actions.clickAndHold().moveByOffset(-250, 0).release().perform(); // Drag left
        //js.executeScript("arguments[0].style.transform = 'translate(0px, 250px)';", map);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String finalPosition = map.getCssValue("transform");
        System.out.println("The initial position of the map is: " + initialPosition);
        System.out.println("The final position of the map is: " + finalPosition);

        Assert.assertNotEquals(initialPosition, finalPosition, "The map did not move!");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.switchTo().defaultContent();
    }

    @Test
    public void testFormValidData(){
        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern/contact");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Scroll down
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,350)");

        //Enter a valid name
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1e9")));
        Assert.assertTrue(nameField.isDisplayed(), "The name filed is not displayed!");

        String nameKey =  "John McGinn";
        nameField.sendKeys(nameKey);

        String actualName = nameField.getAttribute("value");
        System.out.println("The typed name is: " + actualName);
        Assert.assertEquals(actualName, nameKey, "The name does not corespond!");


        //Enter a valid email
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1em")));
        Assert.assertTrue(emailField.isDisplayed(), "The email filed is not displayed!");

        String emailKey =  "johnmcginn@gmail.com";
        emailField.sendKeys(emailKey);

        String actualEmail = emailField.getAttribute("value");
        System.out.println("The typed email is: " + actualEmail);
        Assert.assertEquals(actualEmail, emailKey, "The email does not corespond!");


        //Enter a valid phone number
        WebElement phoneNumberField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1ev")));
        Assert.assertTrue(phoneNumberField.isDisplayed(), "The phone number filed is not displayed!");

        String phoneNumberKey =  "40779852552";
        phoneNumberField.sendKeys(phoneNumberKey);

        String actualPhoneNumber = phoneNumberField.getAttribute("value");
        System.out.println("The typed phone number is: " + actualPhoneNumber);
        Assert.assertEquals(actualPhoneNumber, phoneNumberKey, "The phone number does not corespond!");

        //Enter 100 words text
        WebElement messageField = wait.until(ExpectedConditions.elementToBeClickable(By.id("textarea_comp-jxbsa1f7")));
        Assert.assertTrue(messageField.isDisplayed(), "The message filed is not displayed!");

        String messageKey =  "This is a sample message to test the contact form functionality with a specific number of characters included.";
        messageField.sendKeys(messageKey);

        String actualMessage = messageField.getAttribute("value");
        System.out.println("The typed message is: " + actualMessage);
        Assert.assertEquals(actualMessage, messageKey, "The message does not corespond!");

        //Click Submit button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("comp-jxbsa1fi")));
        Assert.assertTrue(submitButton.isDisplayed(), "The submit button is not displayed!");

        submitButton.click();
        WebElement confirmationMesasge = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"comp-jxbsa1fv\"]/p/span")));
        Assert.assertTrue(confirmationMesasge.isDisplayed(), "The submit button is not displayed!");

        // Verify confirmation message
        String expectedConfirmationMessage = "Thanks for submitting!";
        String actualConfirmationMessage = confirmationMesasge.getText();
        Assert.assertEquals(expectedConfirmationMessage, actualConfirmationMessage, "The confirmation message is not corect!");

        // Verify that the form is cleared
        Assert.assertEquals(nameField.getAttribute("value"), "", "The name field was not cleared!");
        Assert.assertEquals(emailField.getAttribute("value"), "", "The email field was not cleared!");
        Assert.assertEquals(phoneNumberField.getAttribute("value"), "", "The phone number field was not cleared!");
        Assert.assertEquals(messageField.getAttribute("value"), "", "The message field was not cleared!");
    }

    @Test
    public void testNoInputText(){
        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern/contact");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Scroll down
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,350)");

        //Enter valid Name, Mail and Phone number

        //Enter a valid name
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1e9")));
        Assert.assertTrue(nameField.isDisplayed(), "The name filed is not displayed!");

        String nameKey =  "John McGinn";
        nameField.sendKeys(nameKey);

        String actualName = nameField.getAttribute("value");
        System.out.println("The typed name is: " + actualName);
        Assert.assertEquals(actualName, nameKey, "The name does not corespond!");


        //Enter a valid email
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1em")));
        Assert.assertTrue(emailField.isDisplayed(), "The email filed is not displayed!");

        String emailKey =  "johnmcginn@gmail.com";
        emailField.sendKeys(emailKey);

        String actualEmail = emailField.getAttribute("value");
        System.out.println("The typed email is: " + actualEmail);
        Assert.assertEquals(actualEmail, emailKey, "The email does not corespond!");


        //Enter a valid phone number
        WebElement phoneNumberField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1ev")));
        Assert.assertTrue(phoneNumberField.isDisplayed(), "The phone number filed is not displayed!");

        String phoneNumberKey =  "40779852552";
        phoneNumberField.sendKeys(phoneNumberKey);

        String actualPhoneNumber = phoneNumberField.getAttribute("value");
        System.out.println("The typed phone number is: " + actualPhoneNumber);
        Assert.assertEquals(actualPhoneNumber, phoneNumberKey, "The phone number does not corespond!");

        //Click Submit button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("comp-jxbsa1fi")));
        Assert.assertTrue(submitButton.isDisplayed(), "The submit button is not displayed!");

        submitButton.click();
        WebElement messageField = wait.until(ExpectedConditions.elementToBeClickable(By.id("textarea_comp-jxbsa1f7")));

        String validationMessage = messageField.getAttribute("validationMessage");
        System.out.println("The validation message is: " + validationMessage);
        Assert.assertEquals(validationMessage, "Please fill out this field.", "Browser validation message not displayed as expected.");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testNoInputName(){

        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern/contact");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Scroll down
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,350)");

        //Enter valid Mail, Phone number and text

        //Enter a valid email
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1em")));
        Assert.assertTrue(emailField.isDisplayed(), "The email filed is not displayed!");

        String emailKey =  "johnmcginn@gmail.com";
        emailField.sendKeys(emailKey);

        String actualEmail = emailField.getAttribute("value");
        System.out.println("The typed email is: " + actualEmail);
        Assert.assertEquals(actualEmail, emailKey, "The email does not corespond!");


        //Enter a valid phone number
        WebElement phoneNumberField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1ev")));
        Assert.assertTrue(phoneNumberField.isDisplayed(), "The phone number filed is not displayed!");

        String phoneNumberKey =  "40779852552";
        phoneNumberField.sendKeys(phoneNumberKey);

        String actualPhoneNumber = phoneNumberField.getAttribute("value");
        System.out.println("The typed phone number is: " + actualPhoneNumber);
        Assert.assertEquals(actualPhoneNumber, phoneNumberKey, "The phone number does not corespond!");

        //Enter 100 words text
        WebElement messageField = wait.until(ExpectedConditions.elementToBeClickable(By.id("textarea_comp-jxbsa1f7")));
        Assert.assertTrue(messageField.isDisplayed(), "The message filed is not displayed!");

        String messageKey =  "This is a sample message to test the contact form functionality with a specific number of characters included.";
        messageField.sendKeys(messageKey);

        String actualMessage = messageField.getAttribute("value");
        System.out.println("The typed message is: " + actualMessage);
        Assert.assertEquals(actualMessage, messageKey, "The message does not corespond!");

        //Click Submit button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("comp-jxbsa1fi")));
        Assert.assertTrue(submitButton.isDisplayed(), "The submit button is not displayed!");

        submitButton.click();

        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1e9")));

        String validationMessage = nameField.getAttribute("validationMessage");
        System.out.println("The validation message is: " + validationMessage);
        Assert.assertEquals(validationMessage, "Please fill out this field.", "Browser validation message not displayed as expected.");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testNoInputEmail(){

        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern/contact");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Scroll down
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,350)");

        //Enter valid Name, Phone number and text

        //Enter a valid name
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1e9")));
        Assert.assertTrue(nameField.isDisplayed(), "The name filed is not displayed!");

        String nameKey =  "John McGinn";
        nameField.sendKeys(nameKey);

        String actualName = nameField.getAttribute("value");
        System.out.println("The typed name is: " + actualName);
        Assert.assertEquals(actualName, nameKey, "The name does not corespond!");

        //Enter a valid phone number
        WebElement phoneNumberField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1ev")));
        Assert.assertTrue(phoneNumberField.isDisplayed(), "The phone number filed is not displayed!");

        String phoneNumberKey =  "40779852552";
        phoneNumberField.sendKeys(phoneNumberKey);

        String actualPhoneNumber = phoneNumberField.getAttribute("value");
        System.out.println("The typed phone number is: " + actualPhoneNumber);
        Assert.assertEquals(actualPhoneNumber, phoneNumberKey, "The phone number does not corespond!");

        //Enter 100 words text
        WebElement messageField = wait.until(ExpectedConditions.elementToBeClickable(By.id("textarea_comp-jxbsa1f7")));
        Assert.assertTrue(messageField.isDisplayed(), "The message filed is not displayed!");

        String messageKey =  "This is a sample message to test the contact form functionality with a specific number of characters included.";
        messageField.sendKeys(messageKey);

        String actualMessage = messageField.getAttribute("value");
        System.out.println("The typed message is: " + actualMessage);
        Assert.assertEquals(actualMessage, messageKey, "The message does not corespond!");

        //Click Submit button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("comp-jxbsa1fi")));
        Assert.assertTrue(submitButton.isDisplayed(), "The submit button is not displayed!");

        submitButton.click();

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1em")));

        String validationMessage = emailField.getAttribute("validationMessage");
        System.out.println("The validation message is: " + validationMessage);
        Assert.assertEquals(validationMessage, "Please fill out this field.", "Browser validation message not displayed as expected.");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
    }

    @Test
    public void testNoInputPhoneNumber(){

        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern/contact");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Scroll down
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,350)");

        //Enter a valid name
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1e9")));
        Assert.assertTrue(nameField.isDisplayed(), "The name filed is not displayed!");

        String nameKey =  "John McGinn";
        nameField.sendKeys(nameKey);

        String actualName = nameField.getAttribute("value");
        System.out.println("The typed name is: " + actualName);
        Assert.assertEquals(actualName, nameKey, "The name does not corespond!");


        //Enter a valid email
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1em")));
        Assert.assertTrue(emailField.isDisplayed(), "The email filed is not displayed!");

        String emailKey =  "johnmcginn@gmail.com";
        emailField.sendKeys(emailKey);

        String actualEmail = emailField.getAttribute("value");
        System.out.println("The typed email is: " + actualEmail);
        Assert.assertEquals(actualEmail, emailKey, "The email does not corespond!");

        //Enter 100 words text
        WebElement messageField = wait.until(ExpectedConditions.elementToBeClickable(By.id("textarea_comp-jxbsa1f7")));
        Assert.assertTrue(messageField.isDisplayed(), "The message filed is not displayed!");

        String messageKey =  "This is a sample message to test the contact form functionality with a specific number of characters included.";
        messageField.sendKeys(messageKey);

        String actualMessage = messageField.getAttribute("value");
        System.out.println("The typed message is: " + actualMessage);
        Assert.assertEquals(actualMessage, messageKey, "The message does not corespond!");

        //Click Submit button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("comp-jxbsa1fi")));
        Assert.assertTrue(submitButton.isDisplayed(), "The submit button is not displayed!");

        submitButton.click();

        WebElement phoneNumberField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1ev")));

        String validationMessage = phoneNumberField.getAttribute("validationMessage");
        System.out.println("The validation message is: " + validationMessage);
        Assert.assertEquals(validationMessage, "Please fill out this field.", "Browser validation message not displayed as expected.");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testInvalidInputName(){

        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern/contact");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Scroll down
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,350)");

        //Enter invalid name with letters, numbers and special characters
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1e9")));
        Assert.assertTrue(nameField.isDisplayed(), "The name filed is not displayed!");

        String nameKey =  "castc2164`";
        nameField.sendKeys(nameKey);

        String actualName = nameField.getAttribute("value");
        System.out.println("The typed name is: " + actualName);
        Assert.assertEquals(actualName, nameKey, "The name does not corespond!");

        String validationMessageInvalidName = nameField.getAttribute("validationMessage");
        System.out.println("The validation message is: " + validationMessageInvalidName);
        Assert.assertEquals(validationMessageInvalidName, "Cannot type numbers or special characters.", "Browser validation message not displayed as expected.");

        //Enter valid Mail, Phone number and text
        //Enter a valid email
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1em")));
        Assert.assertTrue(emailField.isDisplayed(), "The email filed is not displayed!");

        String emailKey =  "johnmcginn@gmail.com";
        emailField.sendKeys(emailKey);

        String actualEmail = emailField.getAttribute("value");
        System.out.println("The typed email is: " + actualEmail);
        Assert.assertEquals(actualEmail, emailKey, "The email does not corespond!");


        //Enter a valid phone number
        WebElement phoneNumberField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1ev")));
        Assert.assertTrue(phoneNumberField.isDisplayed(), "The phone number filed is not displayed!");

        String phoneNumberKey =  "40779852552";
        phoneNumberField.sendKeys(phoneNumberKey);

        String actualPhoneNumber = phoneNumberField.getAttribute("value");
        System.out.println("The typed phone number is: " + actualPhoneNumber);
        Assert.assertEquals(actualPhoneNumber, phoneNumberKey, "The phone number does not corespond!");

        //Enter 100 words text
        WebElement messageField = wait.until(ExpectedConditions.elementToBeClickable(By.id("textarea_comp-jxbsa1f7")));
        Assert.assertTrue(messageField.isDisplayed(), "The message filed is not displayed!");

        String messageKey =  "This is a sample message to test the contact form functionality with a specific number of characters included.";
        messageField.sendKeys(messageKey);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        String actualMessage = messageField.getAttribute("value");
        System.out.println("The typed message is: " + actualMessage);
        Assert.assertEquals(actualMessage, messageKey, "The message does not corespond!");

        //Click Submit button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("comp-jxbsa1fi")));
        Assert.assertTrue(submitButton.isDisplayed(), "The submit button is not displayed!");

        submitButton.click();

        String validationMessage = nameField.getAttribute("validationMessage");
        System.out.println("The validation message is: " + validationMessage);
        Assert.assertEquals(validationMessage, "Please fill out a correct name.", "Browser validation message not displayed as expected.");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testInvalidInputEmail(){

        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern/contact");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Scroll down
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,350)");

        //Enter invalid email, multiple @ other special characters etc.
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1em")));
        Assert.assertTrue(emailField.isDisplayed(), "The email filed is not displayed!");

        String emailKey =  "joh.nmcgin@yah@oo.com";
        emailField.sendKeys(emailKey);

        String actualEmail = emailField.getAttribute("value");
        System.out.println("The typed email is: " + actualEmail);
        Assert.assertEquals(actualEmail, emailKey, "The email does not corespond!");

        //Enter valid Name, Phone number and text
        //Enter valid name
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1e9")));
        Assert.assertTrue(nameField.isDisplayed(), "The name filed is not displayed!");

        String nameKey =  "John McGinn";
        nameField.sendKeys(nameKey);

        String actualName = nameField.getAttribute("value");
        System.out.println("The typed name is: " + actualName);
        Assert.assertEquals(actualName, nameKey, "The name does not corespond!");

        //Enter a valid phone number
        WebElement phoneNumberField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1ev")));
        Assert.assertTrue(phoneNumberField.isDisplayed(), "The phone number filed is not displayed!");

        String phoneNumberKey =  "40779852552";
        phoneNumberField.sendKeys(phoneNumberKey);

        String actualPhoneNumber = phoneNumberField.getAttribute("value");
        System.out.println("The typed phone number is: " + actualPhoneNumber);
        Assert.assertEquals(actualPhoneNumber, phoneNumberKey, "The phone number does not corespond!");

        //Enter 100 words text
        WebElement messageField = wait.until(ExpectedConditions.elementToBeClickable(By.id("textarea_comp-jxbsa1f7")));
        Assert.assertTrue(messageField.isDisplayed(), "The message filed is not displayed!");

        String messageKey =  "This is a sample message to test the contact form functionality with a specific number of characters included.";
        messageField.sendKeys(messageKey);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        String actualMessage = messageField.getAttribute("value");
        System.out.println("The typed message is: " + actualMessage);
        Assert.assertEquals(actualMessage, messageKey, "The message does not corespond!");

        //Click Submit button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("comp-jxbsa1fi")));
        Assert.assertTrue(submitButton.isDisplayed(), "The submit button is not displayed!");

        submitButton.click();

        String validationMessage = emailField.getAttribute("validationMessage");
        System.out.println("The validation message is: " + validationMessage);
        Assert.assertEquals(validationMessage, "A part following '@' should not contain the symbol '@'.", "Browser validation message not displayed as expected.");
    }

    @Test
    public void testInvalidInputPhoneNumber(){

        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern/contact");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Scroll down
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,350)");

        //Enter invalid phone number: special characters, letters & numbers
        WebElement phoneNumberField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1ev")));
        Assert.assertTrue(phoneNumberField.isDisplayed(), "The phone number filed is not displayed!");

        String phoneNumberKey =  "setegd54yery@@$@";
        phoneNumberField.sendKeys(phoneNumberKey);

        String actualPhoneNumber = phoneNumberField.getAttribute("value");
        System.out.println("The typed phone number is: " + actualPhoneNumber);
        Assert.assertEquals(actualPhoneNumber, phoneNumberKey, "The phone number does not corespond!");

        String validationMessageInvalidPhoneNumber = phoneNumberField.getAttribute("validationMessage");
        System.out.println("The validation message is: " +validationMessageInvalidPhoneNumber);
        Assert.assertEquals(validationMessageInvalidPhoneNumber, "Cannot type letters or special characters.", "Browser validation message not displayed as expected.");

        //Enter valid Name, Mail and text

        //Enter a valid name
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1e9")));
        Assert.assertTrue(nameField.isDisplayed(), "The name filed is not displayed!");

        String nameKey =  "John McGinn";
        nameField.sendKeys(nameKey);

        String actualName = nameField.getAttribute("value");
        System.out.println("The typed name is: " + actualName);
        Assert.assertEquals(actualName, nameKey, "The name does not corespond!");

        //Enter a valid email
        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("input_comp-jxbsa1em")));
        Assert.assertTrue(emailField.isDisplayed(), "The email filed is not displayed!");

        String emailKey =  "johnmcginn@gmail.com";
        emailField.sendKeys(emailKey);

        String actualEmail = emailField.getAttribute("value");
        System.out.println("The typed email is: " + actualEmail);
        Assert.assertEquals(actualEmail, emailKey, "The email does not corespond!");

        //Enter 100 words text
        WebElement messageField = wait.until(ExpectedConditions.elementToBeClickable(By.id("textarea_comp-jxbsa1f7")));
        Assert.assertTrue(messageField.isDisplayed(), "The message filed is not displayed!");

        String messageKey =  "This is a sample message to test the contact form functionality with a specific number of characters included.";
        messageField.sendKeys(messageKey);

        String actualMessage = messageField.getAttribute("value");
        System.out.println("The typed message is: " + actualMessage);
        Assert.assertEquals(actualMessage, messageKey, "The message does not corespond!");

        //Click Submit button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("comp-jxbsa1fi")));
        Assert.assertTrue(submitButton.isDisplayed(), "The submit button is not displayed!");

        submitButton.click();

        String validationMessage = phoneNumberField.getAttribute("validationMessage");
        System.out.println("The validation message is: " + validationMessage);
        Assert.assertEquals(validationMessage, "Please fill out a correct phone number.", "Browser validation message not displayed as expected.");
    }

}
