package com.luma.testsuite;

import com.luma.customlisteners.CustomListeners;
import com.luma.pages.HomePage;
import com.luma.pages.MensPantPage;
import com.luma.pages.OverNightDuffle;
import com.luma.pages.ShoppingCartPage;
import com.luma.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class GearTest extends BaseTest {
    ShoppingCartPage shoppingCartPage;
    OverNightDuffle overNightDuffle;
    HomePage homePage;
    MensPantPage mensPantPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        shoppingCartPage = new ShoppingCartPage();
        overNightDuffle = new OverNightDuffle();
        homePage = new HomePage();
        mensPantPage = new MensPantPage();
    }

    @Test(groups = {"sanity" , "regression"})

    public void userShouldAddProductSuccessFullyToShoppingCart() throws InterruptedException {

        Thread.sleep(2000);
        homePage.mouseHoverToGearMenu();
        //* Click on Bags
        homePage.clickOnBag();
        //* Click on Product Name ‘Overnight Duffle’
        overNightDuffle.clickOnOvernightDuffelBag();
        //* Verify the text ‘Overnight Duffle’
        Assert.assertEquals(overNightDuffle.verifyTextOverNigtDuffelBag(), "Overnight Duffle");
        //* Change Qty 3
        overNightDuffle.chageQtyOfBag();
        //* Click on ‘Add to Cart’ Button.
        overNightDuffle.clickOnAddToCartProduct();
        //* Verify the text ‘You added Overnight Duffle to your shopping cart.’
        Assert.assertEquals(overNightDuffle.verifyTextAddedToShoppingCartSuccessfully(), "You added Overnight Duffle to your shopping cart.");
        //* Click on ‘shopping cart’ Link into message
        overNightDuffle.clickOnShoppingCartLink();
        //* Verify the product name ‘Cronus Yoga Pant’
        Assert.assertEquals(shoppingCartPage.verifyTextBagName(), "Overnight Duffle");
        //* Verify the Qty is ‘3’
        Assert.assertEquals(shoppingCartPage.verifyQtyOfProduct(), "3");
        //* Verify the product price ‘$135.00’
        Assert.assertEquals(shoppingCartPage.verifyProductPrice(), "$135.00");
        //* Change Qty to ‘5’
        shoppingCartPage.changeQtyOfProduct();
        //* Click on ‘Update Shopping Cart’ button
        shoppingCartPage.updateShoppingCart();
        //* Verify the product price ‘$225.00’
        // waitUntilVisibilityOfElementLocated(By.xpath("(//span[@class='price'])[12]"),5);
        Thread.sleep(2000);
        Assert.assertEquals(shoppingCartPage.verifyUpdatedProductPrice(), "$225.00");
    }

}
