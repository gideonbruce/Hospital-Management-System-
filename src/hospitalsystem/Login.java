/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalsystem;



import com.example.util.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Login {
     @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> roleComboBox;

    public Login() {
        // Initialize roles for ComboBox in the constructor
        roleComboBox = new ComboBox<>(FXCollections.observableArrayList("doctor", "patient"));
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = roleComboBox.getValue();

        if (validateLogin(username, password, role)) {
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome, " + username + "!");
            // Proceed to the relevant dashboard or main application
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username, password, or role.");
        }
    }

    @FXML
    private void handleSignup() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = roleComboBox.getValue();

        if (createUser(username, password, role)) {
            showAlert(Alert.AlertType.INFORMATION, "Signup Successful", "Account created successfully! Please login.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Signup Failed", "Username is already taken.");
        }
    }

    private boolean validateLogin(String username, String password, String role) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ? AND role = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, role);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();  // Return true if a match is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean createUser(String username, String password, String role) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String checkQuery = "SELECT * FROM users WHERE username = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, username);
            ResultSet resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                return false;  // Username already exists
            }

            String insertQuery = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, username);
            insertStatement.setString(2, password);
            insertStatement.setString(3, role);
            insertStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
