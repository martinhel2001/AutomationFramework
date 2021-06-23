import BaseTest.BaseTest;
import baseMain.spreadsheetAutomation.GoogleSheetAPI;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class DataDrivenTest extends BaseTest {

    private String range = "UserInfo!A2:E";

    @Test
    public void reviewDataFromGSpreadsheet() throws IOException {
         GoogleSheetAPI sheetAPI = new GoogleSheetAPI();
         List<List<Object>> values = sheetAPI.getSpreadSheetRecords("1N6Cj4wogX-FBVnFml5jLyuUbbcvYOr41VQ91q_OGS-E", range);
        for (List<Object> row : values) {
            for (int i=0; i<row.size();i++){
                System.out.format("El valor de la fila %s y columna %s  es: %s \n ", row.get(0).toString(), values.get(0).get(i), row.get(i).toString());
            }
        }
    }

    @DataProvider (name ="dpGSpreadsheet")
    public Object[][] dpMethod() throws IOException {
        GoogleSheetAPI sheetAPI = new GoogleSheetAPI();
        return new Object [][] {sheetAPI.getSpreadSheetRecords("1N6Cj4wogX-FBVnFml5jLyuUbbcvYOr41VQ91q_OGS-E", range).toArray()};
    }


    @Test (dataProvider = "dpGSpreadsheet" )
    public void testDataProviderFromGspreadsheet() throws IOException {
        GoogleSheetAPI sheetAPI = new GoogleSheetAPI();
        List<List<Object>> values = sheetAPI.getSpreadSheetRecords("1N6Cj4wogX-FBVnFml5jLyuUbbcvYOr41VQ91q_OGS-E", range);
        for (List<Object> row : values) {
            for (int i=0; i<row.size();i++){
                System.out.format("El valor de la fila %s y columna %s  es: %s \n ", row.get(0).toString(), values.get(0).get(i), row.get(i).toString());
            }
        }
    }

}
