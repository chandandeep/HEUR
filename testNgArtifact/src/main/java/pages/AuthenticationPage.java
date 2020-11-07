package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Chandandeep Singh on 03-03-2019.
 */
public class AuthenticationPage extends Page {

    @FindBy(id="email")
    public WebElement emailIdField;

    @FindBy(id="passwd")
    public WebElement passwordField;

    @FindBy(id="SubmitLogin")
    public WebElement submitButton;


    public AuthenticationPage(WebDriver driver) {
        super(driver);
    }

    public AuthenticationPage enterEmail(String emailId){
        emailIdField.sendKeys(emailId);
        return this;
    }

    public AuthenticationPage enterPassword(String password){
        passwordField.sendKeys(password);
        return this;
    }

    public HomePage clickSignInButton(){
        submitButton.click();
        return loadPage(HomePage.class);
    }

}
