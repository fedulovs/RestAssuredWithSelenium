import com.codeborne.selenide.Condition;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemoWebShopTests extends TestBase {
    
    @Test
    public void addProductAndCheckViaUi() {

        // Добавить продукт через API

        Response response =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .cookie("Nop.customer=5b7262cb-5325-490c-a031-65c890cd5e3a;")
                        .when()
                        .post("http://demowebshop.tricentis.com/addproducttocart/catalog/31/1/1")
                        .then()
                        .statusCode(200)
                        .body("success", is(true))
                        .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                        .extract().response();

        System.out.println(response.path("updatetopcartsectionhtml").toString());

        // Подложить куку

        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");

        getWebDriver().manage().addCookie(
                new Cookie("Nop.customer", "5b7262cb-5325-490c-a031-65c890cd5e3a"));

        // Проверить что продукт добавился через UI

        open("http://demowebshop.tricentis.com/cart");
        $(".product-name").shouldHave(Condition.text("14.1-inch Laptop"));
    }
}