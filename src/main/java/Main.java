import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        Operations o = new Operations();
        o.loadCurrentYear();
        o.loadEmployees();
        o.calculateSalary();
        o.sortEmployees();
        o.printEmployeeDetails();
        o.salaryByGender();
        o.salaryByCityAndGender();

    }

}
