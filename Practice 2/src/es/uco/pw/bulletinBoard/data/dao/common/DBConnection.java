package es.uco.pw.bulletinBoard.data.dao.common;

import java.sql.Connection;
import java.sql.DriverManager;

import es.uco.pw.bulletinBoard.business.common.PropsManager;

public class DBConnection {
    private static Connection connection = null;
    private static String user;
    private static String name;
    private static String password;
    private static String url; 
    private static String port;
    private static String serverName;

    public static Connection getConnection(){
    	if(connection == null) connect();
        return connection;
    }
    
    private static void loadParams() {
        PropsManager pm = new PropsManager("config.properties");

    	user = pm.getProperty("DATABASE_USER");
        name = pm.getProperty("DATABASE_NAME");
        password = pm.getProperty("DATABASE_PASSWORD");
        port = pm.getProperty("DATABASE_PORT");
        serverName = pm.getProperty("DATABASE_SERVER_NAME");
        url = "jdbc:mysql://"+serverName+":"+port+"/"+name;
    }

    private static void connect(){
    	
    	loadParams();
    	
        try {
           Class.forName("com.mysql.jdbc.Driver");
           connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
