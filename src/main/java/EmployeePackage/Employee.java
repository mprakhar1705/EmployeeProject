package EmployeePackage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    private int empID;
    private String firstName;
    private String lastName;
    private char gender;
    private String email;
    private String fatherName;
    private int age;
    private int yearOfJoining;
    private float salary;
    private float hikePercentage;
    private float finalSalary;
    private String city;

    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    public static class EmployeeBuilder {
        private int empID;
        private String firstName;
        private String lastName;
        private char gender;
        private String email;
        private String fatherName;
        private int age;
        private int yearOfJoining;
        private float salary;
        private float hikePercentage;
        private float finalSalary;
        private String city;

        public EmployeeBuilder() {
        }

        public EmployeeBuilder empID(int empID) {
            this.empID = empID;
            return this;
        }

        public EmployeeBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public EmployeeBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public EmployeeBuilder gender(char gender) {
            this.gender = gender;
            return this;
        }

        public EmployeeBuilder email(String email) {
            this.email = email;
            return this;
        }

        public EmployeeBuilder fatherName(String fatherName) {
            this.fatherName = fatherName;
            return this;
        }

        public EmployeeBuilder age(int age) {
            this.age = age;
            return this;
        }

        public EmployeeBuilder yearOfJoining(int yearOfJoining) {
            this.yearOfJoining = yearOfJoining;
            return this;
        }

        public EmployeeBuilder salary(float salary) {
            this.salary = salary;
            return this;
        }

        public EmployeeBuilder hikePercentage(float hikePercentage) {
            this.hikePercentage = hikePercentage;
            return this;
        }

        public EmployeeBuilder finalSalary(float finalSalary) {
            this.finalSalary = finalSalary;
            return this;
        }

        public EmployeeBuilder city(String city) {
            this.city = city;
            return this;
        }

        public Employee build() {
            return new Employee(empID, firstName, lastName, gender, email, fatherName, age, yearOfJoining, salary, hikePercentage, finalSalary, city);
        }

        public String toString() {
            return "Employee.EmployeeBuilder(empID=" + this.empID + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", gender=" + this.gender + ", email=" + this.email + ", fatherName=" + this.fatherName + ", age=" + this.age + ", yearOfJoining=" + this.yearOfJoining + ", salary=" + this.salary + ", hikePercentage=" + this.hikePercentage + ", finalSalary=" + this.finalSalary + ", city=" + this.city + ")";
        }
    }
}
