package API_RestAssuredTests;

import Connectors.dataentities.mantis.Category;
import Connectors.dataentities.mantis.Priority;
import Connectors.dataentities.mantis.Project;
import Connectors.dataentities.mantis.Severity;
import Connectors.mantis.MantisConnector;
import org.testng.annotations.Test;

public class MantisTests {
    MantisConnector mantisAPI = new MantisConnector();

    @Test
    public void createSimpleMantisIssue()  {
        int statusCodeReceived = mantisAPI.createSimpleIssue("Resumen de bug simple","Descripcion de bug simple", Project.AUTOMATION_BUGS, Category.Stress);
        System.out.println("Status code: "+ statusCodeReceived);
    }

    @Test
    public void createCompleteMantisIssue(){
        int statusCodeReceived = mantisAPI.createCompleteIssue("Resumen de bug completo","Descripcion de bug completo","Info Adicional",Project.AUTOMATION_BUGS,Category.API, Priority.immediate, Severity.block,false);
        System.out.println("Status code: "+ statusCodeReceived);
    }
}
