package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/hospital";
    private static final String username = "root";
    private static final String password = "password";

    public static void main(String[] args) {
//        Loading Drivers
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        // Create Connection
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor(connection);
            while(true){
                System.out.println("Welcome to HOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. Delete Patient");
                System.out.println("4. Add Doctor");
                System.out.println("5. View Doctors");
                System.out.println("6. Delete Doctor");
                System.out.println("7. Book Appointment");
                System.out.println("8. View Appointments");
                System.out.println("9. Cancel Appointment");
                System.out.println("10. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch(choice){
                    case 1 :
                        // Add Patient
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2 :
                        // View Patients
                        patient.viewPatients();
                        System.out.println();
                        break;
                    case 3 :
                        // Delete Patient
                        System.out.print("Enter Patient ID to delete: ");
                        int patientId = scanner.nextInt();
                        patient.deletePatientById(patientId);
                        System.out.println();
                        break;
                    case 4 :
                        // Add Doctor
                        doctor.addDoctor();
                        System.out.println();
                        break;
                    case 5 :
                        // View Doctors
                        doctor.viewDoctors();
                        System.out.println();
                        break;
                    case 6 :
                        // Delete Doctor
                        System.out.print("Enter Doctor ID to delete: ");
                        int doctorId = scanner.nextInt();
                        doctor.deleteDoctorById(doctorId);
                        System.out.println();
                        break;
                    case 7  :
                        // Book Appointment
                        bookAppointment(patient, doctor, connection, scanner);
                        System.out.println();
                        break;
                    case 8 :
                        // View Appointments
                        viewAppointments(connection, doctor, scanner);
                        System.out.println();
                        break;
                    case 9 :
                        cancelAppointment(connection, scanner);
                        System.out.println();
                        break;
                    case 10 :
                        System.out.println("Thank you for using Hospital Management System. Have a nice day!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again!!!");
                }

            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner){
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String appointmentDate = scanner.next();
        if(patient.getPatientById(patientId) && doctor.getDoctorById(doctorId)){
            if(checkDoctorAvailability(doctorId, appointmentDate, connection)){
                String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES (?,?,?)";
                try{
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, patientId);
                    preparedStatement.setInt(2, doctorId);
                    preparedStatement.setString(3, appointmentDate);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if(rowsAffected > 0){
                        System.out.println("Appointment Booked ✅");
                    }
                    else{
                        System.out.println("Failed to Book Appointment");
                    }

                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("Doctor is not available on "+ appointmentDate);
            }
        }
        else{
            System.out.println("Either Doctor or Patient doesn't exist!!");
        }

    }

    public static void viewAppointments(Connection connection, Doctor doctor, Scanner scanner){
        System.out.println("Do you want to view appointments for a specific doctor? (yes/no) ");
        String response = scanner.next();
        if(response.equalsIgnoreCase("yes")){
            System.out.print("Enter Doctor ID to view appointments: ");
            int doctorId = scanner.nextInt();
            String query = "SELECT * FROM appointments WHERE doctor_id = ?";
            try{
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, doctorId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(doctor.getDoctorById(doctorId)){
                    System.out.println("Appointments for Doctor ID: " + doctorId);
                    System.out.println("+----------------+----------------+---------------------+");
                    System.out.println("| Appointment ID | Patient ID     | Appointment Date    |");
                    System.out.println("+----------------+----------------+---------------------+");
                    while(resultSet.next()){
                        int appointmentId = resultSet.getInt("id");
                        int patientId = resultSet.getInt("patient_id");
                        String appointmentDate = resultSet.getString("appointment_date");
                        System.out.printf("| %-14s | %-14s | %-19s |\n", appointmentId, patientId, appointmentDate);
                        System.out.println("+----------------+----------------+---------------------+");
                    }
                }
                else{
                    System.out.println("Doctor with ID " + doctorId + " does not exist.");
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Do you want to view appointments for a specific date? (yes/no)");
            String dateResponse = scanner.next();
            if(dateResponse.equalsIgnoreCase("yes")){
                System.out.print("Enter appointment date (YYYY-MM-DD): ");
                String appointmentDate = scanner.next();
                String query = "SELECT * FROM appointments WHERE appointment_date = ?";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, appointmentDate);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    System.out.println("Appointments on " + appointmentDate + ":");
                    System.out.println("+----------------+----------------+---------------------+");
                    System.out.println("| Appointment ID | Patient ID     | Doctor ID           |");
                    System.out.println("+----------------+----------------+---------------------+");
                    while(resultSet.next()){
                        int appointmentId = resultSet.getInt("id");
                        int patientId = resultSet.getInt("patient_id");
                        int doctorId = resultSet.getInt("doctor_id");
                        System.out.printf("| %-14s | %-14s | %-19s |\n", appointmentId, patientId, doctorId);
                        System.out.println("+----------------+----------------+---------------------+");
                    }
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("Do you want to view all appointments? (yes/no)");
                String allResponse = scanner.next();
                if(allResponse.equalsIgnoreCase("yes")){
                    String query = "SELECT * FROM appointments";
                    try{
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        System.out.println("All Appointments:");
                        System.out.println("+----------------+----------------+---------------------+------------+");
                        System.out.println("| Appointment ID | Patient ID     | Doctor ID           | Date       |");
                        System.out.println("+----------------+----------------+---------------------+------------+");
                        while(resultSet.next()){
                            int appointmentId = resultSet.getInt("id");
                            int patientId = resultSet.getInt("patient_id");
                            int doctorId = resultSet.getInt("doctor_id");
                            String appointmentDate = resultSet.getString("appointment_date");
                            System.out.printf("| %-14s | %-14s | %-19s | %-10s |\n", appointmentId, patientId, doctorId, appointmentDate);
                            System.out.println("+----------------+----------------+---------------------+------------+");
                        }
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                else{
                    System.out.println("Exiting appointment view.");
                }
            }
        }

    }

    public static void cancelAppointment(Connection connection, Scanner scanner){
        System.out.print("Enter the Appointment ID to cancel the Appointment: ");
        int appointmentId = scanner.nextInt();
        String query = "DELETE FROM appointments WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, appointmentId);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Appointment with ID " + appointmentId + " has been cancelled successfully.");
            } else {
                System.out.println("No appointment found with ID " + appointmentId + ".");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection){
        String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date =  ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int count = resultSet.getInt(1);
                if(count == 0) return true;
                else
                    return false;
            }
        } catch(SQLException e){
              e.printStackTrace();
        }
        return false;
    }

}
