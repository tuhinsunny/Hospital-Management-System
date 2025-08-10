# 🏥 Hospital Management System

> A **Java + MySQL** based console application for managing hospital operations, built with clean code practices and a scalable structure.
> Designed to simulate real-world hospital management tasks like handling patients, doctors, and appointments — all from a terminal interface.

---

## 🚀 Features

### 👨‍⚕️ Patient Management

* Add new patients with basic details
* View all registered patients
* Delete patient records

### 🩺 Doctor Management

* Add new doctors with specialization
* View doctor listings
* Delete doctor records

### 📅 Appointment Handling

* Book patient appointments with doctors
* View all scheduled appointments
* Cancel existing appointments

### 🖥️ User Interface

* Interactive console menu
* Clear prompts for input and validation
* Static sample data available for quick demos

---

## 🛠️ Tech Stack

| Technology | Purpose                 |
| ---------- | ----------------------- |
| **Java**   | Core application logic  |
| **JDBC**   | Database connectivity   |
| **MySQL**  | Persistent data storage |
| **SQL**    | CRUD operations         |
| **Git**    | Version control         |

---

## 📂 Project Structure


```

Hospital Management System/
├── .idea/                                # IDE-specific settings (e.g., IntelliJ IDEA)
├── sql/                                  # Database-related files, like schema scripts
├── src/                                  # Source code directory
│   └── HospitalManagementSystem/         # Main Java package
│       ├── Doctor.java                   # Represents the Doctor entity; likely contains attributes and methods for doctors
│       ├── HospitalManagementSystem.java # The main entry point of the application
│       └── Patient.java                  # Represents the Patient entity; contains attributes and methods for patients
├── .gitignore                            # Specifies files and directories to be ignored by Git
└── Hospital Management System.iml        # IntelliJ IDEA module file; defines project structure and dependencies

```

---

## How to Run the Hospital Management System Project ?

### Prerequisites
- Java Development Kit (JDK 8 or higher)
- MySQL or compatible database server

### Setup Steps

1. **Clone or Download the Project**
   - Copy the entire project folder to your machine.
   - git clone https://github.com/tuhinsunny/Hospital-Management-System.git


2. **Set Up the Database**
   - Install MySQL.
   - Open `sql/hospital_setup.sql` in a MySQL client or command line.
   - Execute the script to create the database and tables.

3. **Configure Database Connection**
    - Update database credentials in the Java source code (HospitalManagementSystem.java) if needed (url, username, password).
    ```java
    private static final String url = "jdbc:mysql://localhost:3306/hospital_db";
    private static final String username = "root";
    private static final String password = "yourpassword";
    ```
4. **Compile the Java Source Files**
   - Open a terminal and navigate to the `src` directory.
   - Run:
     ```
     javac HospitalManagementSystem/*.java
     ```

5. **Run the Application**
   - In the same terminal, run:
     ```
     java HospitalManagementSystem.HospitalManagementSystem
     ```

6. **(Optional) Use an IDE**
   - Import the project into IntelliJ IDEA, Eclipse, or NetBeans.
   - Set the main class to `HospitalManagementSystem.HospitalManagementSystem`.

### Notes
- Ensure any required external libraries (JARs) are included in the classpath.
- Update connection details if your database setup differs.







---

## 📸 Screenshots

### Main Menu
<p align="center">
  <img src="Main%20Menu.png" alt="Main Menu Screenshot" width="500"/>
</p>

### Database Schema
<p align="center">
  <img src="Database_Schema.png" alt="Db Schema Screenshot" width="500"/>
</p>

---

## 🎯 Why This Project Stands Out

- ✅ **Clean, modular Java code** — easy to extend with new features
- ✅ **SQL-backed persistent storage** — realistic data handling
- ✅ **Static sample data** — instantly test without manual entry
- ✅ **Practical real-world problem solving** — applicable to healthcare scenarios
- ✅ **Git best practices** — meaningful commits and proper structure



---

## 🔮 Possible Future Enhancements

* GUI version using **JavaFX** or **Swing**
* User authentication and role-based access (Admin, Receptionist, Doctor)
* Data analytics for patient visits and doctor performance
* Cloud database integration for remote access


---

## 🤝 Connect with me

💼 [LinkedIn](https://www.linkedin.com/in/tuhin-chandra-a675ab250/) <br/>
📧 [Gmail](mailto:tuhinchandra2k04@gmail.com)

---

