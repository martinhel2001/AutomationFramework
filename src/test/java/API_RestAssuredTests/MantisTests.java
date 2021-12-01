package API_RestAssuredTests;

import Connectors.dataentities.mantis.Category;
import Connectors.dataentities.mantis.Priority;
import Connectors.dataentities.mantis.Project;
import Connectors.dataentities.mantis.Severity;
import Connectors.mantis.MantisConnector;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static Connectors.mantis.MantisConnector.encodeFileToBase64Binary;

public class MantisTests {
    MantisConnector mantisAPI = new MantisConnector();

    @Test
    public void createSimpleMantisIssue()  {
        int statusCodeReceived = mantisAPI.createSimpleIssue("Resumen de bug simple","Descripcion de bug simple", Project.AUTOMATION_BUGS, Category.Stress);
        System.out.println("Status code: "+ statusCodeReceived);
    }

    @Test
    public void createCompleteMantisIssue(){
        int statusCodeReceived = mantisAPI.createCompleteIssueWithoutAttachement("Resumen de bug completo","Descripcion de bug completo","Info Adicional",Project.AUTOMATION_BUGS,Category.API, Priority.immediate, Severity.block,false);
        System.out.println("Status code: "+ statusCodeReceived);
    }

    @Test
    public void createCompleteMantisIssueWithAttachment() throws Exception {

        /* METODO 1 BASE 64
        // load file from /src/test/resources
        ClassLoader classLoader = getClass().getClassLoader();
        File inputFile = new File(classLoader
                .getResource("C:\\Automation\\mama.png")
                .getFile());

        byte[] fileContent = FileUtils.readFileToByteArray(inputFile);
        String encodedString = Base64
                .getEncoder()
                .encodeToString(fileContent);
*/

        File f =  new File("C:\\Automation\\mama.png");
        String encodstring = encodeFileToBase64Binary(f);
        System.out.println("Encoded image: "+encodstring);

        int statusCodeReceived = mantisAPI.createCompleteIssueWithAttachment("Resumen de bug completo","Descripcion de bug completo","Info Adicional",Project.AUTOMATION_BUGS,Category.API,Priority.immediate, Severity.block,encodstring,false);
        System.out.println("Status code: "+ statusCodeReceived);
    }


}
