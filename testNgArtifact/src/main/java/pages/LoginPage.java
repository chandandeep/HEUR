package pages;

import controls.Controllers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Chandandeep Singh on 03-03-2019.
 */
public class LoginPage extends Page {



    @FindBy(css="a[class='login']")
    public WebElement signInButton;
    public Controllers controllers;


    public LoginPage(WebDriver driver) {

        super(driver);
        controllers = new Controllers();
    }


    public AuthenticationPage clickOnSignIn(){
        controllers.button.click(signInButton);
        return loadPage(AuthenticationPage.class);
    }

}
