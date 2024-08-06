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

public class TestExploreHotelPage {
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
    public void testExploreHotelTextSection() {
        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement exploreButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("i6kl732v1label")));

        // Verifică dacă butonul este vizibil
        Assert.assertTrue(exploreButton.isDisplayed(), "Explore button is not displayed");
        System.out.println("EXPLORE button is displayed");

        exploreButton.click();

        // Verifică dacă URL-ul curent este cel așteptat
        String expectedURL = "https://ancabota09.wixsite.com/intern/explore";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "The wrong page is loaded!");
        System.out.println("URL of the Explore page is: " + actualURL);

        WebElement exploreText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6kvh3dl\"]/p/span")));
        String expectedText = "Discover the elegance and comfort of our hotel. Nestled in the heart of the city, our establishment offers a unique blend of contemporary design and classic luxury. Whether you're here for business or leisure, our hotel provides an exceptional experience with top-notch amenities, personalized services, and a warm, welcoming atmosphere. Take a tour through our beautifully designed rooms, enjoy exquisite dining at our on-site restaurant, and unwind at our state-of-the-art spa. Let us make your stay unforgettable, providing you with the perfect home away from home.";
        String actualText = exploreText.getText();
        Assert.assertEquals(actualText, expectedText, "The wrong text is loaded!");
    }

    @Test
    public void testAmenities() {
        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern/explore");
        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Cleaning amenity
        //image

        WebElement cleaningServicesImage = driver.findElement(By.xpath("//*[@id=\"img_i6lvcm6t\"]/img"));
        String cleaningImageSrc = cleaningServicesImage.getAttribute("src");
        System.out.println("The cleaning amenities image src is: " + cleaningImageSrc);

        String expectedCleaningSubstring = "9c608a_40c6a63735ab47b096691cfd25e22168.png";
        Assert.assertTrue(cleaningImageSrc.contains(expectedCleaningSubstring), "Cleaning Services image src does not contain the expected string.");

        //title

        WebElement cleaningServicesTitle = driver.findElement(By.xpath("//*[@id=\"i6lvcm6t_1\"]/p/span"));
        String cleaningActualTitle = cleaningServicesTitle.getText();
        System.out.println("The actual title of cleaning services is: " + cleaningActualTitle);
        String cleaningExpectedTitle = "Cleaning Services";
        Assert.assertEquals(cleaningActualTitle, cleaningExpectedTitle, "Cleaning amenities title does not corespond");

        //Parking amenity
        //image

        WebElement parkingImage = driver.findElement(By.xpath("//*[@id=\"img_i6lvcgoi\"]/img"));
        String parkingImageSrc = parkingImage.getAttribute("src");
        System.out.println("The parking amenities image src is: " + parkingImageSrc);

        String expectedParkingSubstring = "9c608a_faef9c2646824b4cb7d694d28e246dae.png";
        Assert.assertTrue(parkingImageSrc.contains(expectedParkingSubstring), " Parking image src does not contain the expected string.");

        //title

        WebElement parkingTitle = driver.findElement(By.xpath("//*[@id=\"i6lvcgoj_0\"]/p/span"));
        String parkingActualTitle = parkingTitle.getText();
        System.out.println("The actual title of parking is: " + parkingActualTitle);
        String parkingExpectedTitle = "Free Parking";
        Assert.assertEquals(parkingActualTitle, parkingExpectedTitle, "Parking amenities title does not corespond");

        //Furnish amenity
        //image

        WebElement furnishImage = driver.findElement(By.xpath("//*[@id=\"img_i6lvciyu_0\"]/img"));
        String furnishImageSrc = furnishImage.getAttribute("src");
        System.out.println("The furnish amenities image src is: " + furnishImageSrc);

        String expectedFurnishSubstring = "9c608a_3c58fe1f4ad24cac8823dbfb3445a4e6.png";
        Assert.assertTrue(furnishImageSrc.contains(expectedFurnishSubstring), "Furnish image src does not contain the expected string.");

        //title

        WebElement furnishTitle = driver.findElement(By.xpath("//*[@id=\"i6lvciyu_1\"]/p/span"));
        String furnishActualTitle = furnishTitle.getText();
        System.out.println("The actual title of furnish is: " + furnishActualTitle);
        String furnishExpectedTitle = "Fully Furnished";
        Assert.assertEquals(furnishActualTitle, furnishExpectedTitle, "Furnish amenities title does not corespond");


        //Wifi amenity
        //image

        WebElement wifiImage = driver.findElement(By.xpath("//*[@id=\"img_i6lvacuh\"]/img"));
        String wifiImageSrc = wifiImage.getAttribute("src");
        System.out.println("The wifi amenities image src is: " + wifiImageSrc);

        String expectedWifiSubstring = "9c608a_b504533992514b198819a54f27520449.png";
        Assert.assertTrue(wifiImageSrc.contains(expectedWifiSubstring), "Wifi image src does not contain the expected string.");

        //title

        WebElement wifiTitle = driver.findElement(By.xpath("//*[@id=\"i6kugnss\"]/p/span"));
        String wifiActualTitle = wifiTitle.getText();
        System.out.println("The actual title of wifi is: " + wifiActualTitle);
        String wifiExpectedTitle = "Free WiFi";
        Assert.assertEquals(wifiActualTitle, wifiExpectedTitle, "Parking amenities title does not corespond");

        //Airport transfer amenity
        //image

        WebElement airportImage = driver.findElement(By.xpath("//*[@id=\"img_i6lvdfku\"]/img"));
        String airportImageSrc = airportImage.getAttribute("src");
        System.out.println("The airport transfer amenities image src is: " + airportImageSrc);

        String expectedAirportSubstring = "9c608a_b7451c0859164f889f85d82de735148e.png";
        Assert.assertTrue(airportImageSrc.contains(expectedAirportSubstring), "Airport transfer image src does not contain the expected string.");

        //title

        WebElement airportTitle = driver.findElement(By.xpath("//*[@id=\"i6lvdfkt\"]/p/span"));
        String airportActualTitle = airportTitle.getText();
        System.out.println("The actual title of airport transfer is: " + airportActualTitle);
        String airportExpectedTitle = "Airport Transfers";
        Assert.assertEquals(airportActualTitle, airportExpectedTitle, "Airport transfers amenity title does not corespond");

    }

    @Test
    public void testExploreCityBlock(){
        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern/explore");
        // Explicit wait

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement exploreCityBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bgMedia_mediajg145x9g1")));
        Assert.assertTrue(exploreCityBlock.isDisplayed(), "Explore the City block is not visible.");
    }

    @Test
    public void testExploreCityChinatown(){
        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern/explore");
        // Explicit wait

        //Validate that the Chinatown block exists
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement exploreCityCinatown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6kv2te2\"]/div[1]")));
        Assert.assertTrue(exploreCityCinatown.isDisplayed(), "Explore the City Chinatown block is not visible.");

        //Validate that the image exists
        WebElement chinatownImage = driver.findElement(By.xpath("//*[@id=\"img_i6kv4ak9\"]/img"));
        String chinatownImageSrc = chinatownImage.getAttribute("src");
        System.out.println("The Chinatown image src is: " + chinatownImageSrc);

        String expectedChinatownSubstring = "9c608a_14eb60e42d3a42f29fe67d9b579e26de.jpg";
        Assert.assertTrue(chinatownImageSrc.contains(expectedChinatownSubstring), "Chinatown image src does not contain the expected string.");

        //Validate the title
        WebElement chinatownTitle = driver.findElement(By.xpath("//*[@id=\"i6kv3ge8\"]/p[1]"));
        String chinatownActualTitle = chinatownTitle.getText();
        System.out.println("The actual title of Chinatown block is: " + chinatownActualTitle);
        String chinatownExpectedTitle = "Chinatown";
        Assert.assertEquals(chinatownActualTitle, chinatownExpectedTitle, "Chinatown block title does not corespond");

        //Validate that the text exists
        WebElement chinatownText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6kv3ge8\"]/p[4]")));
        Assert.assertTrue(chinatownText.isDisplayed(), "Text element is not displayed!");

        //Validate that the text is relevant
        String expectedText = "Discover the vibrant heart of Chinatown, where the rich blend of traditional culture and modern urban energy creates a unique and captivating atmosphere. Wander through its bustling streets, savoring authentic Asian cuisine and exploring colorful markets and historic landmarks.";
        String actualText = chinatownText.getText();
        Assert.assertEquals(actualText, expectedText, "The wrong text is loaded in the Chinatown block!");
    }


    @Test
    public void testExploreCityHaightAshbury(){
        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern/explore");
        // Explicit wait

        //Validate that the Haight&Ashbury block exists
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement exploreCityHaightAshbury = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6kvbhma\"]/div[1]")));
        Assert.assertTrue(exploreCityHaightAshbury.isDisplayed(), "Explore the City Haight&Ashbury block is not visible.");

        //Validate that the image exists
        WebElement haightAshburyImage = driver.findElement(By.xpath("//*[@id=\"img_i6kvbhmc\"]/img"));
        String haightAshburyImageSrc = haightAshburyImage.getAttribute("src");
        System.out.println("The Haight&Ashbury image src is: " + haightAshburyImageSrc);

        String expectedHaightAshburySubstring = "9c608a_569e962c58334d07a4048e125af8fb82.jpg";
        Assert.assertTrue(haightAshburyImageSrc.contains(expectedHaightAshburySubstring), "Haight&Ashbury image src does not contain the expected string.");

        //Validate the title
        WebElement haightAshburyTitle = driver.findElement(By.xpath("//*[@id=\"i6kvbhmb\"]/p[1]"));
        String haightAshburyActualTitle = haightAshburyTitle.getText();
        System.out.println("The actual title of Haight&Ashbury block is: " + haightAshburyActualTitle);
        String haightAshburyExpectedTitle = "Haight & Ashbury";
        Assert.assertEquals(haightAshburyActualTitle, haightAshburyExpectedTitle, "Haight&Ashbury block title does not corespond");

        //Validate that the text exists
        WebElement haightAshburyText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6kvbhmb\"]/p[4]")));
        Assert.assertTrue(haightAshburyText.isDisplayed(), "Text element of haight&Ashbury is not displayed!");

        //Validate that the text is relevant
        String expectedText = "Haight & Ashbury is a famous neighborhood in San Francisco, known for its pivotal role in the 1960s counterculture movement. It features colorful Victorian houses, eclectic shops, and vibrant street art, reflecting its rich history and bohemian spirit.";
        String actualText = haightAshburyText.getText();
        Assert.assertEquals(actualText, expectedText, "The wrong text is loaded in the Haight&Ashbury block!");
    }

    @Test
    public void testExploreCityGoldengateBridge(){
        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // URL launch
        driver.get("https://ancabota09.wixsite.com/intern/explore");
        // Explicit wait

        //Validate that the Golden Gate Bridge block exists
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement exploreCityGoldenGateBridge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6kvbkvz\"]/div[1]")));
        Assert.assertTrue(exploreCityGoldenGateBridge.isDisplayed(), "Explore the City Golden Gate Bridge block is not visible.");

        //Validate that the image exists
        WebElement goldenGateBridgeImage = driver.findElement(By.xpath("//*[@id=\"img_i6kvbkw0_0\"]/img"));
        String goldenGateBridgeImageSrc = goldenGateBridgeImage.getAttribute("src");
        System.out.println("The Golden Gate Bridge image src is: " + goldenGateBridgeImageSrc);

        String expectedGoldenGateBridgeSubstring = "9c608a_66f0495affeb412ba01b0d9f0bd3dd6b.jpg";
        Assert.assertTrue(goldenGateBridgeImageSrc.contains(expectedGoldenGateBridgeSubstring), "Golden Gate Bridge image src does not contain the expected string.");

        //Validate the title
        WebElement goldenGateBridgeTitle = driver.findElement(By.xpath("//*[@id=\"i6kvbkw0\"]/p[1]"));
        String goldenGateBridgeActualTitle = goldenGateBridgeTitle.getText();
        System.out.println("The actual title of Golden Gate Bridge block is: " + goldenGateBridgeActualTitle);
        String goldenGateBridgeExpectedTitle = "Golden Gate Bridge";
        Assert.assertEquals(goldenGateBridgeActualTitle, goldenGateBridgeExpectedTitle, "CGolden Gate Bridge block title does not corespond");

        //Validate that the text exists
        WebElement goldenGateBridgeText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i6kvbkw0\"]/p[4]")));
        Assert.assertTrue(goldenGateBridgeText.isDisplayed(), "Text element of Golden Gate Bridge is not displayed!");

        //Validate that the text is relevant
        String expectedText = "The Golden Gate Bridge is an iconic suspension bridge connecting San Francisco to Marin County, renowned for its striking International Orange color and Art Deco design. Spanning 1.7 miles across the Golden Gate Strait, it offers breathtaking views of the bay and is a symbol of American engineering excellence.";
        String actualText = goldenGateBridgeText.getText();
        Assert.assertEquals(actualText, expectedText, "The wrong text is loaded in the Golden Gate Bridge block!");
    }

}
