/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patient;

/**
 *
 * @author Bruce
 */

import com.example.model.Patient;
import com.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDao {
     public boolean addPatient(Patient patient) {
        String query = "INSERT INTO patients (first_name, last_name, age, gender, address, phone, medical_history) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setInt(3, patient.getAge());
            statement.setString(4, patient.getGender());
            statement.setString(5, patient.getAddress());
            statement.setString(6, patient.getPhone());
            statement.setString(7, patient.getMedicalHistory());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Patient patient = new Patient(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("gender"),
                        resultSet.getString("address"),
                        resultSet.getString("phone"),
                        resultSet.getString("medical_history")
                );
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    public Patient searchPatientById(int id) {
        String query = "SELECT * FROM patients WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Patient(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("gender"),
                        resultSet.getString("address"),
                        resultSet.getString("phone"),
                        resultSet.getString("medical_history")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePatient(Patient patient) {
        String query = "UPDATE patients SET first_name = ?, last_name = ?, age = ?, gender = ?, address = ?, phone = ?, medical_history = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setInt(3, patient.getAge());
            statement.setString(4, patient.getGender());
            statement.setString(5, patient.getAddress());
            statement.setString(6, patient.getPhone());
            statement.setString(7, patient.getMedicalHistory());
            statement.setInt(8, patient.getId());

            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
