package baseMain;

import java.io.*;
import java.util.Properties;

public class UserPropertiesReader {

    private Properties properties;

    public UserPropertiesReader(String username){
        String propertyFilePath= "src/main/resources/users/"+username+".properties";

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
            throw new RuntimeException("users properties not found at " + propertyFilePath);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public String getUsername () {
        return properties.getProperty("username");
    }

    public String getPassword () {
        return properties.getProperty("password");
    }

}
