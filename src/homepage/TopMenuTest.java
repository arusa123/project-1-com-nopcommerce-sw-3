package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utility.Utilities;

public class TopMenuTest extends Utilities {
    @Before
    public void browserSetUp() {
        openBrowser("Chrome", "https://demo.nopcommerce.com/");
    }
    public void selectMenu(String menu) {

        if (menu.equalsIgnoreCase("Computers")) {
            clickOnElement(By.linkText("Computers"));
        } else if (menu.equalsIgnoreCase("Electronics")) {
            clickOnElement(By.linkText("Electronics"));
        } else if (menu.equalsIgnoreCase("Apparel")) {
            clickOnElement(By.linkText("Apparel "));
        } else {
            System.out.println("Wrong selection");
        }

    }
    @Test
    public void verifyPageNavigation(){
        selectMenu("Electronics");
        verifyText( By.xpath("//div[@class='page-title']"),"Electronics");
    }
@After
    public void tearDown(){
        closeBrowser();

}

}
