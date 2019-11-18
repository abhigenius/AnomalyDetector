package com.anomaly.model;

/**
 * Model for Anomaly Detector
 */
public class AnomalyDetectorModel {

    private String sensorId;

    private String model;

    private String threshold;

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    @Override
    public String toString() {
        return "AnomalyDetectorModel{" +
                "sensorId='" + sensorId + '\'' +
                ", model='" + model + '\'' +
                ", threshold='" + threshold + '\'' +
                '}';
    }
}
