
package wbapp.domain;

import java.util.ArrayList;
import wbapp.domain.AircraftData;

/**
 *
 * @author santeripitkanen
 */
public class WBCalculator {
    
    private double[][] data;
    private int index;
    private int count;
    
    public WBCalculator() {
        
    }
    
    public double[][] calculateData(double[][] data, int count) {
        this.data = data;
        this.count = count;
        double weight = this.data[0][1];
        double moment = this.data[0][2];
        for (int i = 1; i < count; i++) {
            if (i > 1 && this.data[i][4] == 0) {
                this.data[i][1] = weight;
                this.data[i][2] = moment;
                this.data[i][0] = calculateArm(weight, moment);
            } else {
                if (this.data[i][5] == 1) {
                    weight += this.data[i][1];
                    moment += calculateMoment(this.data[i][0], this.data[i][1]);
                    this.data[i][2] = calculateMoment(this.data[i][0], this.data[i][1]);
                }
                if (this.data[i][5] == 0) {
                    weight -= this.data[i][1];
                    moment -= calculateMoment(this.data[i][0], this.data[i][1]);
                    this.data[i][2] = calculateMoment(this.data[i][0], this.data[i][1]);
                }
            }
        }
        return this.data;
    }
    
    public double calculateMoment(double arm, double weight) {
        double moment = arm * weight / 100;
        double round = Math.round(moment * 100.0) / 100.0;
        return round;
    }
    
    public double calculateArm(double weight, double moment) {
        double arm = moment * 100 / weight;
        double round = Math.round(arm * 100.0) / 100.0;
        return round;
    }
}
