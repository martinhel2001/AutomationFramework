package DataDriven;

//import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class DataProviderSources {
    private String filePath = "src/test/resources/exceldemo.xlsx";
    private String sheetName = "demo";

    private Iterator<Object[]> parseCsvData(String fileName) throws IOException
    {
        BufferedReader input = null;
        File file = new File(fileName);
        input = new BufferedReader(new FileReader(file));
        String line = null;
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        while ((line = input.readLine()) != null)
        {
            String in = line.trim();
            String[] temp = in.split(",");
            List<Object> arrray = new ArrayList<Object>();
            for(String s : temp)
            {
                //arrray.add(Integer.parseInt(s));  USED FOR INTEGER DATA (NEEDS TO CHANGE PARAMETERS TYPE IN DATA PROVIDER METHOD AS WELL)
                arrray.add(s);
            }
            data.add(arrray.toArray());
        }
        input.close();
        return data.iterator();
    }

        /**
         * @param filePath  excel file path
         * @param sheetName  sheet name in xlsx file
         * @return excel data
         * @throws InvalidFormatException
         * @throws IOException
         */
        public Object[][] readExcel(String filePath, String sheetName) throws InvalidFormatException, IOException {
            FileInputStream file= new FileInputStream(filePath);
            XSSFWorkbook wb = new XSSFWorkbook(file);
            XSSFSheet sheet = wb.getSheet(sheetName);
            int rowCount = sheet.getLastRowNum()+1;
            int column = sheet.getRow(0).getLastCellNum();
            Object[][] data = new Object[rowCount][column];
            for (int i = 1; i <= rowCount; i++) {
                XSSFRow row = sheet.getRow(i-1);
                for (int j = 0; j < column; j++) {
                    XSSFCell cell = row.getCell(j);
                    DataFormatter formatter = new DataFormatter();
                    String val = formatter.formatCellValue(cell);
                    data[i - 1][j] = val;
                }
            }

            return data;
        }


    @DataProvider(name="excelData")
    public Object[][] readExcel() throws InvalidFormatException, IOException {
        return readExcel(filePath, sheetName);
    }

    @DataProvider(name = "csvData")
    public Iterator<Object[]> testData() throws IOException
    {
        return parseCsvData("src/test/resources/datadriven.csv");
    }


    @DataProvider(name = "data-provider")
    public Object[][] dpMethod (Method m){
        switch (m.getName()) {
            case "Sum":
                return new Object[][] {{2, 3 , 5}, {5, 7, 9}};
            case "Diff":
                return new Object[][] {{2, 3, -1}, {5, 7, -2}};
        }
        return null;

    }
}
