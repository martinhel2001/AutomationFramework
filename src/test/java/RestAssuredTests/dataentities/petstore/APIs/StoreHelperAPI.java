package RestAssuredTests.dataentities.petstore.APIs;

import RestAssuredTests.dataentities.petstore.Order;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.List;

import static io.restassured.RestAssured.given;

public class StoreHelperAPI {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;
    private List<String> inventories;

    public StoreHelperAPI(){
        this.requestSpec = new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").build();
        this.responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    public static String getInventoryStatus(){
        return given().
                log().all().
                spec(requestSpec).
        when().
                get("/store/inventory").
        then().
                log().body().extract().body().asString();
    }

    public int placeOrder(Order orderToPlace){
        return given().contentType(ContentType.JSON).
                log().all().
                spec(requestSpec).
                body(orderToPlace).
        when().
                post("/store/order").
        then().
                log().body().extract().statusCode();
    }

    public Order getOrder(int orderId){
        return given().
                    log().all().
                    pathParam("orderId",orderId).
                    spec(requestSpec).
                when().
                    get("/store/order/{orderId}").
                then().
                    log().body().
                    extract().as(Order.class);
    }

    public int deleteOrder(String orderId){
        return given().
                log().all().
                pathParam("orderId", orderId).
                spec(requestSpec).
        when().
                delete("store/order/{orderId}").
        then().
                log().body().
                spec(responseSpec).
                extract().statusCode();
    }
}
