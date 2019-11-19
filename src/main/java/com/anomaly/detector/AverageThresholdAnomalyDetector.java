package com.anomaly.detector;

import com.anomaly.model.AnomalyDetectorModel;
import com.anomaly.model.Event;
import com.anomaly.model.Status;

import java.util.List;

public class AverageThresholdAnomalyDetector implements AnomalyDetector {

    @Override
    public Event detectAnomaly(Event event, AnomalyDetectorModel anomalyDetectorModel, List<Event> eventList) {
        Double threshold = Double.parseDouble(anomalyDetectorModel.getThreshold());
        System.out.println("Number of Events : " + eventList.size());
        Double average = calculateAverage(eventList);
        if(average < threshold) {
            event.setStatus(Status.NO_ANOMALY.toString());
            event.setMessage("");
            event.setCause("");
        }
        else  {
            event.setStatus(Status.ANOMALY.toString());
            event.setMessage("Exceeds threshold");
            event.setCause("Average Threshold Anomaly Detector");
        }
        return event;
    }

    private Double calculateAverage(List<Event> eventList) {
        Double sum = 0.0;
        if(eventList!= null && eventList.size()!= 0) {
            for (Event e : eventList) {
                sum += Double.parseDouble(e.getValue());
            }
            return sum/eventList.size();
        }
        return 0.0;
    }
}
