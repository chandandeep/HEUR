package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Chandandeep Singh on 03-03-2019.
 */
public class Page {

    public WebDriver driver;

    public Page(WebDriver driver){
       this.driver = driver;
    }

    public<T extends Page> T loadPage(Class<T> page) {
       return PageFactory.initElements(driver, page);
    }
}
