
package wbapp.domain;

import java.sql.*;
import java.util.ArrayList;
import wbapp.dao.AcDataDao;
/**
 *
 * @author santeripitkanen
 */
public class AircraftData {
    
    private AcDataDao acData;
    private double[][] dataList;
    private double[][] dataList2;
    private double[][] dataList3;
    private ResultSet rs;
    private ResultSet rs2;
    private int count;
    private int count2;
    private int[] index;
    private ArrayList<String> items;
    private ArrayList<String> itemsAcDependant;
    private ArrayList<String> fullList;
    private int coordinateCount;
    
    public AircraftData(AcDataDao acData, int planeId) {
        this.acData = acData;
        try {
            this.rs = this.acData.getAcData(planeId);
            this.rs2 = this.acData.getAcDataDepend(planeId);
            this.count = this.acData.getCount(planeId);
            this.count2 = this.acData.getCountDepend(planeId);
        } catch (SQLException e) {
            System.out.println("Virhe: " + e);
        }
        this.items = new ArrayList<>();
        this.itemsAcDependant = new ArrayList<>();
        this.fullList = new ArrayList<>();
        this.index = new int[count + count2];
        createDefaultDataList();
        createDataList();
        createDataListDepend();
        createFullDataList();
        createFullItemList();
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
                dataList2[i][5] = rs2.getDouble("operation");
                i++;
            }
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
        }
    }
    public void createFullDataList() {
        dataList3[0][0] = dataList[0][0];
        dataList3[0][1] = dataList[0][1];
        dataList3[0][2] = dataList[0][2];
        dataList3[0][3] = dataList[0][3];
        dataList3[0][4] = dataList[0][4];
        dataList3[0][5] = -1;
        int id = 1;
        int section = 1;
        int index = 0;
        int k = 0;
        for (int i = 1; i < count; i++) {
            for (int l = 0; l < count2; l++) {
                if (dataList2[l][4] == section) {
                    dataList3[id][0] = dataList2[k][0];
                    dataList3[id][1] = dataList2[k][1];
                    dataList3[id][2] = dataList2[k][2];
                    dataList3[id][3] = dataList2[k][3];
                    dataList3[id][4] = dataList2[k][4];
                    dataList3[id][5] = dataList2[k][5];
                    this.index[index] = id;
                    k++;
                    index++;
                    id++;
                }
            }
            dataList3[id][0] = dataList[i][0];
            dataList3[id][1] = dataList[i][1];
            dataList3[id][2] = dataList[i][2];
            dataList3[id][3] = dataList[i][3];
            dataList3[id][4] = dataList[i][4];
            dataList3[id][5] = 0;
            id++;
            section++;
        }
    }
    
    public void addWeights(double weight, int i) {
        dataList3[i][1] = weight;
    }
    
    public boolean checkWeight(double weight, int i) {
        if (weight < dataList3[i][3] && weight >= 0) {
            return true;
        }
        return false;
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
    
    public double[][] getChartData(int planeId) throws SQLException {
        ResultSet rs = acData.getChart(planeId);
        double[][] chartData = new double[1][6];
        while (rs.next()) {
            chartData[0][0] = rs.getDouble("xstart");
            chartData[0][1] = rs.getDouble("xstop");
            chartData[0][2] = rs.getDouble("ystart");
            chartData[0][3] = rs.getDouble("ystop");
            chartData[0][4] = rs.getDouble("xspacing");
            chartData[0][5] = rs.getDouble("yspacing");
        }
        return chartData;
    }
    
    public double[][] getEnvelopeData(int planeId) throws SQLException {
        ResultSet rs = acData.getEnvelope(planeId);
        ResultSet rs2 = acData.getEnvelopeCount(planeId);
        while (rs2.next()) {
            coordinateCount = rs2.getInt("count");
        }
        int i = 0;
        double[][] envelopeData = new double[coordinateCount][2];
        while (rs.next()) {
            envelopeData[i][0] = rs.getDouble("x");
            envelopeData[i][1] = rs.getDouble("y");
            i++;
        }
        return envelopeData;
    }
    
    public int getCoordinateCount(int planeId) throws SQLException {
        return coordinateCount;
    }
    
    public double[][] getData() {
        return dataList3;
    }
    
    public void setData(double[][] lista) {
        dataList3 = lista;
    }
    
    public double getmaxWeight(int a) {
        return this.dataList3[a][3];
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
    
    public int getCount() {
        return this.count + this.count2;
    }
    
    public int getCount2() {
        return this.count2;
    }
    
    private void createDefaultDataList() {
        int cols = 5;
        int rows = this.count;
        dataList = new double[rows][cols];
        int cols2 = 6;
        int rows2 = this.count2;
        dataList2 = new double[rows2][cols2];
        int cols3 = 6;
        int rows3 = count + count2;
        dataList3 = new double[rows3][cols3];
    }
}
