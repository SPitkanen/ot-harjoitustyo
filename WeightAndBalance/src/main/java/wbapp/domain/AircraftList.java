
package wbapp.domain;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wbapp.dao.AircraftDataDao;
/**
 *
 * @author santeripitkanen
 */
public class AircraftList {
    private AircraftDataDao acData;
    
    public AircraftList(AircraftDataDao acData) {
        this.acData = acData;
    }
    
    /**
    * Method creates list from all aircraft stored in database.
    *
    */
    public ObservableList<Aircraft> getAcNameList() {
        ObservableList<Aircraft> planes = FXCollections.observableArrayList();
        int i = 0;
        try {
            ResultSet rs = this.acData.getAircrafts();
            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String register = rs.getString("register");
                Aircraft plane = new Aircraft(id, type, register);
                planes.add(plane);
            }
        } catch (SQLException e) {
            
        }
        return planes;
    }
}
