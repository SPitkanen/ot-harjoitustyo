
package wbapp.ui;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import wbapp.dao.acDataDao;
import wbapp.domain.AircraftData;
import wbapp.domain.WBCalculator;
import wbapp.domain.Aircraft;
import wbapp.domain.User;
import java.sql.*;
/**
 *
 * @author santeripitkanen
 */
public class wbUi {
    public void start() {
        acDataDao data = new acDataDao();
        int count = 0;
        int count2 = 0;
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
            count = data.getCount(2);
            count2 = data.getCountDepend(2);
            rs = data.getAcData(2);
            rs2 = data.getAcDataDepend(2);
        } catch (SQLException e) {
            System.out.println("Virhe: " + e);
        }
        AircraftData acData = new AircraftData(count, count2, rs, rs2);
        double[][] dataList = acData.getDataList();
        double[][] dataList2 = acData.getDataListDepend();
        double[][] dataList3 = acData.getFullDataList();
        ArrayList<String> items = acData.getFullItemList();
        ArrayList<String> items2 = acData.getDataItemsDepend();
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<Double> weights = new ArrayList<>();
        for (int i = 0; i < items2.size(); i++) {
            System.out.print(items2.get(i) + ": ");
            double weight = Double.valueOf(scanner.nextLine());
            double max = dataList2[i][3];
            if (weight < 0 || weight > max) {
                System.out.println("Paino vähintään 0 ja enintään " + max + "!");
                i--;
            } else {
                weights.add(weight);
            }
        }
        WBCalculator calculator = new WBCalculator(dataList, dataList2, dataList3, weights, count, count2);
        dataList3 = calculator.calculateData();
        System.out.println("");
        System.out.println(dataList3[count + count2 - 1][1]);
    }
}
