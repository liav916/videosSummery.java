package Tests;

import PageObjects.BasePage;
import PageObjects.HomePage;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.io.IOException;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrintAd extends BaseTest {

String channelName= "@KAN11";
    @Test
    public void test01getAdvertisement () throws Exception {
        driver.get("https://www.youtube.com/"+channelName);
        homePage.loginToVidiq();

        homePage.GetAllTrendVideos();
        homePage.BrowseTrhugevideos();


}


        }
