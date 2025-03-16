package org.example.demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;  // For password hashing

public class JavaPostgreSql {

    private static final Logger logger = Logger.getLogger(JavaPostgreSql.class.getName());

    public JavaPostgreSql() {
    }

    // Utility method to load properties from the config file
    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
        }
        return properties;
    }

    // Write user information to the database with hashed password
    public static void writeToDatabase(String userName, String userEmail, String userPassword) {
        try {
            Properties properties = loadProperties();
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            // Hash password before storing
            String hashedPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt());

            String query = "INSERT INTO worker(name, email, password) VALUES(?, ?, ?)";

            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement pst = con.prepareStatement(query)) {

                pst.setString(1, userName);
                pst.setString(2, userEmail);
                pst.setString(3, hashedPassword);
                pst.executeUpdate();

                System.out.println("Successfully created.");
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "Database error: " + ex.getMessage(), ex);
            }

        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Properties file error: " + ex.getMessage(), ex);
        }
    }

    // Check if user credentials exist in the database
    public static void checkToDatabase(String userName, String userEmail, String userPassword) {
        try {
            Properties properties = loadProperties();
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            String query = "SELECT * FROM worker WHERE name = ? AND email = ?";

            try (Connection con = DriverManager.getConnection(url, user, password);
                 PreparedStatement pst = con.prepareStatement(query)) {

                pst.setString(1, userName);  // Set the user name
                pst.setString(2, userEmail); // Set the user email

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        String storedPassword = rs.getString("password");
                        if (BCrypt.checkpw(userPassword, storedPassword)) {
                            System.out.println("Authentication successful.");
                        } else {
                            System.out.println("Invalid password.");
                        }
                    } else {
                        System.out.println("User not found.");
                    }
                }

            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "Database error: " + ex.getMessage(), ex);
            }

        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Properties file error: " + ex.getMessage(), ex);
        }
    }

}
