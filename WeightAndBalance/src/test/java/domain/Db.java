
package domain;

import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.nio.file.Paths;

/**
 *
 * @author santeripitkanen
 */
public class Db {
    
    private Connection conn;
    
    public Db () {
        
    }
    
    public Connection createConnection() {
        try {
            String appPath = new File("").getAbsolutePath();
            String dbPath = appPath + "/config/testDb.txt";
            String db = readFile(dbPath).get(0);
            conn = DriverManager.getConnection("jdbc:postgresql:" + db);
               
            checkTablesExist(appPath);
            if (referencePlaneExists(appPath) == false) {
                readReferenceData(appPath);
            }
        } catch (SQLException e) {
            System.out.println("Virhe tietokannassa");
        }
        return conn;
    }
    
    public void checkTablesExist(String appPath) {
        try {
            String schemaPath = appPath + "/config/testSchema.txt";
            ArrayList<String> schema = readFile(schemaPath);
            for (int i = 0; i < schema.size(); i++) {
                String query = schema.get(i);
                PreparedStatement s = conn.prepareStatement(query);
                s.execute();
            }
        } catch (SQLException e) {
        }
    }
    
    public void readReferenceData(String appPath) {
        ArrayList<String> values = readFile(appPath + "/config/testValues.txt");
        try {
           for (int i = 1; i < values.size(); i++) {
                String query = values.get(i);
                System.out.println(query);
                PreparedStatement s = conn.prepareStatement(query);
                s.execute();
            }
        } catch (SQLException e) {
        }
    }
    
    public boolean referencePlaneExists(String appPath) {
        String valuesPath = appPath + "/config/testValues.txt";
        String register = readFile(valuesPath).get(0);
        String query = "SELECT COALESCE((SELECT COUNT(id) FROM aircraft WHERE register=?), 0) AS count";
        int id = 0;
        try {
            PreparedStatement s = conn.prepareStatement(query);
            s.setString(1, register);
            ResultSet rs = s.executeQuery();
            rs.next();
            id = rs.getInt("count");
        } catch (SQLException e) {
        }
        if (id > 0) {
            return true;
        }
        return false;
    }
    
    public ArrayList<String> readFile(String fileName) {
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(Paths.get(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                list.add(line);
            }
        } catch (Exception e) {
        }
        return list;
    }
}
