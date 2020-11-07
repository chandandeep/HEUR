import com.deque.axe.AXE;
import common.AbstractDriverManager;
import common.ChromeDriverManager;
import common.DriverManagerFactory;
import common.DriverType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.Utils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Chandandeep Singh on 03-03-2019.
 */
public class BaseTest {

    AbstractDriverManager driverManager;
    WebDriver driver;
    private static final URL scriptUrl = BaseTest.class.getResource("/axe.min.js");
    public static boolean isSauce = Boolean.parseBoolean(Utils.readProp("isSauce"));

    @BeforeTest
    public void beforeTest() {
        driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println(isSauce + " : is sauce val");
        System.out.println(System.getenv("SAUCE_ACCESS_KEY") + " env value");
        System.out.println(System.getenv("SAUCE_USERNAME") + " env value");

        if(isSauce){
            ChromeDriverManager cdm = new ChromeDriverManager();
            driver = cdm.remoteChromedriver();
        }
        else {
            driver = driverManager.getDriver();
        }
    }

   @AfterMethod
    public void afterMethod(ITestResult result) {
       if (isSauce) {
           ((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
           driver.close();
       } else {
           driverManager.closeDriver();
       }
   }

    @Test
    public void launchTestAutomationGuruTest() {
        Utils utils = new Utils();
        utils.getSauceMethodName(driver,  new Throwable()
                .getStackTrace()[0]
                .getMethodName());
        driver.get("http://automationpractice.com/index.php");
        LoginPage loginPage = new LoginPage(driver);
        Utils.logToAllure("Launched application");

        loginPage.loadPage(LoginPage.class).clickOnSignIn()
        .enterEmail("a@a.com")
        .enterPassword("12345")
        .clickSignInButton();
        Utils.logToAllure("Sign in button clicked");
    }

   // @Test
    public void launchTestAutomationGuruTest1() {
        driver.get("http://automationpractice.com/index.php");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loadPage(LoginPage.class).clickOnSignIn()
                .enterEmail("a@a.com")
                .enterPassword("12345")
                .clickSignInButton();
    }


    @Test(description = "Check Accessibility of page")
    public void testAccessibility() {
        Utils utils = new Utils();
        utils.getSauceMethodName(driver,  new Throwable()
                .getStackTrace()[0]
                .getMethodName());
        driver.get("https://www.w3.org/");
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();

        JSONArray violations = responseJSON.getJSONArray("violations");


        List<String> list = new ArrayList<String>();
        for(int i = 0; i < violations.length(); i++){
            list.add(violations.getJSONObject(i).getString("help"));
        }

        boolean noViolation = violations.length()==0;

        Utils util = new Utils();
        util.logMessage(list);
        Assert.assertTrue(noViolation);


    }

/*

    @Test
    public void testAccessibilityWithSkipFrames() {
        driver.get("http://localhost:5005");
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
                .skipFrames()
                .analyze();

        JSONArray violations = responseJSON.getJSONArray("violations");

        List<String> list = new ArrayList<String>();
        for(int i = 0; i < violations.length(); i++){
            list.add(violations.getJSONObject(i).getString("help"));
        }

        boolean noViolation = violations.length()==0;
        utils.Utils util = new utils.Utils();
        util.logMessage(list);
        Assert.assertTrue(noViolation);
    }



    @Test
    public void testAccessibilityWithOptions() {
        driver.get("http://localhost:5005");
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl)
                .options("{\"rules\": {\"landmark-one-main\": { enabled: false },\"meta-viewport\": { enabled: false } ,\"region\": { enabled: false } } }")
                .analyze();

        JSONArray violations = responseJSON.getJSONArray("violations");

        List<String> list = new ArrayList<String>();
        for(int i = 0; i < violations.length(); i++){
            list.add(violations.getJSONObject(i).getString("help"));
        }

        boolean noViolation = violations.length()==0;
        utils.Utils util = new utils.Utils();
        util.logMessage(list);
        Assert.assertTrue(noViolation);
    }
*/

}
