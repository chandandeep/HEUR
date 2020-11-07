package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static java.rmi.server.LogStream.log;


/**
 * Created by Chandandeep Singh on 29-09-2019.
 */

public class Utils {


    @Step("{0}")
    public static void logToAllure(String message) {
        java.util.logging.Logger.getLogger("Logged to allure: " + message);
    }


    public void logMessage(List<String> list){
        for(int i=0; i< list.size(); i++)

        {
            logToAllure(list.get(i));
        }

    }


    public static String readProp(String prop){
        FileReader reader= null;
        try {
            reader = new FileReader("src/test/resources/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Properties p=new Properties();
        try {
            p.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return   p.getProperty(prop);

    }

    public void getSauceMethodName(WebDriver driver, String methodName){
        if(readProp("isSauce").equals("true")){
            ((JavascriptExecutor) driver).executeScript("sauce:job-name=" + methodName);

        }
    }
}



