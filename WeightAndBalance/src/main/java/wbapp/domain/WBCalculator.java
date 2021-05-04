
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
    /**
    * Method calculates full weight and balance sheet from data list.
    * For each row dependant on aircraft type moment is calculated, weight is the user input.
    * For each row representing a intermediate step mass, arm, weight and moment is calculated.
    * if section = 0 (intermediate step) weight is previous intermediate stage +/- all dependant weights.
    * else weights are added. operation = 1 is added and operation = 0 is subtracted.
    * @param   data   Is the full data matrice containing all of the values before calculation
    * @param count is number of rows in data matrice
    *
    * @return is the matrice + calculated values
    */
    public double[][] calculateData(double[][] data, int count) {
        this.data = data;
        this.count = count;
        double weight = this.data[0][1];
        double moment = this.data[0][2];
        for (int i = 1; i < count; i++) {
            if (i > 1 && this.data[i][4] == 0) {
                //weight = Math.round(weight * 100) / 100;
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
    
    /**
    * Method calculates moment
    * 
    * @param arm arm from given section
    * @param weight weight from given section
    * 
    * @return rounded value
    *
    */
    public double calculateMoment(double arm, double weight) {
        double moment = arm * weight / 100;
        double round = Math.round(moment * 100.0) / 100.0;
        return round;
    }
    
    /**
    * Method calculates arm
    * 
    * @param moment moment from given section
    * @param weight weight from given section
    * 
    * @return rounded value
    *
    */
    public double calculateArm(double weight, double moment) {
        double arm = moment * 100 / weight;
        double round = Math.round(arm * 100.0) / 100.0;
        return round;
    }
}
