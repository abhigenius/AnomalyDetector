package com.anomaly.detector;

import com.anomaly.model.AnomalyDetectorModel;
import com.anomaly.model.Event;

public interface AnomalyDetector {

    Event detectAnomaly(Event event, AnomalyDetectorModel anomalyDetectorModel);

}
