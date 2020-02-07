import au.com.bytecode.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    try {
      Reader reader = Files.newBufferedReader(Paths.get("file.csv"));
      CSVReader csvReader = new CSVReader(reader);

      List<String[]> records = csvReader.readAll();
      for (String[] record : records) {
        for(int i = 0; i < record.length; i++){
          System.out.println(record[i]);
        }
        System.out.println();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
