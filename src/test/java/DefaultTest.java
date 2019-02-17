import com.gmail.igorson090395.Elements.Laptops.LaptopPropertiesBuilder;
import com.gmail.igorson090395.Elements.Laptops.Laptops;
import com.gmail.igorson090395.Utils.Core;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static com.gmail.igorson090395.TabManager.Category.Categories.COMPUTERS_TECH;
import static com.gmail.igorson090395.TabManager.Product.Product.ComputersTech.LAPTOP;
import static com.gmail.igorson090395.TabManager.TabManager.tabManager;


public class DefaultTest {
    private static SoftAssert softAssert = new SoftAssert();
    private String firstProductTitleInList;

    @BeforeSuite
    public void navigate() {
        Core.getInstance().getDriver().get(Core.getInstance().getSpecialKeys().get("site"));
    }

    @Description("Тестовое задание СберТех")
    @Test(description = "Тестовое задание СберТех", priority = 0)
    @Parameters({"price", "brands"})
    public void defaultTest(String price, String brands) {
        goTo();
        sortTesting(price, brands);
        checkAmount();
        checkSavedTitle();
        System.out.println("----------------TEST-ENDED----------------");
    }

    @Step("1-5 steps")
    public void goTo() {
        tabManager().go(COMPUTERS_TECH).go(LAPTOP);
    }

    @Step("6-8 steps")
    public void sortTesting(String price, String brands) {
        Laptops.getInstance().sortWithProperties(new LaptopPropertiesBuilder().withPrice(price).withBrands(parseBrands(brands)));
    }

    @Step("10-11 steps")
    public void checkAmount() {
        softAssert.assertEquals(Laptops.getInstance().getGoodsList().size(), 10);
        firstProductTitleInList = Laptops.getInstance().getProductTitle(Laptops.getInstance().getGoodsList().get(0));
    }

    @Step("12-14 steps")
    public void checkSavedTitle() {
        Laptops.getInstance().search(firstProductTitleInList);
        softAssert.assertEquals(firstProductTitleInList, Laptops.getInstance().getProductTitle(Laptops.getInstance().getGoodsList().get(0)));
    }

    @AfterTest
    public void assertAll() {
        softAssert.assertAll();
        tabManager().goToMainPage();
        softAssert = new SoftAssert();
    }

    @AfterSuite
    public void close() {
        Core.getInstance().getDriver().close();
    }

    private String[] parseBrands(String brands) {
        return brands.split("\\s*,\\s*");
    }
}
