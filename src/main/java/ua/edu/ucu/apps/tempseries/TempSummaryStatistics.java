package ua.edu.ucu.apps.tempseries;

public class TempSummaryStatistics {
    private double average;
    private double deviation;
    private double min;
    private double max;

    public TempSummaryStatistics(double average, double deviation, double min, double max) {
        this.average = average;
        this.deviation = deviation;
        this.min = min;
        this.max = max;
    }
    public double getAverage() {
        return average;
    }

    public double getDeviation() {
        return deviation;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}

