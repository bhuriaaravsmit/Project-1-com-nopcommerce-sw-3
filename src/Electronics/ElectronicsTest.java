package Electronics;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

public class ElectronicsTest extends Utility {


    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonePageSuccessFully() {
        WebElement electronics = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text() = 'Electronics ']"));
        WebElement cellphone = driver.findElement(By.xpath("(//ul/li/a[text()='Cell phones '])[1]"));


        Actions actions = new Actions(driver);


        actions.moveToElement(electronics).moveToElement(cellphone).click().build().perform();

        String expectedText = "Cell phones";
        String actualText = getTextFromElement(By.xpath("//div[@class='page-title']"));
        System.out.println(actualText);
        Assert.assertEquals("Verify the text", expectedText, actualText);
        sleep(100);
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() {

        WebElement electronics = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text() = 'Electronics ']"));
        WebElement cellphone = driver.findElement(By.xpath("(//ul/li/a[text()='Cell phones '])[1]"));

        Actions actions = new Actions(driver);
        actions.moveToElement(electronics).moveToElement(cellphone).click().build().perform();
        String expectedText = "Cell phones";
        String actualText = getTextFromElement(By.xpath("//div[@class='page-title']"));
        System.out.println(actualText);
        Assert.assertEquals("Verify the text", expectedText, actualText);
        sleep(100);
//click on list view tab
        clickOnElement(By.xpath("//a[@class='viewmode-icon list']"));
        sleep(500);
        //click on product name Nokia Lumia 1020
        clickOnElement(By.xpath("//h2[@class='product-title']/a[text()='Nokia Lumia 1020']"));
        sleep(400);
        //verify the text Nokia Lumia 1020
        String expectedPhone = "Nokia Lumia 1020";
        String actualPhone = getTextFromElement(By.xpath("//div[@class='product-name']/h1[text()='Nokia Lumia 1020']"));
        System.out.println(actualPhone);
        Assert.assertEquals("Verify the text", expectedPhone, actualPhone);
//verify the price
        String expectedPrice = "$349.00";
        String actualPrice = getTextFromElement(By.xpath("//span[@id='price-value-20']"));
        System.out.println(actualPrice);
        Assert.assertEquals("Verify the text", expectedPrice, actualPrice);
        sleep(400);
        //change the qty to 2
        sendTextToElement(By.id("product_enteredQuantity_20"), "2");
        clickOnElement(By.id("add-to-cart-button-20"));
        //Verify the Message "The product has been added to your shopping cart" on Top
        //green Bar
        String message = "The product has been added to your shopping cart";
        String actualMessage = getTextFromElement(By.xpath("//p[@class='content']"));
        System.out.println(actualMessage);
        Assert.assertEquals("Verify the message", message, actualMessage);
//close button
        clickOnElement(By.xpath("//span[@class='close']"));
        sleep(400);
        WebElement shoppingCart = driver.findElement(By.xpath("//span[@class='cart-label']"));
        actions.moveToElement(shoppingCart).click().build().perform();
        clickOnElement(By.xpath("//button[@class='button-1 cart-button']"));
        //Verify the message "Shopping cart"
        sleep(400);
        String messageCart = "Shopping cart";
        String actualCart = getTextFromElement(By.xpath("//h1[text()='Shopping cart']"));
        System.out.println(actualCart);
        Assert.assertEquals("Verify the message", messageCart, actualCart);
        //Verify the Total $698.00
        sleep(400);
        String messageTotal = "$698.00";
        String actualTotal = getTextFromElement(By.xpath("(//span[@class='value-summary'])[1]"));
        System.out.println(actualTotal);
        Assert.assertEquals("Verify the message",messageTotal, actualTotal);
//click on checkbox
        clickOnElement(By.id("termsofservice"));
        //Click on “CHECKOUT
        clickOnElement(By.id("checkout"));
//Verify the Text “Welcome, Please Sign In!"
        sleep(400);
        String messageWelcome = "Welcome, Please Sign In!";
        String actualWelcome = getTextFromElement(By.xpath("//h1[text()='Welcome, Please Sign In!']"));
        System.out.println(actualWelcome);
        Assert.assertEquals("Verify the message",messageWelcome, actualWelcome);

//Click on “REGISTER” tab
        clickOnElement(By.xpath("//button[@class='button-1 register-button']"));
//Verify the text “Register”
        sleep(400);
        String messageRegister = "Register";
        String actualRegister = getTextFromElement(By.xpath("//h1[text()='Register']"));
        System.out.println(actualRegister);
        Assert.assertEquals("Verify the message",messageRegister, actualRegister);
//Click on “REGISTER” Button

        clickOnElement(By.id("register-button"));

        //Verify the message “Your registration completed”
        sleep(400);
        String messageComplete = "Register";
        String actualComplete = getTextFromElement(By.xpath("//h1[text()='Register']"));
        System.out.println(actualComplete);
        Assert.assertEquals("Verify the message",messageComplete, actualComplete);

        sendTextToElement(By.id("FirstName"), "Shweta");
        sendTextToElement(By.id("LastName"), "Shah");
        sendTextToElement(By.id("Email"), "shweta1335@gmail.com");
        sendTextToElement(By.id("Company"), "Java");
        sendTextToElement(By.id("Password"), "Passw0rd");
        sendTextToElement(By.id("ConfirmPassword"), "Passw0rd");


        // registtation details
       clickOnElement(By.id("register-button"));
        sleep(400);

        String register = "Your registration completed";
        String successfultRegister = getTextFromElement(By.xpath("//div[@class='result']"));
        System.out.println(actualRegister);
        Assert.assertEquals("Verify the message",register, successfultRegister);
//Click on “REGISTER” Button

        clickOnElement(By.xpath("//a[text()='Continue']"));

    }

    @After
    public void tearDown() {
        //closeBrowser();
    }


}