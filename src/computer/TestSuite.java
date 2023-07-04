package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);

    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {


        WebElement computer = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text() = 'Computers ']"));
        WebElement desktop = driver.findElement(By.xpath("(//ul/li/a[text()='Desktops '])[1]"));


        Actions actions = new Actions(driver);

        actions.moveToElement(computer).moveToElement(desktop).click().build().perform();

        WebElement dropDown = driver.findElement(By.id("products-orderby"));
        Select select = new Select(dropDown);
        select.selectByVisibleText("Name: Z to A");
        sleep(500);
        List<WebElement> elementList = driver.findElements(By.xpath("//h2[@class='product-title']/a"));
        List<String> list = new ArrayList();
        List<String> sortedlist = new ArrayList();

        System.out.println("Total Products are " + elementList.size());

        // need to check why order is not  correct
        for (WebElement web : elementList) {
            list.add(web.getText());
            sortedlist.add(web.getText());
            System.out.println(web.getText());
        }
        Collections.sort(sortedlist, Collections.reverseOrder());
        Assert.assertTrue(list.equals(sortedlist));


    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() {
//Click on Computer Menu
        WebElement computer = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[text() = 'Computers ']"));
        //Click on Desktop
        WebElement desktop = driver.findElement(By.xpath("(//ul/li/a[text()='Desktops '])[1]"));


        Actions actions = new Actions(driver);

        /* actions.moveToElement(computer).build().perform();
        actions.moveToElement(software).click().build().perform();*/

        actions.moveToElement(computer).moveToElement(desktop).click().build().perform();

//Select Sort By position "Name: Z to A"
        selectByVisibleTextFromDropDown(By.id("products-orderby"), "Name: A to Z");
        sleep(1000);
        clickOnElement(By.xpath("(//button[@class='button-2 product-box-add-to-cart-button'])[1]"));

        sleep(500);
        List<WebElement> elements = driver.findElements(By.xpath("//button[@class='button-2 product-box-add-to-cart-button']"));
// Verify the Product will arrange in Descending order
        String expectedText = "Build your own computer";
        String actualText = getTextFromElement(By.xpath("//div[@class='product-name']"));
        System.out.println(actualText);
        Assert.assertEquals("Verify the text", expectedText, actualText);
        sleep(100);
        selectByVisibleTextFromDropDown(By.id("product_attribute_1"), "2.2 GHz Intel Pentium Dual-Core E2200");
        sleep(100);
        selectByVisibleTextFromDropDown(By.id("product_attribute_2"), "8GB [+$60.00]");
        sleep(100);
        clickOnElement(By.id("product_attribute_3_7"));
        sleep(100);
        clickOnElement(By.id("product_attribute_4_9"));
        sleep(100);
        // clickOnElement(By.id("product_attribute_5_10"));

        clickOnElement(By.id("product_attribute_5_12"));

        String expectedPrice = "$1,475.00";
        sleep(200);
        String actualPrice = getTextFromElement(By.xpath("//span[@id='price-value-1']"));
        System.out.println(actualPrice);
        //Assert.assertEquals("Verify the Price", expectedPrice, actualPrice);

        clickOnElement(By.id("add-to-cart-button-1"));


        String expectedMessage = "The product has been added to your shopping cart";
        sleep(200);
        String actualMessage = getTextFromElement(By.xpath("//p[@class='content']"));
        System.out.println(actualMessage);
        Assert.assertEquals("Verify the Message", expectedMessage, actualMessage);

        sleep(200);
        clickOnElement(By.xpath("//span[@class='close']"));
        driver.findElement(By.xpath("//span[@class='cart-label']")).click();
        WebElement gotocart = driver.findElement(By.xpath("//button[@class='button-1 cart-button']"));
// Verify the message "Shopping cart"

        String expectedMsg = "Shopping cart";
        sleep(200);
        String actualMsg = getTextFromElement(By.xpath("//h1[text()='Shopping cart']"));
        System.out.println(actualMsg);
        Assert.assertEquals("Verify the Message", expectedMsg, actualMsg);

        sleep(200);
//Change the Qty to "2" and Click on "Update shopping cart"
        sendTextToElement(By.xpath("//input[@class='qty-input']"), "2");
        clickOnElement(By.id("updatecart"));
        // Verify the Total"$2,950.00
        sleep(300);
        String total = "$2,950.00";
        String actualTotal = getTextFromElement(By.xpath("(//span[@class='value-summary'])[1]"));
        System.out.println(actualTotal);
        Assert.assertEquals("Verify the message", total, actualTotal);
//click on checkbox “I agree with the terms of service
        clickOnElement(By.id("termsofservice"));

        // Click on “CHECKOUT”
        clickOnElement(By.id("checkout"));
//Verify the Text “Welcome, Please Sign In!

        sleep(300);
        String messageTxt = "Welcome, Please Sign In!";
        String actualTxt = getTextFromElement(By.xpath("//h1[text()='Welcome, Please Sign In!']"));
        System.out.println(actualTxt);
        Assert.assertEquals("Verify the message", messageTxt, actualTxt);

//Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//button[@class='button-1 checkout-as-guest-button']"));
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "Shweta");
        sendTextToElement(By.id("BillingNewAddress_LastName"), "Shah");
        sendTextToElement(By.id("BillingNewAddress_Email"), "shweta1335@gmail.com");
        selectByVisibleTextFromDropDown(By.id("BillingNewAddress_CountryId"), "India");
        sendTextToElement(By.id("BillingNewAddress_City"), "Baroda");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "37 ring road");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "380023");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "0265248968");

        clickOnElement(By.xpath("//button[@class='button-1 new-address-next-step-button']"));

        sleep(300);

        clickOnElement(By.id("shippingoption_1"));
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        sleep(300);
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));

        sleep(300);

        selectByVisibleTextFromDropDown(By.id("CreditCardType"), "Master card");
        sendTextToElement(By.id("CardholderName"), "Shweta shah");
        sendTextToElement(By.id("CardNumber"), "5425233430109903");
        selectByVisibleTextFromDropDown(By.id("ExpireMonth"), "08");
        selectByVisibleTextFromDropDown(By.id("ExpireYear"), "2026");
        sendTextToElement(By.id("CardCode"), "123");

        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        sleep(300);
        verifyText(By.xpath("//li[@class='payment-method']/span[@class='value']"), "Credit Card");
        verifyText(By.xpath("//li[@class='shipping-method']/span[@class='value']"), "Next Day Air");

        verifyText(By.xpath("//span[@class='product-subtotal']"), "$2,950.00");

        clickOnElement(By.xpath("//button[@class='button-1 confirm-order-next-step-button']"));
        sleep(500);

        verifyText(By.xpath("//div[@class='page-title']/h1"), "Thank you");
        verifyText(By.xpath("//div[@class='title']/strong"), "Your order has been successfully processed!");

        clickOnElement(By.xpath("//button[@class='button-1 order-completed-continue-button']"));
        sleep(300);

        verifyText(By.xpath("//div[@class='topic-block-title']/h2"), "Welcome to our store");

    }


    @After
    public void tearDown() {
        //  closeBrowser();
    }


}

