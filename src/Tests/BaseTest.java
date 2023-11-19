package Tests;
//
import PageObjects.HomePage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.w3c.dom.Document;
;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class BaseTest {

    static HomePage homePage;
    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("load-extension=" + getExtensionPathVidiq());
    //    options.addArguments("load-extension=" + getExtensionPathHarpa());
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.addArguments("--disable-blink-features=AutomationControlled");

        driver = new ChromeDriver(options);
        resetPage();
        driver.manage().window().maximize();
    }


        private static String getExtensionPathVidiq() {
        // Generate the path to your extension directory
        return "C:\\Users\\liav\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Extensions\\pachckjkecffpdphbpmfolblodfkgbhl\\3.99.0_0";
    }
    public static String getExtensionPathHarpa() {
       // Generate the path to your extension directory
        return "C:\\Users\\liav\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Extensions\\eanggfilgoajaocelnaflolkadkeghjp\\7.0.2_0";
    }

    public static void resetPage() {
        homePage = new HomePage(driver);
    }

//    @AfterClass
//    public static void close() throws IOException {
//        if (driver != null) {
//            driver.quit();
//        }


    }

