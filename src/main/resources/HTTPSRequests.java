package restAssuredTest;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.in;

//Use BBD style Gerkin - keywords
public class HTTPSRequests {

    @Test
    void getShopType(){
        given()
                .when()
                    .post("https://backend-api.myginne.com/V2/19/ShopType")
                .then()
                    .statusCode(200)
                    .statusLine("HTTP/1.1 200 OK")
                    .body("status",equalTo(200))
                    .assertThat().body("message", equalTo("Shop Types"))
                    .assertThat().body("data[0].id",equalTo(1))
                    .assertThat().body("data[0].shop_type",equalTo("Supermarket"))
                    .assertThat().body("data[0].image_url", equalTo(""))
                    .assertThat().body("data[1].id",equalTo(2))
                    .assertThat().body("data[1].shop_type",equalTo("Fruits & Vegetables"))

                .log().all();

    }

    @Test
    void getCancelReason(){
        given()
                .when()
                .contentType(ContentType.JSON)
                .header("lang", "en_US")
                    .post("https://backend-api.myginne.com/V2/19/CancelReason")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body("status",equalTo(200))
                .assertThat().body("message",equalTo("CancelReason List"))

                .assertThat().body("data[0].id",equalTo(2))
                .assertThat().body("data[0].name",equalTo("I have changed my mind"))
                .assertThat().body("data[1].id",equalTo(3))
                .assertThat().body("data[1].name",equalTo("I bought the wrong item(s)"))
                .assertThat().body("data[2].id",equalTo(4))
                .assertThat().body("data[2].name",equalTo("I found a cheaper alternative"))
                .assertThat().body("data[3].id",equalTo(5))
                .assertThat().body("data[3].name",equalTo("I placed a duplicate order"))
                .assertThat().body("data[4].id",equalTo(6))
                .assertThat().body("data[4].name",equalTo("I received negative feedback about the item"))
                .log().all();

    }

    @Test
    public void getAddressType(){
        given()
                .when()
                .contentType(ContentType.JSON)
                .header("lang", "en_US")
                .post("https://backend-api.myginne.com/V2/19/GetAddressType")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
// Performing assertions to validate status code and response body in API testing.
                .body("status_code",equalTo(200))
                .assertThat().body("status",equalTo("Address Types Founds"))
                .assertThat().body("response[0].id",equalTo(1))
                .assertThat().body("response[0].name",equalTo("Home"))
                .assertThat().body("response[0].name_ar",equalTo("منزل"))
                .assertThat().body("response[1].id",equalTo(2))
                .assertThat().body("response[1].name",equalTo("Office"))
                .assertThat().body("response[1].name_ar",equalTo("مكتب"))
                .assertThat().body("response[3].id",equalTo(3))
                .assertThat().body("response[3].name",equalTo("Appartment"))
                .assertThat().body("response[3].name_ar",equalTo("شقه"))
                .assertThat().body("response[2].id",equalTo(4))
                .assertThat().body("response[2].name",equalTo("Other"))
                .assertThat().body("response[2].name_ar",equalTo("اخري"))
                .log().all();
    }
    @Test
    public void getCustomerDetails(){
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"user_id\": 0,\n" +
                        "  \"mobile\": \"1020304055\",\n" +
                        "  \"email\": \"string\"\n" +
                        "}")
                .when()
                .post("https://backend-api.myginne.com/V2/19/GetCustomerDetails")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Content-Type","application/json").log().all();

    }

    @Test
    public void storeLogin(){
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"login_id\": \"tshop@gmail.com\",\n" +
                        "  \"password\": \"Abc@123\"\n" +
                        "}")
                .when()
                .post("https://backend-uat-api.myginne.com/store-web/retailer-login")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Content-Type","application/json").log().all();
    }

    @Test
    public void verifySignupMobile(){
        given()
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post("https://backend-api.myginne.com/V2/16/VerifySignupMobile")
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Content-Type","application/json").log().all();

    }
    @Test
    public void inventoryUpdate(){
        String inventUpdate = "{\n" +
                "    \"user_id\": 326,\n" +
                "    \"store_id\": 354,\n" +
                "    \"product_id\": 43,\n" +
                "    \"cost_price\": 90,\n" +
                "    \"sale_price\": 82,\n" +
                "    \"qty\": 800\n" +
                "}";
        String InventoryUrl = "https://backend-uat-api.myginne.com/store-web/updateinventoryonboard";

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InRzaG9wQGdtYWlsLmNvbSIsInRpbWVzdGFtcCI6IjIwMjQtMDctMjQgMDA6MTc6MzYuOTczNjI3KzAzOjAwIn0.gq4EU3_qFTxK8lQXSsBUW6bWeSeOoRzH_tWoLnd-Uhg")
                .body(inventUpdate)
                .when()
                .post(InventoryUrl)
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .log().all();
    }


}
