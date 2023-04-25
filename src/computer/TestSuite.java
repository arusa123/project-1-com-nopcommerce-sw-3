package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utility.Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends Utilities {
    @Before
    public void browserOpening() {
        openBrowser("Chrome", "https://demo.nopcommerce.com/");
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() throws InterruptedException {
        mouseHoverOnElement(By.linkText("Computers"));
        mouseClickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Desktops']"));
        List<WebElement> listBeforeSorting = driver.findElements(By.xpath("//div[@class='item-grid']/div/div/div/h2"));
        ArrayList<String> originalList = new ArrayList<>();
        for (WebElement e : listBeforeSorting) {
            originalList.add(e.getText());
        }
        System.out.println(originalList);
        Collections.reverse(originalList);
        System.out.println(originalList);
        selectByValueFromDropDown(By.xpath("//select[@id='products-orderby']"), "6");
        Thread.sleep(1000);
        List<WebElement> listAfterSorting = driver.findElements(By.xpath("//div[@class='item-grid']/div/div/div/h2"));
        ArrayList<String> sortedList = new ArrayList<>();
        for (WebElement e : listAfterSorting) {
            sortedList.add(e.getText());
        }
        System.out.println(sortedList);

        Assert.assertEquals("The products are not sorted in Z-A", originalList, sortedList);
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        // 2.1 Click on Computer Menu.
        mouseHoverOnElement(By.linkText("Computers"));

//            2.2 Click on Desktop
        mouseClickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Desktops']"));

//            2.3 Select Sort By position "Name: A to Z"
        selectByValueFromDropDown(By.xpath("//select[@id='products-orderby']"), "5");

//            2.4 Click on "Add To Cart"
        Thread.sleep(2000);
        clickOnElement(By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[2]//div[3]//div[2]//button[1]"));
//          2.5 Verify the Text "Build your own computer"
        String expectedText = "Build your own computer";
        String actualText = getTextFromElement(By.xpath("//h1[normalize-space()='Build your own computer']"));
        Assert.assertEquals("Build your computer", expectedText, actualText);
//        2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        selectByIndexFromDropDown(By.xpath("//select[@id='product_attribute_1']"), 1);
//        2.7.Select "8GB [+$60.00]" using Select class.
        selectByIndexFromDropDown(By.xpath("//select[@id='product_attribute_2']"), 3);
//        2.8 Select HDD radio "400 GB [+$100.00]"
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));


//        2.9 Select OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.xpath("//input[@id='product_attribute_4_9']"));
//        2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander
//                [+$5.00]"
        Thread.sleep(1000);
        // clickOnElement((By) By.id("product_attribute_5").findElement(By.xpath("//input[@id='product_attribute_5_12']")));
        clickOnElement(By.id("product_attribute_3_7"));

        clickOnElement(By.id("product_attribute_5_12"));
        Thread.sleep(1000);

//        2.11 Verify the price "$1,475.00"
        String expectedElement = "$1,475.00";
        String actualtElemenet = getTextFromElement(By.id("price-value-1"));
        Assert.assertEquals("price of the computer", expectedElement, actualtElemenet);


//        2.12 Click on "ADD TO CARD" Button.
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));

//        2.13 Verify the Message "The product has been added to your shopping cart" on Top
//        green Bar
        String expectedTextElement = "The product has been added to your shopping cart";
        String actualTextElement = getTextFromElement(By.xpath("//div[@class='bar-notification success']/p"));
        Assert.assertEquals("Product Added Successful Message", expectedTextElement, actualTextElement);

//        After that close the bar clicking on the cross button.
        clickOnElement(By.xpath("//span[@title='Close']"));

//        2.14 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverOnElement(By.xpath("//span[@class='cart-label']"));
        clickOnElement(By.xpath("//button[normalize-space()='Go to cart']"));
//        2.15 Verify the message "Shopping cart"
        Thread.sleep(1000);
        String expected = "Shopping cart";
        String actualtText = getTextFromElement(By.linkText("Shopping cart"));
        Assert.assertEquals("Shopping Cart", expected, actualtText);

