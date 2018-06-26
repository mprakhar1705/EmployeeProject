import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException, ParserConfigurationException, SAXException {
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
