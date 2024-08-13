import java.io.File;
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

    @Test
    public void testMessage(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().frame(iframe);

        //Validate that the Chat button exists
        WebElement chat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div")));
        //Assert.assertTrue(chat.isDisplayed(), "Chat button is not displayed");

        //Click on the Chat button
        chat.click();

        WebElement textBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/textarea")));
        Assert.assertTrue(textBox.isDisplayed(), "Chat text box is not displayed.The chat does not opened!");

        // Type message into the text field

        String key = "Test Message";
        textBox.sendKeys(key);

        String textBoxMessage = textBox.getAttribute("value");
        System.out.println("The message in the text box is: " + textBoxMessage);
        Assert.assertEquals(textBoxMessage, key ,"The message is not the same");

        //Validate that send button exists
        WebElement sentButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        Assert.assertTrue(sentButton.isDisplayed(), "The sent button is not displayed.");

        //Click send button
        sentButton.click();

        WebElement sentMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[2]/div/div/div/div[1]/div/div/div/div/div/div")));
        String message = sentMessage.getText();
        System.out.println("The sent message is: " + message);
        Assert.assertEquals(key, message, "The message was not send correctly!");
    }

    @Test
    public void testAutoResponse(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().frame(iframe);

        WebElement chat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div")));

        //Click on the Chat button
        chat.click();

        WebElement textBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/textarea")));
        Assert.assertTrue(textBox.isDisplayed(), "Chat text box is not displayed.The chat does not opened!");

        // Type message into the text field

        String key = "Test Message";
        textBox.sendKeys(key);

        String textBoxMessage = textBox.getAttribute("value");
        System.out.println("The message in the text box is: " + textBoxMessage);
        Assert.assertEquals(textBoxMessage, key ,"The message is not the same");

        WebElement sentButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        //Assert.assertTrue(sentButton.isDisplayed(), "The sent button is not displayed.");

        //Click send button
        sentButton.click();

        WebElement sentMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[2]/div/div/div/div[1]/div/div/div/div/div/div")));
        String message = sentMessage.getText();
        System.out.println("The sent message is: " + message);
        Assert.assertEquals(key, message, "The message was not send correctly!");

//        Thread thread = null;
//        try {
//            Thread.sleep(105000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        //Validate that you receive an auto-response
        WebElement chatForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[3]/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/form")));
        Assert.assertTrue(chatForm.isDisplayed(), "Chat form is not sent back!");
    }

    @Test
    public void testForm(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().frame(iframe);

        WebElement chat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div")));

        //Click on the Chat button
        chat.click();

        WebElement textBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/textarea")));
        Assert.assertTrue(textBox.isDisplayed(), "Chat text box is not displayed.The chat does not opened!");

        // Type message into the text field

        String key = "Test Message";
        textBox.sendKeys(key);

        String textBoxMessage = textBox.getAttribute("value");
        System.out.println("The message in the text box is: " + textBoxMessage);
        Assert.assertEquals(textBoxMessage, key ,"The message is not the same");

        WebElement sentButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));

        //Click send button
        sentButton.click();

        WebElement sentMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[2]/div/div/div/div[1]/div/div/div/div/div/div")));
        String message = sentMessage.getText();
        System.out.println("The sent message is: " + message);
        Assert.assertEquals(key, message, "The message was not send correctly!");

