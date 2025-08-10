show databases;

CREATE DATABASE IF NOT EXISTS hospital;
USE hospital;

CREATE TABLE patients (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL
);

CREATE TABLE doctors (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    specialization VARCHAR(255) NOT NULL
);

CREATE TABLE appointments (
	id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);

SHOW tables;

INSERT INTO doctors(name, specialization) VALUES ("Rahul Kumar", "Physician");
INSERT INTO doctors(name, specialization) VALUES ("Shekhar Das", "NeuroSurgeon");
INSERT INTO doctors (name, specialization) VALUES
("Anita Sharma", "Cardiologist"),
("Rajesh Gupta", "Orthopedic Surgeon"),
("Meera Nair", "Pediatrician"),
("Vikram Singh", "Dermatologist"),
("Sanjay Rao", "Oncologist"),
("Pooja Verma", "Endocrinologist"),
("Kunal Malhotra", "Gastroenterologist"),
("Rekha Iyer", "Ophthalmologist"),
("Amit Khanna", "Psychiatrist");

SELECT * FROM patients;

SELECT * FROM doctors;
select * from appointments;

-- These 2 statements are for delete the data from patient and reseting the auto_increment so that id counts from 1
-- delete from Patients;  
-- ALTER TABLE patients AUTO_INCREMENT = 1;

-- delete from doctors;  
-- ALTER TABLE doctors AUTO_INCREMENT = 1;

-- Adding the on delete cascade to the foreign keys so that deleting data does not cause problem :

ALTER TABLE appointments
DROP FOREIGN KEY appointments_ibfk_1;

ALTER TABLE appointments
DROP FOREIGN KEY appointments_ibfk_2;

Describe appointments;
ALTER TABLE appointments
ADD CONSTRAINT fk_patient
FOREIGN KEY (patient_id) REFERENCES patients(id)
ON DELETE CASCADE;

ALTER TABLE appointments
ADD CONSTRAINT fk_doctor
FOREIGN KEY (doctor_id) REFERENCES doctors(id)
ON DELETE CASCADE;
