package com.anomaly.detector;

import com.anomaly.model.AnomalyDetectorModel;
import com.anomaly.model.Event;

import java.util.List;

public interface AnomalyDetector {

    Event detectAnomaly(Event event, AnomalyDetectorModel anomalyDetectorModel, List<Event> eventList);

}