//        Thread thread = null;
//        try {
//            Thread.sleep(1005000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        //Validate that you receive an auto-response
        WebElement chatForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[3]/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/form")));
        Assert.assertTrue(chatForm.isDisplayed(), "Chat form is not sent back!");

        //Complete the form with valid data
        //Name
        WebElement formNameTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"name\"]")));
        Assert.assertTrue(formNameTextBox.isDisplayed(), "Chat form Name text box  is not displayed!");

        // Type a name into the text field

        String formNameKey = "John McGinn";
        formNameTextBox.sendKeys(formNameKey);

        String textBoxName = formNameTextBox.getAttribute("value");
        System.out.println("The message in the Name text box of the form is: " + textBoxName);
        Assert.assertEquals(textBoxName, formNameKey ,"The name is not the same");

        //Email
        WebElement formEmailTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"email\"]")));
        Assert.assertTrue(formEmailTextBox.isDisplayed(), "Chat form Email text box  is not displayed!");

        // Type an email into the text field

        String formEmailKey = "johnmcginn@gmail.com";
        formEmailTextBox.sendKeys(formEmailKey);

        String textBoxEmail = formEmailTextBox.getAttribute("value");
        System.out.println("The message in the Email text box of the form is: " + textBoxEmail);
        Assert.assertEquals(textBoxEmail, formEmailKey ,"The email is not the same");

        //Message
        WebElement formMessageTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"message\"]")));
        Assert.assertTrue(formMessageTextBox.isDisplayed(), "Chat form Message text box  is not displayed!");

        // Type a message into the text field

        String formMessageKey = "Lorem ipsum dolor sit amet";
        formMessageTextBox.sendKeys(formMessageKey);

        String textBoxFormMessage = formMessageTextBox.getAttribute("value");
        System.out.println("The message in the Message text box of the form is: " + textBoxFormMessage);
        Assert.assertEquals(textBoxFormMessage, formMessageKey ,"The message is not the same");

        //Click on Submit button
        WebElement formSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("dUbg3")));
        Assert.assertTrue(formSubmitButton.isDisplayed(), "Chat form Submit button is not displayed!");
        formSubmitButton.click();

        //Validate that you receive a confirmation message

        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[3]/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div[2]")));
        Assert.assertTrue(confirmationMessage.isDisplayed(), "A confirmation message is not sent back!");

    }

    @Test
    public void testAfterSubmission(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().frame(iframe);

        WebElement chat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div")));

        //Click on the Chat button
        chat.click();

        WebElement textBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/textarea")));
        Assert.assertTrue(textBox.isDisplayed(), "Chat text box is not displayed.The chat does not opened!");

        // Type message into the text field

        String key = "Test Message";
        textBox.sendKeys(key);

        String textBoxMessage = textBox.getAttribute("value");
        System.out.println("The message in the text box is: " + textBoxMessage);
        Assert.assertEquals(textBoxMessage, key ,"The message is not the same");

        WebElement sentButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));

        //Click send button
        sentButton.click();

        WebElement sentMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[2]/div/div/div/div[1]/div/div/div/div/div/div")));
        String message = sentMessage.getText();
        System.out.println("The sent message is: " + message);
        Assert.assertEquals(key, message, "The message was not send correctly!");

//        Thread thread = null;
//        try {
//            Thread.sleep(1005000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        //Validate that you receive an auto-response
        WebElement chatForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[3]/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div/form")));
        Assert.assertTrue(chatForm.isDisplayed(), "Chat form is not sent back!");

        //Complete the form with valid data
        //Name
        WebElement formNameTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"name\"]")));
        Assert.assertTrue(formNameTextBox.isDisplayed(), "Chat form Name text box  is not displayed!");

        // Type a name into the text field

        String formNameKey = "John McGinn";
        formNameTextBox.sendKeys(formNameKey);

        String textBoxName = formNameTextBox.getAttribute("value");
        System.out.println("The message in the Name text box of the form is: " + textBoxName);
        Assert.assertEquals(textBoxName, formNameKey ,"The name is not the same");

        //Email
        WebElement formEmailTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"email\"]")));
        Assert.assertTrue(formEmailTextBox.isDisplayed(), "Chat form Email text box  is not displayed!");

        // Type an email into the text field

        String formEmailKey = "johnmcginn@gmail.com";
        formEmailTextBox.sendKeys(formEmailKey);

        String textBoxEmail = formEmailTextBox.getAttribute("value");
        System.out.println("The message in the Email text box of the form is: " + textBoxEmail);
        Assert.assertEquals(textBoxEmail, formEmailKey ,"The email is not the same");

        //Message
        WebElement formMessageTextBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"message\"]")));
        Assert.assertTrue(formMessageTextBox.isDisplayed(), "Chat form Message text box  is not displayed!");

        // Type a message into the text field

        String formMessageKey = "Lorem ipsum dolor sit amet";
        formMessageTextBox.sendKeys(formMessageKey);

        String textBoxFormMessage = formMessageTextBox.getAttribute("value");
        System.out.println("The message in the Message text box of the form is: " + textBoxFormMessage);
        Assert.assertEquals(textBoxFormMessage, formMessageKey ,"The message is not the same");

        //Click on Submit button
        WebElement formSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("dUbg3")));
        Assert.assertTrue(formSubmitButton.isDisplayed(), "Chat form Submit button is not displayed!");
        formSubmitButton.click();

        //Validate that you receive a confirmation message

        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[3]/div/div/div/div/div/div/div/div/div[2]/div/div/div/div/div/div/div/div[2]")));
        Assert.assertTrue(confirmationMessage.isDisplayed(), "A confirmation message is not sent back!");

        //Type message into the text field again

        String keyNewMessage = "New Test Message";
        textBox.sendKeys(keyNewMessage);

        String textBoxNewMessage = textBox.getAttribute("value");
        System.out.println("The new message in the text box is: " + textBoxNewMessage);
        Assert.assertEquals(textBoxNewMessage, keyNewMessage ,"The new message is not the same");

        //Click send button
        WebElement sentNewButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/textarea")));
        sentNewButton.click();

        WebElement sentNewMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[2]/div/div/div/div[1]/div/div/div/div/div/div")));
        String newMessage = sentNewMessage.getText();
        System.out.println("The new sent message is: " + newMessage);
        Assert.assertEquals(keyNewMessage, newMessage, "The new message was not send correctly!");

        //???????

        //Validate that you receive an auto-response
    }

    @Test
    public void testAttachFile(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().frame(iframe);

        WebElement chat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div")));

        //Click on the Chat button
        chat.click();

        WebElement textBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/textarea")));
        Assert.assertTrue(textBox.isDisplayed(), "Chat text box is not displayed.The chat does not opened!");

        //Validate that Attach button exists
        WebElement attachButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        Assert.assertTrue(attachButton.isDisplayed(), "Attach file button is not displayed.");

        //Click on the Attach button
        attachButton.click();

       // Attach a file and send it

        WebElement fileInput = driver.findElement(By.cssSelector("input[data-hook='file-upload-input']"));

        String filePath = "D:\\testcase\\ss\\chat.png";
        String fileName = "chat.png";
        fileInput.sendKeys(filePath);

        //Click on Submit button
        WebElement formSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("dUbg3")));
        Assert.assertTrue(formSubmitButton.isDisplayed(), "Chat form Submit button is not displayed!");
        formSubmitButton.click();


        // Așteaptă câteva secunde să se proceseze încărcarea
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Găsește elementul imagine după încărcare și verifică dacă src-ul este corect
        WebElement uploadedImage = driver.findElement(By.xpath("//*[@id=\"chat-messages-list\"]/div[2]/div/div/div/div/div/div/div/div/div/div/img"));

        // Verifică atributul alt
        String altUploadImage = uploadedImage.getAttribute("alt");
        System.out.println("Alt of the image is: " + altUploadImage);

        // Verifică dacă alt-ul conține numele imaginii sau un text specific
        Assert.assertTrue(altUploadImage.contains("chat.png"), "The alt attribute of the uploaded image does not contain the image name!");

