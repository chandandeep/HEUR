package common;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Chandandeep Singh on 27-02-2019.
 */
public class DriverManagerFactory {

    public static AbstractDriverManager getManager(DriverType type) {
        AbstractDriverManager driverManager = null;

            switch (type) {
                case CHROME:
                    driverManager = new ChromeDriverManager();
                    break;
                default:
                    driverManager = new FireFoxDriverManager();

                    break;
            }


        return driverManager;


    }

    }
