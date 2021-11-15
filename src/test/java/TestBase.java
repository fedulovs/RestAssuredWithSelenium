import com.codeborne.selenide.Configuration;
import helpers.CartActionsHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    CartActionsHelper cart = new CartActionsHelper();

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @AfterEach
    void tearDown() {
        cart.deleteProductFromCart("2079352", "1");
    }
}
