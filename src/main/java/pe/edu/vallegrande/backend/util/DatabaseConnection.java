package pe.edu.vallegrande.backend.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    
    private static final String PROPERTIES_FILE = "reports/database.properties";
    
    public static Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(PROPERTIES_FILE)) {
            props.load(fis);
        }
        
        String driver = props.getProperty("jdbc.driver");
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver de base de datos no encontrado", e);
        }
        
        return DriverManager.getConnection(url, username, password);
    }
}