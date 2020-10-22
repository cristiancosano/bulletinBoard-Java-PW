package es.uco.pw.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLManager {
	private static MySQLManager instance = null;
    private Connection connection = null;
    private String user;
    private String name;
    private String password;
    private String url; 
    private String port;
    private String serverName;

    private MySQLManager(){
        PropsManager pm = new PropsManager("config.properties");

        this.user = pm.getProperty("DATABASE_USER");
        this.name = pm.getProperty("DATABASE_NAME");
        this.password = pm.getProperty("DATABASE_PASSWORD");
        this.port = pm.getProperty("DATABASE_PORT");
        this.serverName = pm.getProperty("DATABASE_SERVER_NAME");
        this.url = "jdbc:mysql://"+this.serverName+":"+this.port+"/"+this.name;

        this.connect();
    }

    public static MySQLManager getInstance() {
        if (instance == null) instance = new MySQLManager();
        return instance;
    }

    public Connection getConnection(){
        return this.connection;
    }

    private void connect(){
        try {
           Class.forName("com.mysql.jdbc.Driver");
           connection = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
