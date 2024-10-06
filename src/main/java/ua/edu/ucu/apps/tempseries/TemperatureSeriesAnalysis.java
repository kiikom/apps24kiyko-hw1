package ua.edu.ucu.apps.tempseries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private int currentSize;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[0];
        this.currentSize = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double temp : temperatureSeries) {
            if (temp < -273) {
                throw new InputMismatchException("Temperature below -273°C detected");
            }
        }
        this.temperatureSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
        this.currentSize = temperatureSeries.length;
    }

    public double average() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temprature is empty");
        }
        double sum = 0;
        for (double temp : temperatureSeries) {
            sum += temp;
        }
        return sum / temperatureSeries.length;
    }

    public double deviation() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temprature is empty");

        }
        double mean = average();
        double sumSquareDiffs = 0;
        for (double temp : temperatureSeries) {
            sumSquareDiffs += Math.pow(temp - mean, 2);
        }
        return Math.sqrt(sumSquareDiffs / temperatureSeries.length);
    }

    public double min() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temprature is empty");

        }
        double minTemperature = temperatureSeries[0];

        for (double temp : temperatureSeries) {
            if (temp < minTemperature) {
                minTemperature = temp;
            }
        }
        return minTemperature;
    }

    public double max() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temprature is empty");

        }
        double maxTemperature = temperatureSeries[0];

        for (double temp : temperatureSeries) {
            if (temp > maxTemperature) {
                maxTemperature = temp;
            }

        }
        return maxTemperature;
    }

    public double findTempClosestToZero() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException("Temperature is empty");
        }

        double closestTemp = temperatureSeries[0];
        for (double temp : temperatureSeries) {
            if (Math.abs(temp) < Math.abs(closestTemp) ||
                    (Math.abs(temp) == Math.abs(closestTemp) && temp > closestTemp)) {
                closestTemp = temp;
            }
        }
        return closestTemp;
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperatureSeries.length == 0){
            throw new IllegalArgumentException("Temperature is empty");
        }
        double closestTemp = temperatureSeries[0];
        for (double temp : temperatureSeries){
            if (Math.abs(temp - tempValue) < Math.abs(closestTemp - tempValue) ||
            (Math.abs(temp - tempValue) == Math.abs(closestTemp - tempValue) && temp > closestTemp)) {
            closestTemp = temp;
        }
            }
            return closestTemp;
        }

    public double[] findTempsLessThen(double tempValue) {
        List<Double> res = new ArrayList<>();
        for (double temp: temperatureSeries){
            if (temp<tempValue){
                res.add(temp);
            }
        }
        double[] resArr = new double[res.size()];
        for (int i = 0; i<res.size(); i++){
            resArr[i] = res.get(i);
        }
        return resArr;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        List<Double> res = new ArrayList<>();
        for (double temp: temperatureSeries){
            if (temp>= tempValue){
                res.add(temp);
            }
        }
        double[] resArr = new double[res.size()];
        for (int i = 0; i<res.size(); i++){
            resArr[i] = res.get(i);
        }
        return resArr;
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        List<Double> res = new ArrayList<>();
        
        for (double temp : temperatureSeries){
            if (temp>= lowerBound && temp<= upperBound){
                res.add(temp);
            }
        }
        double[] resArr = new double[res.size()];
        for (int i = 0; i<res.size(); i++){
            resArr[i] = res.get(i);
        }
        return resArr;
    }

    public void reset() {
        this.temperatureSeries = new double[0];

    }

    public double[] sortTemps() {
        double[] sortedTemp = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
        Arrays.sort(sortedTemp);
        return sortedTemp;
    }

    
    public TempSummaryStatistics summaryStatistics() {
        if (temperatureSeries.length == 0){
            throw new IllegalArgumentException("Teprature is empty");
        }
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        for (double temp : temps) {
            if (temp < -273) {
                throw new InputMismatchException("Temperature below -273°C detected");
            }
        }

        if (currentSize + temps.length > temperatureSeries.length) {
            int newCapacity = Math.max(temperatureSeries.length * 2, currentSize + temps.length);
            temperatureSeries = Arrays.copyOf(temperatureSeries, newCapacity);
        }
        for (double temp : temps) {
            temperatureSeries[currentSize++] = temp;
        }
        return currentSize;
    }
}
