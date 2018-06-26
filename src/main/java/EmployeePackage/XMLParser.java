package EmployeePackage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
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
        doc.getDocumentElement().normalize();
        NodeList  nList = doc.getElementsByTagName("emp");

        for(int i=0;i<nList.getLength();i++){
            Node nNode = nList.item(i);

            if(nNode.getNodeType()==Node.ELEMENT_NODE){
                Element element = (Element) nNode;
                int l = element.getElementsByTagName("hikePercentage").item(0).getTextContent().length();
                Employee emp = new Employee.EmployeeBuilder()
               .empID(Integer.parseInt(element.getElementsByTagName("empId").item(0).getTextContent()))
               .firstName(element.getElementsByTagName("firstName").item(0).getTextContent())
               .lastName(element.getElementsByTagName("lastName").item(0).getTextContent())
               .gender(element.getElementsByTagName("gender").item(0).getTextContent().charAt(0))
               .email(element.getElementsByTagName("email").item(0).getTextContent())
               .fatherName(element.getElementsByTagName("fatherName").item(0).getTextContent())
               .age(Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent()))
               .yearOfJoining(Integer.parseInt(element.getElementsByTagName("yearOfJoining").item(0).getTextContent()))
               .salary(Float.parseFloat(element.getElementsByTagName("salary").item(0).getTextContent()))
               .hikePercentage(Float.parseFloat(element.getElementsByTagName("hikePercentage").item(0).getTextContent().substring(0,l-1)))
               .city(element.getElementsByTagName("city").item(0).getTextContent())
               .build();

                boolean isInputValid = inputValidation(emp);
                if (isInputValid)
                    employeeList.add(emp);

            }
        }
        return employeeList;
    }
}
