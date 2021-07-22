package baseMain;

import java.io.*;
import java.util.Properties;

public class TestsConfigReader {

    private Properties properties;

    public TestsConfigReader(){
        String propertyFilePath= "src/test/resources/tests.config";

        try {
            File fileDir = new File(propertyFilePath);

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        catch (UnsupportedEncodingException e)
        {
            System.out.println(e.getMessage());
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("tests.config not found at " + propertyFilePath);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public String getBrowsers() {
        return properties.getProperty("browsers");
    }

    public String getOBOurl() {
        return properties.getProperty("OBO_url");
    }

    public String getCommerceUrl() {
        return properties.getProperty("ecommerce_url");
    }

    public String getDBurl () {
        return properties.getProperty("DB_url");
    }

    public String getDBuser () {
        return properties.getProperty("DB_user");
    }

    public String getDBpass () {
        return properties.getProperty("DB_pass");
    }
}
