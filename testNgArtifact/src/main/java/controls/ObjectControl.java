package controls;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeoutException;

/**
 * Created by Chandandeep Singh on 27-02-2019.
 */
public class ObjectControl {

    public WebElement element;


    public ObjectControl(WebElement element){
        this.element = element;
    }


    public void click(WebElement element) {
        try {
            element.click();
        }
            catch (StaleElementReferenceException sere) {
                // simply retry finding the element in the refreshed DOM
                element.click();
            }
    }
}
