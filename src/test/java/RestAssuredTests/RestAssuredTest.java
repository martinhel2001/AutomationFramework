package RestAssuredTests;

import dataentities.Location;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class RestAssuredTest {
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    // Let's use http://zippopotam.us/   as example for this!


    @BeforeClass
    public static void createRequestSpecification() {
        requestSpec = new RequestSpecBuilder().setBaseUri("http://zippopotam.us").build();
    }

    @BeforeClass
    public static void createResponseSpecification() {
        responseSpec = new ResponseSpecBuilder().
                            expectStatusCode(200).
                            expectContentType(ContentType.JSON).
                            build();

    }

    @DataProvider (name = "apiOptions")
    public static Object[][] zipCodesAndPlaces() {

        return new Object[][]{
                {"us", "90210", "Beverly Hills"},
                {"us", "12345", "Schenectady"},
                {"ca", "B2R", "Waverley"}
        };
    }


    @Test (dataProvider = "apiOptions")
    public void requestZipCodeFromZippopotam(String expectedCountryCode, String expectedZipCode, String expectedCityName){
        given().
                log().all().
                pathParam("countryCode",expectedCountryCode).pathParam("zipCode",expectedZipCode).
                spec(requestSpec).
        when().
                get("{countryCode}/{zipCode}").
        then().
                log().body().
                spec(responseSpec).
                assertThat().
//                    statusCode(200).
//                    contentType(ContentType.JSON).
                    body("places[0].'place name'", equalTo(expectedCityName));
    }

    @Test
    public void testWithExtract() {
        String placeName =
                    given().log().all().spec(requestSpec).
                    when().get("us/90210").
                    then().extract().path("places[0].'place name'");

        Assert.assertEquals(placeName, "Beverly Hills");
    }

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() { // DESERIALIZE
        Location location =
                given().
                        when().
                        get("http://api.zippopotam.us/us/90210").
                        as(Location.class);

        Assert.assertEquals(
                location.getPlaces().get(0).getPlaceName(),"Beverly Hills"
        );
    }

    @Test
    public void sendLvZipCode1050_checkStatusCode_expect200() {  // SERIALIZE

        Location location = new Location();
        location.setCountry("Netherlands");

        given().
                contentType(ContentType.JSON).
                body(location).
                log().body().
                when().
                post("http://localhost:9876/lv/1050").
                then().
                assertThat().
                statusCode(200);
    }
}
