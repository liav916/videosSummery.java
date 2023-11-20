package PageObjects;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    By switchToLoginButton = By.cssSelector("span[role=\"button\"]");
    By googleEmailField = By.cssSelector("input[type=\"email\"]");
    By googlePasswordField = By.cssSelector("input[type=\"password\"]");
    By googlesubmitbutton = By.cssSelector("button[type=\"submit\"]");
    By vidiqExtensionButton = By.cssSelector("button[class^=\"vidiq-dropdown-button\"]");

    By harpawait = By.cssSelector("row.DialogSuggestion");
    By harpawait2 = By.cssSelector("Button.InfoCircleContainer");



    public void getVideoSummeryWithHarpa() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.ALT).sendKeys("a").keyUp(Keys.ALT).build().perform();
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By iframeLocator = By.className("__hrp-frame__iframe");
        WebElement iframeElement = wait.until(ExpectedConditions.presenceOfElementLocated(iframeLocator));
        driver.switchTo().frame(iframeElement);
        Thread.sleep(5000);
        List<WebElement> HarpaButtons = driver.findElements(By.cssSelector("row.DialogSuggestion"));
        HarpaButtons.get(0).click();
       Thread.sleep(5000);
        List<WebElement> HarpaButtons2 = driver.findElements(By.cssSelector(".DialogAskChoice__optionLabel.Button__label"));
        HarpaButtons2.get(2).click();

    }
    public void BrowseTrhugevideos() throws InterruptedException {

        Thread.sleep(5000);
        List<WebElement> elements = driver.findElements(By.className("vidiq-c-hbWfAH"));
        System.out.println("Videos: " + elements);
        for (WebElement element : elements) {
           element.click();
            Set<String> allWindowHandles = driver.getWindowHandles();
            List<String> windowHandlesList = new ArrayList<>(allWindowHandles);
            driver.switchTo().window(windowHandlesList.get(1));
            getVideoSummeryWithHarpa();
            Thread.sleep(5000);
            driver.close();
            driver.switchTo().window(windowHandlesList.get(0));

        }}
    public void GetAllTrendVideos() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(15000);
        click(vidiqExtensionButton);
        List<WebElement> dropdown = driver.findElements(By.className("e1qzsphf0"));
        dropdown.get(0).click();
        } //done
    public void loginToVidiq() throws InterruptedException {
    Set<String> allWindowHandles = driver.getWindowHandles();
    List<String> windowHandlesList = new ArrayList<>(allWindowHandles);
    Thread.sleep(2000);
    driver.switchTo().window(windowHandlesList.get(1));
    driver.close();
    driver.switchTo().window(windowHandlesList.get(2));
    preformLoginToVidiq();
    driver.switchTo().window(windowHandlesList.get(0));

} //done
    public void preformLoginToVidiq()   {
        String expectedUrl = "https://app.vidiq.com/extension_login_success";
        waitForElement(switchToLoginButton);
        click(switchToLoginButton);
        waitForElement(googleEmailField);
        waitForElement(googlePasswordField);
        click(googleEmailField);
        try{
        String emailValue = readFromFile("value1");
        String PasswordValue = readFromFile("value2");
        sendKeys(googleEmailField, emailValue);
        click(googlePasswordField);
        sendKeys(googlePasswordField, PasswordValue);
        click(googlesubmitbutton);}catch(Exception e){}
        while (!driver.getCurrentUrl().equals(expectedUrl)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
        driver.close();
    }//done
    private  String readFromFile(String keydata) throws Exception {
        String filePath = "C:\\Users\\liav\\IdeaProjects\\Videos\\src\\Data\\liavfile.txt";
        File file = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        return doc.getElementsByTagName(keydata).item(0).getTextContent();
    } //done

}








