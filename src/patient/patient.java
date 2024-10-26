/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patient;

/**
 *
 * @author Bruce
 */
public class patient {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String address;
    private String phone;
    private String medicalHistory;

    // Constructor
    public patient(int id, String firstName, String lastName, int age, String gender, String address, String phone, String medicalHistory) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.medicalHistory = medicalHistory;
    }

    // Getters and setters
    // (Include all necessary getters and setters for each field)
}
