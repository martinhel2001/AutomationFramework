package RestAssuredTests.dataentities.petstore.APIs;

import RestAssuredTests.dataentities.petstore.Pet;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.mortbay.jetty.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PetHelperAPI {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    public PetHelperAPI(){
        this.requestSpec = new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2").build();
        this.responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    public Pet getPetById(String petId){
        given().
            log().all().
            pathParam("petId",petId).when().
            spec(requestSpec).
        when().
            get("pet/{petId}").
        then().
            log().body().
            spec(responseSpec).
            assertThat().statusCode(200);

        Pet pet = given().
                pathParam("petId",petId).when().
                spec(requestSpec).
                when().
                get("pet/{petId}").as(Pet.class);


        return pet;
    }

    public List<Pet> getListOfPetsByStatus(String status){
        return Arrays.asList(
                given().
                    log().all().
                    queryParam("status",status).spec(requestSpec).
                when().
                    get("pet/findByStatus").
                then().
                    extract().response().body().as(Pet[].class));
    }

    public Pet postPet(Pet newPet) {
       Pet pet  = given().
                contentType(ContentType.JSON).
                log().all().
                spec(requestSpec).
                body(newPet).
        when().
                post("/pet").
       then().
               spec(responseSpec).assertThat().statusCode(200).
               extract().body().as(Pet.class);


        return pet;
    }

    public Pet updatePet(Pet newPet) {
        Pet pet  =  given().
                        contentType(ContentType.JSON).
                        log().all().
                        spec(requestSpec).
                        body(newPet).
                    when().
                        put("/pet").
                    then().
                        assertThat().
                        statusCode(200).
                        extract().
                        as(Pet.class);

        return pet;
    }

    public Pet createNewPet(String petId, String petName, String petStatus, List<String> photoURLs){
        Pet newPet = new Pet();
        newPet.setId(petId);
        newPet.setName(petName);
        newPet.setStatus(petStatus);
        newPet.setPhotoUrls(photoURLs);

        return newPet;
    }

    public void deletePet(String petId) {
        given().
                log().all().
                pathParam("petId", petId).
                spec(requestSpec).
        when().
                delete("pet/{petId}").
        then().
                log().body().
                spec(responseSpec).
                assertThat().statusCode(200);
    }
}
