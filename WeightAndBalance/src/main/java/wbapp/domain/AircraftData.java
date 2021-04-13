
package wbapp.domain;

import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author santeripitkanen
 */
public class AircraftData {
    
    private double[][] dataList;
    private double[][] dataList2;
    private double[][] dataList3;
    private ResultSet rs;
    private ResultSet rs2;
    private int count;
    private int count2;
    private ArrayList<String> items;
    private ArrayList<String> itemsAcDependant;
    private ArrayList<String> fullList;
    
    public AircraftData(int count, int count2, ResultSet rs, ResultSet rs2) {
        this.rs = rs;
        this.rs2 = rs2;
        this.count = count;
        this.count2 = count2;
        this.items = new ArrayList<>();
        this.itemsAcDependant = new ArrayList<>();
        this.fullList = new ArrayList<>();
        createDefaultDataList();
        createDataList();
        createDataListDepend();
    }

    public void createDataList() {
        int i = 0;
        try {
            while (this.rs.next()) {
                this.items.add(rs.getString("item"));
                dataList[i][0] = rs.getDouble("arm");
                dataList[i][1] = rs.getDouble("weight");
                dataList[i][2] = rs.getDouble("moment");
                dataList[i][3] = rs.getDouble("maxweight");
                dataList[i][4] = 0;
                i++;
            }
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
        }        
    }
    
    public void createDataListDepend() {
        int i = 0;
        try {
            while (this.rs2.next()) {
                this.itemsAcDependant.add(rs2.getString("item"));
                dataList2[i][0] = rs2.getDouble("arm");
                dataList2[i][1] = rs2.getDouble("weight");
                dataList2[i][2] = rs2.getDouble("moment");
                dataList2[i][3] = rs2.getDouble("maxweight");
                dataList2[i][4] = rs2.getDouble("section");
                i++;
            }
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
        }
    }
    
    public void createFullItemList() {
        int p = 1;
        int k = 0;
        for (int i = 0; i < count; i++) {
            this.fullList.add(this.items.get(i));
            for (int l = 0; l < count2; l++) {
                if (this.dataList2[l][4] == p) {
                    this.fullList.add(this.itemsAcDependant.get(k));
                    k++;
                }
            }
            p++;
        }
    }
    
    public double[][] getDataList() {
        return dataList;
    }
    
    public double[][] getDataListDepend() {
        return dataList2;
    }
    
    public double[][] getFullDataList() {
        return dataList3;
    }
    
    public ArrayList<String> getDataItems() {
        return this.items;
    }
    
    public ArrayList<String> getDataItemsDepend() {
        return this.itemsAcDependant;
    }
    
    public ArrayList<String> getFullItemList() {
        return this.fullList;
    }
    
    private void createDefaultDataList() {
        int cols = 5;
        int rows = this.count;
        dataList = new double[rows][cols];
        int cols2 = 5;
        int rows2 = this.count2;
        dataList2 = new double[rows2][cols2];
        int cols3 = 5;
        int rows3 = count + count2;
        dataList3 = new double[rows3][cols3];
    }
    
                
}
