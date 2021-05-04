        
package wbapp.domain;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wbapp.dao.AircraftDataDao;
import java.util.ArrayList;

/**
 *
 * @author santeripitkanen
 */
public class Results {
    
    private AircraftDataDao acData;
    private String[][] items;
    private int userId;
    
    public Results(AircraftDataDao acData, int userId) {
        this.acData = acData;
        this.userId = userId;
    }
    
    /**
    * Method returns all of the visible flights that the user has saved.
    * Method returns aircraft register and shortened date, theseare displayed to the user.
    * Full date stamp andaircraft id number are later used to search for specific flight data
    * 
    * @return matrice from all of the results
    *
    */
    public String[][] getResults() {
        int count = resultCount(userId);
        items = new String[count][4]; 
        int i = 0;
        try {
            ResultSet rs = this.acData.getResults(userId);
            while (rs.next()) {
                items[i][0] = rs.getString("register");
                String date = rs.getString("date");
                items[i][1] = date(date);
                items[i][2] = date;
                items[i][3] = String.valueOf(rs.getInt("acid"));
                i++;
            }
        } catch (Exception e) {
            
        }
        return items;
        
    }
    
    /**
    * Method returns count of the rows for specific flight.
    * 
    * @param date full date stamp 
    * @param userId id number of the current user
    * 
    * @return number of rows found
    *
    */
    public int getLogCount(String date, int userId) {
        int count = -1;
        try {
            count = this.acData.getLogCount(date, userId);
        } catch (Exception e) {
            
        }
        return count;
    }
    
    /**
    * Method returns String matrice from all of the values stored for specific flight
    * 
    * @param date full date stamp 
    * @param userId id number of the current user
    * 
    * @return full data matrice
    *
    */
    public String[][] getLogData(String date, int userId) {
        String[][] logData = new String[0][4];
        try {
            int count = getLogCount(date, userId);
            logData = new String[count][4];
            ResultSet rs = this.acData.getLogData(date, userId);
            int i = 0;
            while (rs.next()) {
                logData[i][0] = rs.getString("item");
                logData[i][1] = String.valueOf(rs.getDouble("arm"));
                logData[i][2] = String.valueOf(rs.getDouble("weight"));
                logData[i][3] = String.valueOf(rs.getDouble("moment"));
                i++;
            }
        } catch (Exception e) {
            
        }
        return logData;
    }
    
    /**
    * Method returns number of rows for all found flights that the user has saved
    * 
    * @param userId id number of the current user
    * 
    * @return number of rows found
    *
    */
    public int resultCount(int userId) {
        int count = 0;
        try {
            count = acData.getResultCount(userId);
        } catch (Exception e) {
            
        }
        return count;
    }
    
    /**
    * Method creates shortened date stamp to be displayed on the log listing.
    * Returning value year-month-date
    * 
    * @param date full date stamp 
    * 
    * @return new shortened date stamp
    *
    */
    public String date(String date) {
        String newDate = "";
        for (int i = 0; i < date.length(); i++) {
            if (!String.valueOf(date.charAt(i)).equals("T")) {
                newDate = newDate + String.valueOf(date.charAt(i));
            } else {
                break;
            }
        }
        return newDate;
    }
}
