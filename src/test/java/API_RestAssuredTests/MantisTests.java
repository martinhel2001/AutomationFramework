package API_RestAssuredTests;

import BaseTest.BaseTest;
import Connectors.dataentities.mantis.Category;
import Connectors.dataentities.mantis.Priority;
import Connectors.dataentities.mantis.Project;
import Connectors.dataentities.mantis.Severity;
import Connectors.mantis.MantisConnector;
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
        System.out.println("Status code: "+ statusCodeReceived);
    }

}
