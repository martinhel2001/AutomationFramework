package Connectors;

import  java.sql.Connection;
import  java.sql.Statement;
import  java.sql.ResultSet;
import  java.sql.DriverManager;
import  java.sql.SQLException;
public class  SQLConnector {
    Connection conn;
    public Statement openConn(String dbURL, String dbUser, String dbPass) throws  ClassNotFoundException, SQLException {
        //Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
        String dbUrl = "jdbc:sqlserver://" + dbURL;

        //Database Username
        String username = dbUser;

        //Database Password
        String password = dbPass;

        //Load mysql jdbc driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        //Create Connection to DB
        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
        conn = DriverManager.getConnection(dbUrl, username, password);
        if (conn != null) {
            System.out.println("Connected");
        }

        //Create Statement Object
        return conn.createStatement();
    }
    public void closeDBconn() throws SQLException {
        conn.close();
    }
}