//        String acceptedTypes = fileInput.getAttribute("accept");
//        String expectedTypes = "image/*,.doc,.docx,.ppt,.pptx,.xlsx,.xls,.pdf";
//        Assert.assertEquals(acceptedTypes, expectedTypes, "Accepted file types do not match the expected types.");


    }

    @Test
    public void testDownloadAttachment(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().frame(iframe);

        WebElement chat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div")));

        //Click on the Chat button
        chat.click();

        WebElement textBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/textarea")));

        // Type message into the text field

        String key = "Test Message";
        textBox.sendKeys(key);

        WebElement sentButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        //Click send button
        sentButton.click();

        WebElement formSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("dUbg3")));
        Assert.assertTrue(formSubmitButton.isDisplayed(), "Chat form Submit button is not displayed!");

        //Validate that Attach button exists
        WebElement attachButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        Assert.assertTrue(attachButton.isDisplayed(), "Attach file button is not displayed.");

        //Click on the Attach button
        attachButton.click();

        // Attach a file and send it

        WebElement fileInput = driver.findElement(By.cssSelector("input[data-hook='file-upload-input']"));

        String filePath = "D:\\testcase\\ss\\chat.png";
        String fileName = "chat.png";
        fileInput.sendKeys(filePath);

        // Așteaptă câteva secunde să se proceseze încărcarea
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Validate that the download  button exists

        // Wait for the download button to be visible
        WebElement hoverButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[4]/div/div/div/div[1]/div/div/div/div/div/div/img")));
        Assert.assertTrue(hoverButton.isDisplayed(), "Hover Download file button is not displayed!");

        // Perform hover action
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverButton).perform();

        // Wait for the download button to be visible
        WebElement downloadButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.wds_1_96_1_ButtonCore__root.wds_1_96_1_IconButton__root")));
        Assert.assertTrue(downloadButton.isDisplayed(), "Download file button is not displayed!");

        //Click on the download button
        downloadButton.click();

        // Wait for the file download to complete
        File file = new File(filePath);
        int attempts = 0;
        while (!file.exists() && attempts < 30) {
            try {
                Thread.sleep(1000); // Wait for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            attempts++;
        }

        // Verify file existence
        File downloadedFile = new File("C:\\Users\\z004zy5f\\Downloads\\chat.png");
        Assert.assertTrue(downloadedFile.exists(), "Downloaded file does not exist!");

    }

    @Test
    public void testAttachmentOpen(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().frame(iframe);

        WebElement chat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div")));

        //Click on the Chat button
        chat.click();

        WebElement textBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/textarea")));

        // Type message into the text field

        String key = "Test Message";
        textBox.sendKeys(key);

        WebElement sentButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        //Click send button
        sentButton.click();

        WebElement formSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("dUbg3")));
        Assert.assertTrue(formSubmitButton.isDisplayed(), "Chat form Submit button is not displayed!");

        //Validate that Attach button exists
        WebElement attachButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        Assert.assertTrue(attachButton.isDisplayed(), "Attach file button is not displayed.");

        //Click on the Attach button
        attachButton.click();

        // Attach a file and send it

        WebElement fileInput = driver.findElement(By.cssSelector("input[data-hook='file-upload-input']"));

        String filePath = "D:\\testcase\\ss\\chat.png";
        String fileName = "chat.png";
        fileInput.sendKeys(filePath);

        // Wait for the sent image to be visible
        WebElement sentAttachment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"chat-messages-list\"]/div[4]/div/div/div/div[1]/div/div/div/div/div/div/img")));
        Assert.assertTrue(sentAttachment.isDisplayed(), "The sent image is not displayed!");

        //Click on the attachment
        sentAttachment.click();

        // Validate that the attachment is displayed in full screen with details
        WebElement clickedAttachment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Kf3mQ")));
        Assert.assertTrue(clickedAttachment.isDisplayed(), "The attachment that was clicked is not displayed!");

    }

    @Test
    public void testDownloadButtonFullScreen(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().frame(iframe);

        WebElement chat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div")));

        //Click on the Chat button
        chat.click();

        WebElement textBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/textarea")));

        // Type message into the text field

        String key = "Test Message";
        textBox.sendKeys(key);

        WebElement sentButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        //Click send button
        sentButton.click();

        WebElement formSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("dUbg3")));
        Assert.assertTrue(formSubmitButton.isDisplayed(), "Chat form Submit button is not displayed!");

        //Validate that Attach button exists
        WebElement attachButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        Assert.assertTrue(attachButton.isDisplayed(), "Attach file button is not displayed.");

        //Click on the Attach button
        attachButton.click();

        // Attach a file and send it

        WebElement fileInput = driver.findElement(By.cssSelector("input[data-hook='file-upload-input']"));

        String filePath = "D:\\testcase\\ss\\chat.png";
        String fileName = "chat.png";
        fileInput.sendKeys(filePath);

        // Wait for the sent image to be visible
        WebElement sentAttachment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("_7KPCE")));
        Assert.assertTrue(sentAttachment.isDisplayed(), "The sent image is not displayed!");

        //Click on the attachment
        sentAttachment.click();

        //WebElement clickedAttachment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Kf3mQ")));

        //Validate that the download button exists
        WebElement downloadButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("HATjR")));
        Assert.assertTrue(downloadButton.isDisplayed(), "Download button is not displayed!");

        //Click on download button
        downloadButton.click();

        // Wait for the file download to complete
        File file = new File(filePath);
        int attempts = 0;
        while (!file.exists() && attempts < 30) {
            try {
                Thread.sleep(1000); // Wait for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            attempts++;
        }

        // Verify file existence
        File downloadedFile = new File("C:\\Users\\z004zy5f\\Downloads\\chat.png");
        Assert.assertTrue(downloadedFile.exists(), "Downloaded file does not exist!");
    }

    @Test
    public void testCloseButtonFullScreen(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"comp-jr4sqg2g\"]/iframe")));
        driver.switchTo().frame(iframe);

        WebElement chat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div")));

        //Click on the Chat button
        chat.click();

        WebElement textBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/textarea")));

        // Type message into the text field

        String key = "Test Message";
        textBox.sendKeys(key);

        WebElement sentButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        //Click send button
        sentButton.click();

        WebElement formSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("dUbg3")));
        Assert.assertTrue(formSubmitButton.isDisplayed(), "Chat form Submit button is not displayed!");

        //Validate that Attach button exists
        WebElement attachButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div[2]/div/div[2]/div[2]/div/button[2]")));
        Assert.assertTrue(attachButton.isDisplayed(), "Attach file button is not displayed.");

        //Click on the Attach button
        attachButton.click();

        // Attach a file and send it

        WebElement fileInput = driver.findElement(By.cssSelector("input[data-hook='file-upload-input']"));

        String filePath = "D:\\testcase\\ss\\chat.png";
        String fileName = "chat.png";
        fileInput.sendKeys(filePath);

        // Wait for the sent image to be visible
        WebElement sentAttachment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("_7KPCE")));
        Assert.assertTrue(sentAttachment.isDisplayed(), "The sent image is not displayed!");

        //Click on the attachment
        sentAttachment.click();

        WebElement clickedAttachment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Kf3mQ")));
        Assert.assertTrue(clickedAttachment.isDisplayed(), "The attachment that was clicked is not displayed!");

        //Validate that the close button exists
        WebElement closeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("R6Msn")));
        Assert.assertTrue(closeButton.isDisplayed(), "Close the full screen button is not displayed!");

        //Click on close button
        closeButton.click();
        Assert.assertFalse(clickedAttachment.isDisplayed(), "The attachment that was clicked is not displayed!");
    }

}
