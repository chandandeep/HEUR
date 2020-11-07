package controls;

import org.openqa.selenium.WebElement;

/**
 * Created by Chandandeep Singh on 27-02-2019.
 */
public class Button extends ObjectControl {

    public Button(WebElement element) {
        super(element);
    }

   public void click(WebElement element){
       element.click();
   }

   public String getText(WebElement element){
       return element.getText();
   }

   public boolean isEnabled(WebElement element) {
       if(element.isEnabled()){
           return true;
       }
       else{
           return false;
       }
   }


}
