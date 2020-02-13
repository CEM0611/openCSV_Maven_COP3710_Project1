import au.com.bytecode.opencsv.CSVReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class Main {
  public static void main(String[] args) {
    Connection conn = null;
    try {
      // database properties
      final String jdbcDriver = "org.h2.Driver";
      final String dbUrl = "jdbc:h2:./res/H2";
      try {
        // connect to database
        Class.forName(jdbcDriver);
        conn = DriverManager.getConnection(dbUrl);
      } catch (Exception e) {
        e.printStackTrace();
      }

      Reader reader = Files.newBufferedReader(Paths.get("file.csv"));
      CSVReader csvReader = new CSVReader(reader);

      List<String[]> records = csvReader.readAll();
      for (String[] record : records) {
        for (int i = 0; i < record.length; i++) {
          System.out.println(record[i]);
        }
        System.out.println();
        for (int i = 0; i < record.length; i++) {
          PreparedStatement ps = conn.prepareStatement(
              "INSERT INTO AUTHOR VALUES ('"
                  + record[i]
                  + "', '"
                  + record[i+1]
                  + "', '"
                  + record[i+2]
                  + "')");
          ps.executeUpdate();
          i = record.length;
        }
      }
    } catch (IOException | SQLException e) {
      e.printStackTrace();
    }
  }
}
