
package wbapp.domain;

import java.util.ArrayList;
import wbapp.domain.AircraftData;

/**
 *
 * @author santeripitkanen
 */
public class WBCalculator {
    
    private double[][] data;
    private double[][] data2;
    private double[][] data3;
    private int index;
    private ArrayList<Double> weights;
    private int count;
    private int count2;
    
    public WBCalculator(double[][] data, double[][] data2, double[][] data3, ArrayList<Double> weights, int count, int count2) {
        this.data = data;
        this.data2 = data2;
        this.data3 = data3;
        this.weights = weights;
        this.count = count;
        this.count2 = count2;
        
    } 
    
    public void listWeights() {
        for (int i = 0; i < this.weights.size(); i++) {
            this.data2[i][1] = this.weights.get(i);
        }
    }
    
    public double[][] calculateData() {
        listWeights();
        double weight = this.data[0][1];
        double moment = this.data[0][2];
        this.data3[0][0] = this.data[0][0];
        this.data3[0][1] = this.data[0][1];
        this.data3[0][2] = this.data[0][2];
        this.data3[0][3] = this.data[0][3];
        this.data3[0][4] = this.data[0][4];
        int k = 1;
        int p = 1;
        for (int i = 1; i < count; i++) {
            for (int l = 0; l < count2; l++) {
                if (this.data2[l][4] == p) {
                    this.data3[k][0] = this.data2[l][0];
                    this.data3[k][1] = this.data2[l][1];
                    this.data3[k][2] = calculateMoment(this.data2[l][0], this.data2[l][1]);
                    this.data3[k][3] = this.data2[l][3];
                    this.data3[k][4] = this.data2[l][4];
                    if (p == 1 || p == 2) {
                        weight += this.data3[k][1];
                        moment += this.data3[k][2];
                        System.out.println(moment);
                        k++;
                    } else {
                        weight -= this.data3[k][1];
                        moment -= this.data3[k][2];
                        k++;
                    }
                }
            }
            this.data3[k][0] = calculateArm(weight, moment);
            this.data3[k][1] = weight;
            this.data3[k][2] = moment;
            this.data3[k][3] = this.data2[i][3];
            this.data3[k][4] = this.data2[i][4];
            k++;
            p++;
        }
        return this.data3;
    }
    
    public double calculateMoment(double arm, double weight) {
        double moment = arm * weight / 100;
        double round = Math.round(moment * 100) / 100;
        return round;
    }
    
    public double calculateArm(double weight, double moment) {
        double arm = moment * 100 / weight;
        double round = Math.round(arm * 100) / 100;
        return round;
    }
}
