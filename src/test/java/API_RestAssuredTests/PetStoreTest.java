package API_RestAssuredTests;


// using API examples from https://petstore.swagger.io/#/pet

import BaseTest.BaseTest_API;
import API_RestAssuredTests.dataentities.petstore.APIs.StoreHelperAPI;
import API_RestAssuredTests.dataentities.petstore.Order;
import API_RestAssuredTests.dataentities.petstore.Pet;
import API_RestAssuredTests.dataentities.petstore.APIs.PetHelperAPI;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.post;

import static io.restassured.RestAssured.given;
import static java.lang.String.valueOf;

@Listeners(utils.Listeners.TestListener.class)

public class PetStoreTest extends BaseTest_API {

    PetHelperAPI petAPI = new PetHelperAPI();
    StoreHelperAPI storeAPI = new StoreHelperAPI();
    String petIDtoDelete="";

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
        Pet petToSearch = petAPI.getPetById("1");
        Assert.assertEquals(petToSearch.getName(),"doberman");
    }

    @Test (dataProvider = "petIDsToRetrieve")
    public void requestPetsFromDataprovider(int petId, String petName){
        Pet pet = petAPI.getPetById(valueOf(petId));
        Assert.assertEquals(pet.getName(),petName);
    }

    @Test
    public void addNewPetTest() throws InterruptedException {
        String petId = "0";
        String petName="Diana";
        List<String> photoURLs= new ArrayList<>();
        photoURLs.add("https://www.puntoqa.com.ar");
        photoURLs.add("https://www.gremio.com");

        Pet newlyPet = new PetHelperAPI().createNewPet(petId,petName,"active",photoURLs);

        Pet insertedPet = petAPI.postPet(newlyPet);
        System.out.println("Pet ID assigned to new Pet: "+insertedPet.getId());
        Thread.sleep(15000);
        Pet petVerification = petAPI.getPetById(insertedPet.getId());
        System.out.println("Pet ID insertado: "+insertedPet.getId());
        System.out.println("Pet name grabado: "+insertedPet.getName());
        petIDtoDelete = insertedPet.getId();
//        Assert.assertEquals(petVerification.getName(),petName,"Pet name didn't match expected "+petName+" and got "+petVerification.getName());
    }

    @Test
    public void updatePet() {
        String petId = "9";
        String newName = "Lucrecia";
        String newStatus = "invalid";

        // Get Pet that will be modified
        Pet petToBeModified = petAPI.getPetById(petId);

        // Modify values in Pet
        petToBeModified.setName(newName);
        petToBeModified.setStatus(newStatus);

        // Update Pet and validates PUT responses with new values
        Pet petModified = petAPI.updatePet(petToBeModified);
        Assert.assertEquals(petModified.getName(),newName, "Pet Name not updated properly according to PUT");
        Assert.assertEquals(petModified.getStatus(),newStatus,"Pet Status not updated properly according to PUT");

        // Get Pet again and validates new values in response
        Pet petRetrieved = petAPI.getPetById(petId);
        Assert.assertEquals(petRetrieved.getName(),newName, "Pet Name not updated properly according to GET");
        Assert.assertEquals(petRetrieved.getStatus(),newStatus,"Pet Status not updated properly according to GET");
    }

    @Test
    public void getPetsByStatus() {
        String status = "available";

        List<Pet> petList = petAPI.getListOfPetsByStatus(status);

        for(Pet pet:petList){
            System.out.println("Pet ID: "+pet.getId());
            System.out.println("Pet name: "+pet.getName());
            System.out.println("Pet status: "+pet.getStatus());
        }
        log.info("###");
        System.out.println("Qty of pets on status "+status+": "+petList.size());
    }

    @Test (dependsOnMethods = {"addNewPetTest"})
    public void deletePetTest() {
        petAPI.deletePet(petIDtoDelete);

        Assert.assertTrue(petAPI.getPetById(petIDtoDelete).getStatus().equals("1"));
    }

    @Test
    public void getInventoriesStatus() {
        String inventories = storeAPI.getInventoryStatus();
        System.out.println(inventories);
    }

    @Test
    public void placeOrderAndGetIt() {
        Order orderToPlace = new Order(1,9,5,"2021-08-04T20:50:06.467Z","placed",true);
        int statusCode = storeAPI.placeOrder(orderToPlace);
        Assert.assertEquals(statusCode,200);

        Order orderRetrieved = storeAPI.getOrder(orderToPlace.getId());
        Assert.assertEquals(orderRetrieved.getPetId(),orderToPlace.getPetId());
    }

    @Test
    public void placeOrderAndDeleteIt() {
        Order orderToPlace = new Order(8,10,3,"2021-08-04T20:50:06.467Z","placed",true);
        int statusCode = storeAPI.placeOrder(orderToPlace);
        Assert.assertEquals(statusCode,200);

        Assert.assertEquals(storeAPI.deleteOrder(valueOf(orderToPlace.getId())),200);

    }

}
