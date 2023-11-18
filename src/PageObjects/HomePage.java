package PageObjects;


import org.openqa.selenium.*;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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






    public void BrowseTrhugevideos(){
        List<WebElement> videos = driver.findElements(By.cssSelector("[class=\"vidiq-c-lesPJm.vidiq-c-lesPJm-ikDVlAw-css\"]"));
        System.out.println("Videos: " + videos.size());
    for (int i = 0; i < videos.size(); i++) {
      videos = driver.findElements(By.cssSelector("[class=\"vidiq-c-lesPJm.vidiq-c-lesPJm-ikDVlAw-css\"]"));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {System.out.println("did not sleep");}
        WebElement temp = videos.get(i);

        temp.click();
        driver.navigate().back();

    }
    }
    public void GetAllTrendVideos(){

        click(vidiqExtensionButton);
        List<WebElement> dropdown = driver.findElements(By.cssSelector("div[class=\"css-4ebkc2 e1qzsphf0\"]"));
        dropdown.get(0).click();

        }
    public void loginToVidiq() {
    Set<String> allWindowHandles = driver.getWindowHandles();
    List<String> windowHandlesList = new ArrayList<>(allWindowHandles);
    driver.switchTo().window(windowHandlesList.get(1));
    preformLoginToVidiq();
    driver.switchTo().window(windowHandlesList.get(0));
}
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
    }
    private static String readFromFile(String keydata) throws Exception {
        String filePath = "C:\\Users\\liav\\IdeaProjects\\Videos\\src\\Data\\liavfile.txt";
        File file = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        return doc.getElementsByTagName(keydata).item(0).getTextContent();
    }






