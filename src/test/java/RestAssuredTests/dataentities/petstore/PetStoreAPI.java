package RestAssuredTests.dataentities.petstore;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PetStoreAPI {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    public PetStoreAPI(){
        this.requestSpec = new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").build();
        this.responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    public Pet getPet(int petId){
        Pet pet = given().
                log().all().
                pathParam("petId",petId).when().
                spec(requestSpec).
                when().
                get("pet/{petId}").
                as(Pet.class);

        return pet;
    }

    public void postPet(Pet newPet) {
        given().
                contentType(ContentType.JSON).
                log().all().
                spec(requestSpec).
                body(newPet).
        when().
                post("/pet").
        then().
                assertThat().statusCode(200);
    }

    public Pet createNewPet(int petId, String petName, String petStatus, List<String> photoURLs){
        Pet newPet = new Pet();
        newPet.setId(petId);
        newPet.setName(petName);
        newPet.setStatus(petStatus);
        newPet.setPhotoUrls(photoURLs);

        return newPet;
    }
}
