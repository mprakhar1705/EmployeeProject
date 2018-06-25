import EmployeePackage.Employee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.util.regex.Pattern.matches;


public class Operations {

    private int currentYear;
    private String s;
    private List<Employee> employeeList = new ArrayList<>();

    public void loadEmployees() throws IOException {
        ClassLoader classLoader = Operations.class.getClassLoader();
        File file = new File(classLoader.getResource("Sample_input1.csv").getFile());

        BufferedReader br = new BufferedReader(new FileReader(file));
        br.readLine();
        while ((s = br.readLine()) != null) {
            String record[] = s.split(",");
            int l = record[9].length();
            Employee emp = new Employee.EmployeeBuilder()
                    .empID(Integer.parseInt(record[0]))
                    .firstName(record[1])
                    .lastName(record[2])
                    .gender(record[3].charAt(0))
                    .email(record[4])
                    .fatherName(record[5])
                    .age(Integer.parseInt(record[6]))
                    .yearOfJoining(Integer.parseInt(record[7]))
                    .salary(Float.parseFloat(record[8]))
                    .hikePercentage(Float.parseFloat(record[9].substring(0, l - 1)))
                    .city(record[10])
                    .build();


            boolean isInputValid = inputValidation(emp);
            if (isInputValid)
                employeeList.add(emp);

        }
    }

    public void loadCurrentYear() {
        Calendar now = Calendar.getInstance();
        currentYear = now.get(Calendar.YEAR);
    }

    public void printEmployeeDetails() {

        String format = "%5s |%20s |%20s |%10s |%35s |%20s |%10s |%15s |%10s |%10s |%15s |%20s\n";
        System.out.format(format, "EMP ID", "FIRST NAME", "LAST NAME", "GENDER",
                "EMAIL", "FATHER\'S NAME", "AGE", "YEAR OF JOINING", "SALARY", "HIKE %", "FINAL SALARY", "CITY");
        for (Employee e : employeeList) {
            System.out.format(format, e.getEmpID(), e.getFirstName(), e.getLastName(),
                    e.getGender(), e.getEmail(), e.getFatherName(), e.getAge(),
                    e.getYearOfJoining(), e.getSalary(), e.getHikePercentage(), e.getFinalSalary(), e.getCity());


        }
    }

    public int calculateBonus(Employee e) {


        int experience = currentYear - e.getYearOfJoining();

        if (experience <= 3)
            return (10000);
        else if (experience <= 5)
            return (25000);
        else if (experience <= 10)
            return (50000);
        else {
            return (75000);
        }

    }


    public void calculateSalary() {
        for (Employee e : employeeList) {
            int bonus = calculateBonus(e);

            float salary = e.getSalary() * (1 + e.getHikePercentage() / 100) + bonus;
            e.setFinalSalary(salary);
        }
    }

    public void sortEmployees() {
        Collections.sort(employeeList, (o1, o2) -> {
            if (o1.getFinalSalary() < o2.getFinalSalary())
                return 1;
            else return -1;
        });
    }

    public void salaryByGender() {
        float maleTotalSalary = 0, femaleTotalSalary = 0;
        for (Employee e : employeeList) {
            if (e.getGender() == 'M')
                maleTotalSalary += e.getSalary();
            else
                femaleTotalSalary += e.getSalary();
        }
        System.out.println("\n\nThe Salary Division is as follows:\nMALE : " + maleTotalSalary + "\nFEMALE : " + femaleTotalSalary);

    }

    public void salaryByCityAndGender() {

        float totalSalary;
        int maleCount, femaleCount;
        Map<String, List<Employee>> map = new HashMap<>();

        for (Employee e : employeeList) {

            if (map.get(e.getCity()) == null) {
                map.put(e.getCity(), new ArrayList<>());
            }
            map.get(e.getCity()).add(e);

        }

        Set<String> keys = map.keySet();

        for (String key : keys) {
            System.out.println("CITY : " + key);
            totalSalary = 0;
            maleCount = 0;
            femaleCount = 0;

            List<Employee> empList = map.get(key);

            for (Employee e : empList) {
                totalSalary += e.getFinalSalary();
                if (e.getGender() == 'M')
                    maleCount++;
                else
                    femaleCount++;
            }
            System.out.println("Total salary: " + totalSalary + " No. of Males: " + maleCount + " No. of Females: " + femaleCount + "\n");
            System.out.println("---------------------------------------------------------");
        }

    }

    private boolean emailValidation(String email) {

        return matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", email);
    }

    private boolean inputValidation(Employee e) {

        //  System.out.println(currentYear);
        return e.getEmpID() > 0 && e.getFirstName() != null && e.getLastName() != null && (e.getGender() == 'M' || e.getGender() == 'F') &&
                emailValidation(e.getEmail()) && e.getFatherName() != null && (e.getAge() > 18 && e.getAge() < 65) &&
                (e.getYearOfJoining() > 1900 && e.getYearOfJoining() < currentYear) && e.getSalary() > 0.0 && e.getHikePercentage() >= 0.0
                && e.getCity() != null;
    }


}

