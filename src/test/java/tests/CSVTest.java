package tests;

import utility.CSVReaderUtil;
import java.util.List;
import java.util.Map;

public class CSVTest {

    public static void main(String[] args) {
        String filePath = "src/test/resources/TestData/TestDataCSV.csv";
        List<Map<String, String>> users = CSVReaderUtil.readCSV(filePath);

        for (Map<String, String> user : users) {
            System.out.println("ID: " + user.get("id"));
            System.out.println("Name: " + user.get("name"));
            System.out.println("Gender: " + user.get("gender"));
            System.out.println("Language: " + user.get("language"));
            System.out.println("----------------------");
        }
    }
}