//        2.16 Change the Qty to "2" and Click on "Update shopping cart"
        driver.findElement(By.xpath("//input[@class='qty-input']")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.xpath("//input[@class='qty-input']")).sendKeys("2");
        clickOnElement(By.id("updatecart"));

//        2.17 Verify the Total"$2,950.00"
        String excptedText = "$2,950.00";
        String actulTxt = getTextFromElement(By.xpath("//span[@class='product-subtotal']"));
        Assert.assertEquals("Total", excptedText, actulTxt);

//        2.18 click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));
//        2.19 Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));

//        2.20 Verify the Text “Welcome, Please Sign In!”
        String expectedTexxt = "Welcome, Please Sign In!";
        String acttultxt = getTextFromElement(By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"));
        Assert.assertEquals("Welcome", expectedTexxt, acttultxt);
//        2.21Click on “CHECKOUT AS GUEST”Tab
        clickOnElement(By.xpath("//button[normalize-space()='Checkout as Guest']"));


//        2.22 Fill the all mandatory field
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "Prime");
        sendTextToElement(By.id("BillingNewAddress_LastName"), "Testing");
        sendTextToElement(By.id("BillingNewAddress_Email"), "prime123@gmail.com");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
        sendTextToElement(By.id("BillingNewAddress_City"), "London");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "26 London Road ");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "UB7 7JP");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "07412576899");

//        2.23 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

//        2.24 Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.id("shippingoption_1"));

//        2.25 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));


//        2.26 Select Radio Button “Credit Card”

        clickOnElement(By.xpath("(//input[@name='paymentmethod'])[2]"));
        Thread.sleep(1000);
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));

//        2.27 Select “Master card” From Select credit card dropdown
        selectByIndexFromDropDown(By.xpath("//select[@id='CreditCardType']"), 1);

//        2.28 Fill all the details
        sendTextToElement(By.id("CardholderName"), "Prime Testing");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "5105105105105100");
        selectByIndexFromDropDown(By.xpath("//select[@id='ExpireMonth']"), 2);
        selectByIndexFromDropDown(By.xpath("//select[@id='ExpireYear']"), 2);
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "768");

//        2.29 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

//        2.30 Verify “Payment Method” is “Credit Card”
        String exptText = "Payment Method: Credit Card";
        String actultxt = getTextFromElement(By.xpath("//li[@class='payment-method']"));
        Assert.assertEquals("Payment method", exptText, actultxt);

//        2.32 Verify “Shipping Method” is “Next Day Air”
        String element = "Shipping Method: Next Day Air";
        String actelement = getTextFromElement(By.xpath("//li[@class='shipping-method']"));
        Assert.assertEquals("Shipping method", element, actelement);

//        2.33 Verify Total is “$2,950.00”
        String expectedTotal = "$2,950.00";
        String actualTotal = getTextFromElement(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$2,950.00')]"));
        Assert.assertEquals("Total", expectedTotal, actualTotal);
//        2.34 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[normalize-space()='Confirm']"));

//        2.35 Verify the Text “Thank You”
        String expectedThankYou = "Thank you";
        String actualThank = getTextFromElement(By.xpath("//h1[normalize-space()='Thank you']"));
        Assert.assertEquals("Thank you ", expectedThankYou, actualThank);

//        2.36 Verify the message “Your order has been successfully processed!”
        String expectedMessage = "Your order has been successfully processed!";
        String actualMessage = getTextFromElement(By.xpath("//strong[normalize-space()='Your order has been successfully processed!']"));
        Assert.assertEquals("Order is completed ", expectedMessage, actualMessage);
//        2.37 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[normalize-space()='Continue']"));

//        2.37 Verify the text “Welcome to our store”
        String verifyText = "Welcome to our store";
        String actualverifying = getTextFromElement(By.xpath("//h2[normalize-space()='Welcome to our store']"));
        Assert.assertEquals("Welcome to store", verifyText, actualverifying);

    }

    @After
    public void browserclosing() {
        closeBrowser();
    }
}
