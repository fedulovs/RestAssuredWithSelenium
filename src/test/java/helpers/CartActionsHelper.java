package helpers;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CartActionsHelper {

    public void deleteProductFromCart(String itemId, String amount) {
        Response response =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .body("removefromcart: " + itemId +
                                "itemquantity" + itemId + ": " + amount +
                                "updatecart: Update shopping cart")
                        .cookie("Nop.customer=095d8eaa-4685-442e-9110-a7718f6fd277;")
                        .when()
                        .post("http://demowebshop.tricentis.com/cart")
                        .then()
                        .statusCode(200)
                        .extract().response();

        System.out.println("Shopping cart was cleared");
    }
}
