package Connectors.mantis;

import Connectors.dataentities.mantis.Category;
import Connectors.dataentities.mantis.Priority;
import Connectors.dataentities.mantis.Project;
import Connectors.dataentities.mantis.Severity;
import com.google.api.client.util.Base64;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileInputStream;

import static io.restassured.RestAssured.given;

public class MantisConnector {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    public MantisConnector(String mantisURL, String host, String token){
        this.requestSpec = given().
                //log().all().
                baseUri(mantisURL+"/api/rest/issues/").
                contentType(ContentType.JSON).
                header("Authorization", token).
                header("Host",host);

        this.responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    public String postIssue(String jsonBody) {
        requestSpec.body(jsonBody);

        Response response = requestSpec.post("");
        System.out.println("Obtained status message: "+response.getStatusLine());
        String mantisID = StringUtils.substringAfter(response.getStatusLine(),"Created with id ");
        System.out.println();

       //return response.getStatusCode();
        return mantisID;
    }


    public String createSimpleIssue(String summary, String desc, Project proj, Category cat) {
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

    public String createCompleteIssueWithoutAttachment(String summary, String desc, String additionalInfo, Project proj, Category cat, Priority prio, Severity sev, boolean isSticky){
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

        System.out.println("Complete issue body: "+jo.toString());
        return postIssue(jo.toString());
    }

    public static String encodeFileToBase64Binary(File file) throws Exception{
        FileInputStream fileInputStreamReader = new FileInputStream(file);
        byte[] bytes = new byte[(int)file.length()];
        fileInputStreamReader.read(bytes);
        return new String(Base64.encodeBase64(bytes), "UTF-8");
    }
    public String createCompleteIssueWithAttachment(String summary, String desc, String additionalInfo, Project proj, Category cat, Priority prio, Severity sev, String fileBase64, boolean isSticky){
        JSONObject jo = new JSONObject();
        JSONObject category = new JSONObject();
        JSONObject project = new JSONObject();
        JSONObject priority = new JSONObject();
        JSONObject severity = new JSONObject();
        JSONObject reproducibility = new JSONObject();
        JSONArray tagsArray = new JSONArray();
        JSONObject tag = new JSONObject();
        JSONArray filesArray = new JSONArray();
        JSONObject file = new JSONObject();

        jo.put("summary",summary);
        jo.put("description", desc);
        jo.put("additional_information",additionalInfo);
        jo.put("sticky",isSticky);

        tag.put("name","automation bug");
        tagsArray.add(tag);

        file.put("name","screenshot");
        file.put("content",fileBase64);
        filesArray.add(file);

        category.put("name",cat.name());
        project.put("name",proj.getProjectName());
        priority.put("name",prio.name());
        severity.put("name",sev.name());
        reproducibility.put("reproducibility","always");


        jo.put("category",category);
        jo.put("priority",priority);
        jo.put("project",project);
        jo.put("tags",tagsArray);
        jo.put("files",filesArray);


        return postIssue(jo.toString());
    }


}
