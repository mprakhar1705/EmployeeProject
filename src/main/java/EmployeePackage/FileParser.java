package EmployeePackage;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import static java.util.regex.Pattern.matches;

public interface FileParser {

    List<Employee> load(File file) throws IOException, SAXException, ParserConfigurationException;

      default boolean inputValidation(Employee e) {

          Calendar now = Calendar.getInstance();
          int currentYear = now.get(Calendar.YEAR);


          boolean isEmailValid = matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$",e.getEmail());

          return e.getEmpID() > 0 && e.getFirstName() != null && e.getLastName() != null && (e.getGender() == 'M' || e.getGender() == 'F') &&
                  isEmailValid==true && e.getFatherName() != null && (e.getAge() > 18 && e.getAge() < 65) &&
                  (e.getYearOfJoining() > 1900 && e.getYearOfJoining() < currentYear) && e.getSalary() > 0.0 && e.getHikePercentage() >= 0.0
                  && e.getCity() != null;
      }
}
