package common;

import org.openqa.selenium.WebDriver;

/**
 * Created by Chandandeep Singh on 27-02-2019.
 */
public abstract class AbstractDriverManager {

    protected WebDriver driver;
    protected abstract void startService();
    protected abstract void stopService();
    protected abstract void createDriver();



    public   void closeDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }

    }

    public WebDriver getDriver() {
        if (null == driver) {
            startService();
            createDriver();
        }
        return driver;
    }
}