//    public void Post(String storeName, String storeService, String storeAddress, String storeNumber) throws Exception {
//        HttpClient httpClient = HttpClient.newHttpClient();
//        // Replace with the actual API endpoint
//        String apiUrl = "https://script.google.com/macros/s/AKfycbw_MLiirmtNkNWpeOI-zHqDfca5pCnhuNn2ll_qRfvCKbYavvCnOQ4oYS8CvUwcd3eZzw/exec";
//        // Replace with the desired JSON payload
//        String jsonPayload = "{\n" +
//                "  \"city\":\""+City+"\",\n" +
//                "  \"storeName\":\"" + storeName + "\",\n" +
//                "  \"storeService\":\"" + storeService + "\",\n" +
//                "  \"address\":\"" + storeAddress + "\",\n" +
//                "  \"phone\":\"" + storeNumber + "\"\n" +
//                "}";
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(new URI(apiUrl))
//                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
//                .header("Content-Type", "application/json") // Set the content type to JSON
//                .build();
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//        // Check the HTTP status code
//        int statusCode = response.statusCode();
//        if (statusCode == 302) { // 201 indicates a successful creation in many APIs
//            String responseBody = response.body();
//            System.out.println("status code: " + statusCode);
//            System.out.println("Response Body:\n" + responseBody);
//        } else {
//            System.out.println("POST request failed with status code: " + statusCode);
//            System.out.println(jsonPayload);
//        }
//    }
//
//    public void recaptchapass() throws InterruptedException {
//        boolean captchaPresent = isElementPresent(recaptcha);
//
//        while (captchaPresent) {
//            System.out.println("Captcha detected. Please manually pass the captcha.");
//            Thread.sleep(20000); // Sleep for 5 seconds before checking again
//
//            captchaPresent = isElementPresent(recaptcha);
//        }
//
//        System.out.println("Captcha passed or not detected.");
//        Thread.sleep(10000);
//    }
//    public boolean isElementPresent(By by) {
//        try {
//            driver.findElement(by);
//            return true;
//        } catch (NoSuchElementException e) {
//            return false;
//        }
//    }
//    public void clickOnMoreAds() throws InterruptedException {
//    Thread.sleep(1000);
//  try {
//      WebElement nextButtonElement = driver.findElement(nextButton);
//        waitForElement(nextButton);
//        if (nextButtonElement.isDisplayed()){
//            ScrollAndClick(nextButton);
//        }
//        clickOnMoreAds();}catch (NoSuchElementException e) {}  }
//    public void ScrollAndClick (By by) {
//        WebElement elementToClick = driver.findElement(by);
//        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", elementToClick);
//        // Click the element
//        elementToClick.click();
//
//
//}
//    public void clickOnPosts() throws Exception {
//        recaptchapass();
//        WebElement allPostsElement = driver.findElement(allPostsConteiner);
//        waitForElement(allPostsConteiner);
//        List<WebElement> posts;
//        clickOnMoreAds();
//        Thread.sleep(3000);
//        recaptchapass();
//       // posts = allPostsElement.findElements(By.cssSelector("[class^='list-results-scroll'] li"));
//        posts = allPostsElement.findElements(By.cssSelector(".list-results li"));
//
//        System.out.println("post size is" + posts.size());
//        for (int i = 0; i < posts.size(); i++) {
//            recaptchapass();
//            WebElement post = posts.get(i);
//            try {
//            //    Thread.sleep(1000);
//                scrollToElementAndClick(post);
//            } catch (StaleElementReferenceException e) {
//                System.out.println("Stale element reference, retrying click...");
//                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed
//                post = wait.until(ExpectedConditions.elementToBeClickable(posts.get(i)));
//                scrollToElementAndClick2(post);             //  post.click();
//            }
//
//
//            // Extract information
//            String storeName = getStoreInfo(ByStoreName, i);
//            String storeService = getStoreService(ByStoreService, i);
//            String storeAddress = getStoreInfo(ByStoreAddress, i);
//            String storeNumber = getStoreInfo(ByStoreNumber, i);
//            storeAddress = storeAddress.replace("\"", "'");
//System.out.println("storeName : "+storeName+"\n"+
//                   "storeService : "+storeService+"\n"+
//                   "storeAddress : "+storeAddress+"\n"+
//                   "storeNumber : "+storeNumber+"\n"+
//                   "post number: " + i+"\n"+"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//
//
//
//            // Call the Post method with the extracted values
//            Post(storeName, storeService, storeAddress, storeNumber);
//
//            waitForElement(BackButton);
//            Thread.sleep(2000);
//
////           String currentURL = driver.getCurrentUrl();
//           try{ click(BackButton);} catch (ElementClickInterceptedException e){driver.navigate().back();
//           }
//            //
////            Thread.sleep(1000);
////            String currentURL2 = driver.getCurrentUrl();
////if (currentURL.equals(currentURL2))
////{click(BackButton);}
//
//
//            clickOnMoreAds();
//            waitForElement(allPostsConteiner);
//            recaptchapass();
//        }
//    }
//    public String getStoreInfo(By by, int index)  {
//
//        String StoreName;
//       try{ waitForElement(by); // Make sure this method correctly waits for the element
//        WebElement element = driver.findElement(by); // Assuming 'driver' is your WebDriver instance
//        StoreName = element.getText();}
//        catch (TimeoutException e){
//            StoreName = "there is no phone number for the this buisnes";
//        }
//
//        // Remove the word "מנוהל" from StoreName
//        StoreName = StoreName.replace("מנוהל", "").trim();
//        StoreName = StoreName.replace("\"", "'");
//
//
//       // System.out.println("Post Number: " + index+" "+ StoreName);
//        return StoreName;
//    }
//
//    public String getStoreService(By by, int index)  {
//        WebElement element = driver.findElement(BYTotal); // Assuming 'driver' is your WebDriver instance
//        WebElement element1 = element.findElement(by); // Assuming 'driver' is your WebDriver instance
//
//        String StoreName;
//        waitForElement(by); // Make sure this method correctly waits for the element
//        StoreName = element1.getText();
//        // Remove the word "מנוהל" from StoreName
//
//
//        if (StoreName.contains("חלקי"))
//        {
//            StoreName = "חנות";
//        }
//else
//        {
//            StoreName = "נותן שירות";
//        }
//       // System.out.println("Post Number: " + index+" :"+ StoreName);
//        return StoreName;
//    }
}








