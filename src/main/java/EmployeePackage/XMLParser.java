package EmployeePackage;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser implements FileParser{
    @Override
    public List<Employee> load(File file) throws IOException, SAXException, ParserConfigurationException {
        String s;
        List<Employee> employeeList = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);


        BufferedReader br = new BufferedReader(new FileReader(file));
        br.readLine();
        while ((s = br.readLine()) != null) {
            String record[] = s.split("");
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

        return employeeList;
    }



}
