import baseMain.spreadsheetAutomation.GoogleSheetAPI;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import static baseMain.spreadsheetAutomation.GetGoogleSheetData.getAllCurrentPromoInfo;

public class DataDrivenTest extends BaseTest{

    private String range = "UserInfo!A2:E";
    String userName = "johns";

    @Test
    public void getInfoFromGoogleSpreasheet() throws InterruptedException, IOException, GeneralSecurityException {
        List<List<Object>> promoInfo = getAllCurrentPromoInfo();
    }

    @Test
    public void verifyProfileInfo() throws IOException {
        //login(userName,password);
        //String[] userProfileInfo = getProfileInfo();
        //List<String> userData = Arrays.asList(userProfileInfo);
        //Get data from sheet  and verify first profile info of john
        GoogleSheetAPI sheetAPI = new GoogleSheetAPI();
        List<List<Object>> values = sheetAPI.getSpreadSheetRecords("1N6Cj4wogX-FBVnFml5jLyuUbbcvYOr41VQ91q_OGS-E", range);
        for (List<Object> row : values) {
            for (int i=0; i<row.size();i++){
                System.out.format("El valor de la fila %s y columna %s  es: %s \n ", row.get(0).toString(), values.get(0).get(i), row.get(i).toString());
            }
        }
    }

    public String[] getProfileInfo() {
        //Navigate to Profile page
        //driver.findElement(By.id("user-profile")).click();
        String firstName = driver.findElement(By.id("firstname")).getText();
        String lastName = driver.findElement(By.id("lastname")).getText();
        String age = driver.findElement(By.id("user-age")).getText();
        String sex = driver.findElement(By.id("user-sex")).getText();
        String[] arrayUserInfo = {firstName, lastName, age, sex};
        return arrayUserInfo;
    }

    public void login(String userName, String password) {
        driver.findElement(By.id("session_key-login")).sendKeys(userName);
        driver.findElement(By.id("session_password-login")).sendKeys(password);
        driver.findElement(By.id("signin")).click();
    }
}
