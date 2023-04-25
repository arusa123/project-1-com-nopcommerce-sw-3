package Electronics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utility.Utilities;

import java.util.Random;

public class ElectronicsTest extends Utilities {
    @Before
    public void browserOpening() {
        openBrowser("Chrome", "https://demo.nopcommerce.com/");
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {

//        1.1 Mouse Hover on “Electronics”Tab
        mouseHoverOnElement(By.linkText("Electronics"));

//        1.2 Mouse Hover on “Cell phones” and click
        mouseClickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));
//        1.3 Verify the text “Cell phones”
        String expectedTextPhone = "Cell phones";
        String actualText = getTextFromElement(By.xpath("//h1[normalize-space()='Cell phones']"));
        Assert.assertEquals("Cell phone", expectedTextPhone, actualText);

    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
//        2.1 Mouse Hover on “Electronics”Tab
        mouseHoverOnElement(By.linkText("Electronics"));

//        2.2 Mouse Hover on “Cell phones” and click
        mouseClickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));

//        2.3 Verify the text “Cell phones”
        String expectedTextPhone = "Cell phones";
        String actualText = getTextFromElement(By.xpath("//h1[normalize-space()='Cell phones']"));
        Assert.assertEquals("Cell phone", expectedTextPhone, actualText);

//        2.4 Click on List View Tab
        clickOnElement(By.xpath("//a[normalize-space()='List']"));
        Thread.sleep(2000);

//        2.5 Click on product name “Nokia Lumia 1020” link
        clickOnElement(By.linkText("Nokia Lumia 1020"));

//        2.6 Verify the text “Nokia Lumia 1020”
        String expectedNokiaText = "Nokia Lumia 1020";
        String actualTextNokia = getTextFromElement(By.xpath("//h1[normalize-space()='Nokia Lumia 1020']"));
        Assert.assertEquals("Nokia Lumia", expectedNokiaText, actualTextNokia);

//        2.7 Verify the price “$349.00”
        String expectedPrice = "$349.00";
        String actualPrice = getTextFromElement(By.xpath("//span[@class='price-value-20']"));
        Assert.assertEquals("Price", expectedPrice, actualPrice);

//        2.8 Change quantity to 2
        driver.findElement(By.name("addtocart_20.EnteredQuantity")).sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.name("addtocart_20.EnteredQuantity")).sendKeys("2");

//        2.9 Click on “ADD TO CART” tab
        clickOnElement(By.xpath("//button[@class='button-1 add-to-cart-button']"));

//        2.10 Verify the Message "The product has been added to your shopping cart" on Top
//        green Bar
        String expectedMessage = "The product has been added to your shopping cart";
        String actualMessage = getTextFromElement(By.xpath("//div[@class='bar-notification success']/p"));
        Assert.assertEquals("Added to Cart", expectedMessage, actualMessage);

//        After that close the bar clicking on the cross button.
        clickOnElement(By.xpath("//span[@title='Close']"));

//        2.11 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverOnElement(By.xpath("//span[@class='cart-label']"));
        clickOnElement(By.xpath("//button[normalize-space()='Go to cart']"));
//
//        2.12 Verify the message "Shopping cart"
        Thread.sleep(1000);
        String expected = "Shopping cart";
        String actualtText = getTextFromElement(By.linkText("Shopping cart"));
        Assert.assertEquals("Shopping Cart", expected, actualtText);
//        2.13 Verify the quantity is 2
        String expectedQuantity = "2";
        String actualQuantity = driver.findElement(By.xpath("(//input[@value])[2]")).getAttribute("value");
        Assert.assertEquals("Verify quantity", expectedQuantity, actualQuantity);

//        2.14 Verify the Total $698.00
        String expectedTotal = "$698.00";
        String actualTotal = getTextFromElement(By.xpath("//span[@class='product-subtotal']"));
        Assert.assertEquals("Total", expectedTotal, actualTotal);


//        2.15 click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));

//       2.16 Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));

