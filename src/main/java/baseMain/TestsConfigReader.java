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

    public String getDBKapsch_url() {return properties.getProperty("DB_url"); }
    public String getDBKapsch_user() {
        return properties.getProperty("DB_user");
    }
    public String getDBKapsch_pass() {
        return properties.getProperty("DB_pass");
    }

    public String getDBFree_url() {return properties.getProperty("DBfree_url"); }
    public String getDBFree_user() {
        return properties.getProperty("DBfree_user");
    }
    public String getDBFree_pass() {
        return properties.getProperty("DBfree_pass");
    }

    public String getMantis_url() {
        return properties.getProperty("mantis_URL");
    }
    public String getMantis_host() {
        return properties.getProperty("mantis_Host");
    }
    public String getMantis_token() {
        return properties.getProperty("mantis_Token");
    }

    public String getFTP_host() {
        return properties.getProperty("ftp_Host");
    }
    public String getFTP_user() {
        return properties.getProperty("ftp_User");
    }
    public String getFTP_pass() {
        return properties.getProperty("ftp_Pass");
    }
    public String getFTP_url() {
        return properties.getProperty("ftp_URL");
    }

    public String getSlack_token() {
        return properties.getProperty("slack_Token");
    }
    public String getSlack_channel() {return properties.getProperty("slack_Channel");}
}
