import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class SaxParser {
    public static void main(String[] args) throws XMLStreamException, FileNotFoundException{
        List<Student> list = SaxParser.readXML();
        for (Student o: list){
            System.out.println(o.toString()); 
    }
    public static List<Student> readXML () throws FileNotFoundException, XMLStreamException{
    List<Student> list = new ArrayList<>();
    Student student = null;
    String content = null;
    File input = new File("D:\BT Java\input.xml");
    InputStream out = new FileInputStream(input);
    XMLInputFactory factory = new XMLInputFactory.newInstance();
    XMLStreamReader reader = factory.createXMLEventReader(out);
    while (reader.hasNext()){
        int i = reader.next();
        switch (i){
            case XMLStreamConstants.START_ELEMENT:
                if ("student".equals(reader.getLocalName())){
                    student = new Student();
                    student.getId(reader.getAttributeValue(0));
                    }
                    break;
            case XMLStreamConstants.CHARACTERS:
                content = reader.getText().trim();
                break;
                case XMLStreamConstants.END_ELEMENT:
                    switch (reader.getLocalName()) {
                        case "student":
                            list.add(student);
                            break;
                        case "fistname":
                            student.getFistname(content);
                            break;
                        case "lastname":
                            student.getLastname(content);
                            break;
                        case "mark":
                            student.getMark(content);
                            break;
                        default:
                            break;
                    }
                }
        }
        return list;
    }   
}
    

