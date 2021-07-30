package RestAssuredTests;


// using API examples from https://petstore.swagger.io/#/pet

import BaseTest.BaseTest_API;
import RestAssuredTests.dataentities.petstore.ApiResponse;
import RestAssuredTests.dataentities.petstore.Category;
import RestAssuredTests.dataentities.petstore.Pet;
import RestAssuredTests.dataentities.petstore.PetStoreAPI;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.post;
import static org.hamcrest.core.IsEqual.equalTo;

import static io.restassured.RestAssured.given;

@Listeners(utils.Listeners.TestListener.class)

public class PetStoreTest extends BaseTest_API {

    PetStoreAPI pstoreAPI = new PetStoreAPI();

    @DataProvider(name = "petIDsToRetrieve")
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][]{
                {1, "Doberman"},
                {2, "Husky"},
                {3, "Bulldog"}
        };
    }

    @Test
    public void testPet1(){
        Pet petToSearch = pstoreAPI.getPet(1);
        Assert.assertEquals(petToSearch.getName(),"doberman");
    }

    @Test (dataProvider = "petIDsToRetrieve")
    public void requestPetsFromDataprovider(int petId, String petName){
        Pet pet = pstoreAPI.getPet(petId);
        Assert.assertEquals(pet.getName(),petName);
    }

    @Test
    public void addNewPetTest(){
        int petId = 9;
        String petName="Diana";
        List<String> photoURLs= new ArrayList<>();
        photoURLs.add("https://www.puntoqa.com.ar");
        photoURLs.add("https://www.gremio.com");

        Pet newlyPet = new PetStoreAPI().createNewPet(petId,petName,"active",photoURLs);

        Pet insertedPet = pstoreAPI.postPet(newlyPet);

        Pet petVerification = pstoreAPI.getPet(insertedPet.getId());
        Assert.assertEquals(petVerification.getName(),petName);
    }

    @Test
    public void updatePet() {
        int petId = 9;
        String newName = "Lucrecia";
        String newStatus = "invalid";

        Pet petToBeModified = pstoreAPI.getPet(petId);

        petToBeModified.setName(newName);
        petToBeModified.setStatus(newStatus);

        pstoreAPI.updatePet(petToBeModified);

        Assert.assertEquals(pstoreAPI.getPet(petToBeModified.getId()).getName(),newName, "Pet Name not updated properly");
        Assert.assertEquals(pstoreAPI.getPet(petToBeModified.getId()).getStatus(),newStatus,"Pet Status not updated properly");
    }
}
