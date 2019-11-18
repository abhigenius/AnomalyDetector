package com.anomaly.detector;

import com.anomaly.model.AnomalyDetectorModel;
import com.anomaly.model.Event;
import com.anomaly.model.Status;

public class UpperBoundThresholdAnomalyDetector implements AnomalyDetector {

    @Override
    public Event detectAnomaly(Event event, AnomalyDetectorModel anomalyDetectorModel) {
        Double threshold = Double.parseDouble(anomalyDetectorModel.getThreshold());
        Double eventValue = Double.parseDouble(event.getValue());
        if(eventValue < threshold) {
            event.setStatus(Status.NO_ANOMALY.toString());
            event.setMessage("");
            event.setCause("");
        }
        else  {
            event.setStatus(Status.ANOMALY.toString());
            event.setMessage("Exceeds threshold");
            event.setCause("Upper Bound Threshold Detector");
        }
        return event;
    }
}
