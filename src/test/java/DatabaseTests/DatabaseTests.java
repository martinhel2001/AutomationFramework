package DatabaseTests;

import BaseTest.BaseTest_API;
import Connectors.SQLConnector;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.Listeners.TestListenerUI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Listeners(TestListenerUI.class)
public class DatabaseTests extends BaseTest_API {
    SQLConnector sql = new SQLConnector();


    @Test (description = "Validate MySQL database connection with query")
    public void dbFreeMySQLConnectionTest() throws SQLException, ClassNotFoundException {
        Statement stmt = sql.openConnSQL(testsConfig.getDBFree_url(),testsConfig.getDBFree_user(),testsConfig.getDBFree_pass());

        //Query to Execute
        String query = "SELECT * FROM employee;";

        // Execute the SQL Query. Store results in ResultSet
        ResultSet rs= stmt.executeQuery(query);

        // While Loop to iterate through all data and print results
        while (rs.next()){
            String employeeName = rs.getString(1);
            String employeeLastName = rs.getString(2);
            System. out.println("Employee name : "+employeeName+"  "+"Employee last name: "+employeeLastName);
        }
        // closing DB Connection
        sql.closeDBconn();
    }

}
