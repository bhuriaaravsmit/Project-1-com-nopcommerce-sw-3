package utilities;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Utility extends BaseTest {
    /**
     * This method will click on element
     */
    public void clickOnElement(By by){
        driver.findElement(by).click();
    }

    /**
     * This method will get text from element
     */
    public String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }

    /**
     * This method will get text from attribute
     */
    public String getAttributeValue(By by,String attribute){
        return driver.findElement(by).getAttribute(attribute);
    }


    /**
     * This method will send text to element
     */
    public void sendTextToElement(By by, String text) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);

    }
    /* This method will select the option by visible text
     */
    public void selectByVisibleTextFromDropDown (By by, String text){
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }
    /**
     * This method will select the option by visible value
     */
    public void selectByValueFromDropDown (By by, String value){
        new Select(driver.findElement(by)).selectByValue(value);
    }

    public void sleep(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        }catch(Exception ex){

        }
    }

    public void verifyText(By by,String expectedMessage){
        String actualTxt = getTextFromElement(by);
        System.out.println(actualTxt);
        Assert.assertEquals("Verify the message", expectedMessage, actualTxt);
    }
}




