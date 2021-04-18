
package wbapp.domain;

import java.util.ArrayList;
import wbapp.dao.AcDataDao;
import wbapp.domain.Aircraft;
import java.sql.*;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wbapp.dao.AcDataDao;
/**
 *
 * @author santeripitkanen
 */
public class AircraftList {
    private AcDataDao acData;
    
    public AircraftList(AcDataDao acData) {
        this.acData = acData;
    }
    
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