//        2.17 Verify the Text “Welcome, Please Sign In!”
        String expectedTextWelcome = "Welcome, Please Sign In!";
        String acttulwelcomeText = getTextFromElement(By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"));
        Assert.assertEquals("Welcome", expectedTextWelcome, acttulwelcomeText);

//        2.18 Click on “REGISTER” tab
        clickOnElement(By.xpath("//button[normalize-space()='Register']"));

//        2.19 Verify the text “Register”
        String verifyRegisterText = "Register";
        String actualWelcomeText = getTextFromElement(By.xpath("//h1[normalize-space()='Register']"));
        Assert.assertEquals("Registration", verifyRegisterText, actualWelcomeText);

//        2.20 Fill the mandatory fields
        //clicking on the register link
        // clickOnElement(By.linkText("Register"));

        //Select gender radio button
        clickOnElement(By.id("gender-female"));

        //Enter First name

        sendTextToElement(By.id("FirstName"), "Prime");

        // enter last name

        sendTextToElement(By.id("LastName"), "Testing");

        Random random = new Random();
        int ran = random.nextInt(1000);
        //enter email id
        sendTextToElement(By.id("Email"), "prime" + ran + "@gmail.com");

        //set password
        sendTextToElement(By.id("Password"), "prime123");
        //confirm password
        sendTextToElement(By.id("ConfirmPassword"), "prime123");

//        2.21 Click on “REGISTER” Button
        clickOnElement(By.name("register-button"));

//        2.22 Verify the message “Your registration completed”

        String actualtext = getTextFromElement(By.xpath("//div[@class='result']"));
        Assert.assertEquals("Registered successfully", "Your registration completed", actualtext);

//        2.23 Click on “CONTINUE” tab
        //Thread.sleep(1000);
        clickOnElement(By.xpath("//a[@class='button-1 register-continue-button']"));

//        2.24 Verify the text “Shopping card”
        String actulatShopping = getTextFromElement(By.xpath("//h1[normalize-space()='Shopping cart']"));
        Assert.assertEquals("Shopping cart", "Shopping cart", actulatShopping);

//        2.25 click on checkbox “I agree with the terms of service”
        clickOnElement(By.id("termsofservice"));

//        2.26 Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));

//        2.27 Fill the Mandatory fields
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "Prime");
        sendTextToElement(By.id("BillingNewAddress_LastName"), "Testing");
        sendTextToElement(By.id("BillingNewAddress_Email"), "prime00@gmail.com");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
        sendTextToElement(By.id("BillingNewAddress_City"), "London");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "653 London Road ");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "UB9 7OP");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "07499874657");

//        2.28 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
//        2.29 Click on Radio Button “2nd Day Air ($0.00)”
        clickOnElement(By.id("shippingoption_2"));

//        2.30 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

//        2.31 Select Radio Button “Credit Card”
        clickOnElement(By.xpath("(//input[@name='paymentmethod'])[2]"));
        Thread.sleep(1000);
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));

//        2.32 Select “Visa” From Select credit card dropdown
        selectByIndexFromDropDown(By.xpath("//select[@id='CreditCardType']"), 0);

//        2.33 Fill all the details
        sendTextToElement(By.id("CardholderName"), "Prime Testing");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "5105105105105100");
        selectByIndexFromDropDown(By.xpath("//select[@id='ExpireMonth']"), 6);
        selectByIndexFromDropDown(By.xpath("//select[@id='ExpireYear']"), 2);
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "999");

//        2.34 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

//        2.35 Verify “Payment Method” is “Credit Card”
        String exptPaymentText = "Payment Method: Credit Card";
        String actulPaymenttxt = getTextFromElement(By.xpath("//li[@class='payment-method']"));
        Assert.assertEquals("Payment method", exptPaymentText, actulPaymenttxt);

//        2.36 Verify “Shipping Method” is “2nd Day Air”
        String verifyElement = "Shipping Method: 2nd Day Air";
        String verifyActualement = getTextFromElement(By.xpath("//li[@class='shipping-method']"));
        Assert.assertEquals("Shipping method", verifyElement, verifyActualement);

//        2.37 Verify Total is “$698.00”
        String verifyTottal = "$698.00";
        String actualtoTal = getTextFromElement(By.xpath("//span[@class='value-summary']//strong[contains(text(),'$698.00')]"));
        Assert.assertEquals("Total", verifyTottal, actualtoTal);

//        2.38 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[normalize-space()='Confirm']"));

//        2.39 Verify the Text “Thank You”
        String actualThank = getTextFromElement(By.xpath("//h1[normalize-space()='Thank you']"));
        Assert.assertEquals("Thank you ", "Thank you ", actualThank);

//        2.40 Verify the message “Your order has been successfully processed!”
        String expectedMessageElement = "Your order has been successfully processed!";
        String actualMessageElement = getTextFromElement(By.xpath("//strong[normalize-space()='Your order has been successfully processed!']"));
        Assert.assertEquals("Order is completed ", expectedMessageElement, actualMessageElement);

//        2.41 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[normalize-space()='Continue']"));

//        2.42 Verify the text “Welcome to our store”
        String verifyText = "Welcome to our store";

//        2.43 Click on “Logout” link
        clickOnElement(By.xpath("//a[normalize-space()='Log out']"));

//        2.44 Verify the URL is “https://demo.nopcommerce.com/”
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals("Current Url", "https://demo.nopcommerce.com/", actualUrl);
        System.out.println(actualUrl);

    }

}
