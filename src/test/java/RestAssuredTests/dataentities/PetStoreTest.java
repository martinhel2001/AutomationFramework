package RestAssuredTests.dataentities;


// using API examples from https://petstore.swagger.io/#/pet

import BaseTest.BaseTest;
import RestAssuredTests.dataentities.petstore.Pet;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.hamcrest.core.IsEqual.equalTo;

import static io.restassured.RestAssured.given;

@Listeners(utils.Listeners.TestListener.class)

public class PetStoreTest extends BaseTest {
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createRequestSpecification() {
        requestSpec = new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").build();
    }

    @BeforeClass
    public static void createResponseSpecification() {
        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @DataProvider(name = "petIDsToRetrieve")
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][]{
                {1, "Doberman"},
                {2, "Husky"},
                {3, "Bulldog"}
        };
    }

    private Pet getPet(int petId){
        Pet pet = given().
                    log().all().
                    pathParam("petId",petId).when().
                    spec(requestSpec).
                when().
                    get("pet/{petId}").
                    as(Pet.class);

        return pet;
    }


    @Test
    public void testPet1(){
        Pet petToSearch = getPet(1);
        Assert.assertEquals(petToSearch.getName(),"doberman");
    }

    @Test (dataProvider = "petIDsToRetrieve")
    public void requestPetsFromDataprovider(int petId, String petName){
        Pet pet = getPet(petId);
        Assert.assertEquals(pet.getName(),petName);
    }

    @Test
    public void addNewPet(){
        Pet newPet = new Pet();

        newPet.setId(5);
    }
}
