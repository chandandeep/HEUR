package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.Utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Chandandeep Singh on 27-02-2019.
 */
public class ChromeDriverManager extends AbstractDriverManager {

    private ChromeDriverService chromeDriverService;

    @Override
    protected void startService() {
    if(null==chromeDriverService){
     try{
         chromeDriverService = new ChromeDriverService.Builder().usingDriverExecutable(new File("drivers/chromedriver86.exe"))
                 .usingAnyFreePort()
                 .build();
         chromeDriverService.start();
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
    }

    @Override
    protected void stopService() {
        if (null != chromeDriverService && chromeDriverService.isRunning())
            chromeDriverService.stop();
    }

    @Override
    protected void createDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(chromeDriverService, capabilities);
    }



     public WebDriver remoteChromedriver() {
         String sauceUserName = Utils.readProp("SAUCE_USERNAME");
         String sauceAccessKey = Utils.readProp("SAUCE_ACCESS_KEY");
         DesiredCapabilities capabilities = new DesiredCapabilities();
         capabilities.setCapability("username", sauceUserName);
         capabilities.setCapability("accessKey", sauceAccessKey);
         capabilities.setCapability("browserName", "Chrome");
         capabilities.setCapability("platform", "Windows 10");
         capabilities.setCapability("version", "85.0");
         ChromeOptions options = new ChromeOptions();
         options.addArguments("--start-maximized");
         options.addArguments("--disable-notifications");
         capabilities.setCapability(ChromeOptions.CAPABILITY, options);

         try {
             driver = new RemoteWebDriver(new URL("https://Malti007:e85c29a5-bf53-403d-8336-0a20418e4c54@ondemand.us-west-1.saucelabs.com:443/wd/hub"), capabilities);
         } catch (
                 MalformedURLException e) {
             e.printStackTrace();
         }
         return driver;
     }

}
