package com.anomaly.detector;

/**
 * Factory class for getting the AnomalyDetector
 */
public class AnomalyDetectorFactory {
    static UpperBoundThresholdAnomalyDetector upperBoundThresholdAnomalyDetector = new UpperBoundThresholdAnomalyDetector();
    static AverageThresholdAnomalyDetector averageThresholdAnomalyDetector = new AverageThresholdAnomalyDetector();
    public static AnomalyDetector getDetector(String detectorName) {
        if(detectorName == null) {
            return null;
        }
        if("UpperBoundThresholdAnomalyDetector".equals(detectorName)) {
            return upperBoundThresholdAnomalyDetector;
        }
        if("AverageThresholdAnomalyDetector".equals(detectorName)) {
            return averageThresholdAnomalyDetector;
        }
        return null;
    }
}
