package DataDriven;

import BaseTest.BaseTest;
import baseMain.spreadsheetAutomation.GoogleSheetAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class DataDrivenTest extends BaseTest {

    private String range = "UserInfo!A2:E";


    @Test (enabled = false) // DISABLED porque falta actualizar el permiso para ver el Gspreadsheet
    public void reviewDataFromGSpreadsheet() throws IOException {
         GoogleSheetAPI sheetAPI = new GoogleSheetAPI();
         List<List<Object>> values = sheetAPI.getSpreadSheetRecords("1N6Cj4wogX-FBVnFml5jLyuUbbcvYOr41VQ91q_OGS-E", range);
        for (List<Object> row : values) {
            for (int i=0; i<row.size();i++){
                System.out.format("El valor de la fila %s y columna %s  es: %s \n ", row.get(0).toString(), values.get(0).get(i), row.get(i).toString());
            }
        }
    }

    @Test (dataProvider = "dpGSpreadsheet", dataProviderClass = DataProviderSources.class, enabled = false) // DISABLED porque falta actualizar el permiso para ver el Gspreadsheet
    public void testDataProviderFromGspreadsheet() throws IOException {
        GoogleSheetAPI sheetAPI = new GoogleSheetAPI();
        List<List<Object>> values = sheetAPI.getSpreadSheetRecords("1N6Cj4wogX-FBVnFml5jLyuUbbcvYOr41VQ91q_OGS-E", range);
        for (List<Object> row : values) {
            for (int i=0; i<row.size();i++){
                System.out.format("El valor de la fila %s y columna %s  es: %s \n ", row.get(0).toString(), values.get(0).get(i), row.get(i).toString());
            }
        }
    }

    // DataProviders from another Class and using Method names as conditions for data retrieved
    @Test (dataProvider = "data-provider", dataProviderClass = DataProviderSources.class)
    public void Sum (int a, int b, int result) {
        int sum = a + b;
        Assert.assertEquals(result, sum);
    }

    @Test (dataProvider = "data-provider", dataProviderClass = DataProviderSources.class)
    public void Diff (int a, int b, int result) {
        int diff = a - b;
        Assert.assertEquals(result, diff);
    }

    @Test(dataProvider = "csvData", dataProviderClass = DataProviderSources.class, description = "Test using CSV file as Data Provider")
    public void verifyLoginUsingCsv (String username, String password, String testDescription)
    {
        System.out.println("Reading data from CSV file!");
        System.out.println("Username: "+username+", Password: "+password+", Test Description: "+testDescription);
    }

    @Test(dataProvider = "excelData", dataProviderClass = DataProviderSources.class, description = "Test using EXCEL file as Data Provider")
    public void read(String name, String lastname, String occupation) {
        System.out.println("Name: "+name + ":" +", Lastname: "+lastname+", Occupation: "+occupation);
    }


}
