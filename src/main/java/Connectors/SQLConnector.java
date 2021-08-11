package Connectors;

import  java.sql.Connection;
import  java.sql.Statement;
import  java.sql.ResultSet;
import  java.sql.DriverManager;
import  java.sql.SQLException;

public class  SQLConnector {
    Connection conn;
    public Statement openConnSQL(String dbURL, String dbUser, String dbPass) throws  ClassNotFoundException, SQLException {
        String dbUrl="";
        switch (dbURL){
            case "": {//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
                dbUrl = "jdbc:sqlserver://" + dbURL;
                //Load mysql jdbc driver
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                //Create Connection to DB
                DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
                break;
            }
            case "remotemysql.com:3306/ufTWzK0b6m": {
                dbUrl = "jdbc:mysql://" + dbURL;
                //Load mysql jdbc driver
                //Class.forName("com.mysql.jdbc.Driver");
                //Create Connection to DB
                //DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
                break;
            }
        }

        conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
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