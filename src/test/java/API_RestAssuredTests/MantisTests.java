package API_RestAssuredTests;

import BaseTest.BaseTest;
import dataentities.mantis.Category;
import dataentities.mantis.Priority;
import dataentities.mantis.Project;
import dataentities.mantis.Severity;
import Connectors.MantisConnector;
import org.testng.annotations.Test;

public class MantisTests extends BaseTest {

    MantisConnector mantisAPI = new MantisConnector(testsConfig.getMantis_url(),testsConfig.getMantis_host(),testsConfig.getMantis_token());

    @Test
    public void createSimpleMantisIssue()  {
        String statusCodeReceived = mantisAPI.createSimpleIssue("Resumen de bug simple","Descripcion de bug simple", Project.AUTOMATION_BUGS, Category.Stress);
        System.out.println("Status code: "+ statusCodeReceived);
    }

    @Test
    public void createCompleteMantisIssue(){
        String statusCodeReceived = mantisAPI.createCompleteIssueWithoutAttachment("Resumen de bug completo","Descripcion de bug completo","Info Adicional",Project.AUTOMATION_BUGS,Category.API, Priority.immediate, Severity.block,false);
        System.out.println("Status code : "+ statusCodeReceived);
    }

}
