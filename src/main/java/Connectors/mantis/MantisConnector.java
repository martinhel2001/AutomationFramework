package Connectors.mantis;

import Connectors.dataentities.mantis.Category;
import Connectors.dataentities.mantis.Priority;
import Connectors.dataentities.mantis.Project;
import Connectors.dataentities.mantis.Severity;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class MantisConnector {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    public MantisConnector(){
        this.requestSpec = given().
                log().all().
                baseUri("https://mantisautomation.000webhostapp.com/mantisbt/api/rest/issues/").
                contentType(ContentType.JSON).
                header("Authorization", "-e9SzcXWvYiDYQnHmjWCgLF1aFUjOjGq").
                header("Host","mantisautomation.000webhostapp.com");

        this.responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    public int postIssue(String jsonBody) {
        requestSpec.body(jsonBody);

        Response response = requestSpec.post("");
        System.out.println("Obtained status code: "+response.getStatusCode());
        System.out.println("Obtained status message: "+response.getStatusLine());
        System.out.println("Obtained Response body: "+response.getBody().toString());

       return response.getStatusCode();
    }


    public int createSimpleIssue(String summary, String desc, Project proj, Category cat) {
        JSONObject jo = new JSONObject();
        JSONObject category = new JSONObject();
        JSONObject project = new JSONObject();

        jo.put("summary",summary);
        jo.put("description", desc);
        category.put("name",cat.name());
        project.put("name",proj.getProjectName());
        jo.put("category",category);
        jo.put("project",project);

        return postIssue(jo.toString());
    }

    public int createCompleteIssue(String summary, String desc, String additionalInfo, Project proj, Category cat, Priority prio, Severity sev, boolean isSticky){
        JSONObject jo = new JSONObject();
        JSONObject category = new JSONObject();
        JSONObject project = new JSONObject();
        JSONObject priority = new JSONObject();
        JSONObject severity = new JSONObject();
        JSONObject reproducibility = new JSONObject();
        JSONArray tagsArray = new JSONArray();
        JSONObject tag = new JSONObject();

        jo.put("summary",summary);
        jo.put("description", desc);
        jo.put("additional_information",additionalInfo);
        jo.put("sticky",isSticky);

        tag.put("name","automation bug");
        tagsArray.add(tag);

        category.put("name",cat.name());
        project.put("name",proj.getProjectName());
        priority.put("name",prio.name());
        severity.put("name",sev.name());
        reproducibility.put("reproducibility","always");


        jo.put("category",category);
        jo.put("priority",priority);
        jo.put("project",project);
        jo.put("tags",tagsArray);


        return postIssue(jo.toString());
    }


}
