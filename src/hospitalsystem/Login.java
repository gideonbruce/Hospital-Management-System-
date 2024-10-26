/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalsystem;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;


public class Login {
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        if (username.equals("admin") && password.equals("1234")) {
            showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome," + username + "!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or passowrd."); 
        }
    }
    
    @FXML
    private void handleCancel() {
        usernameField.clear();
        passwordField.clear();
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
