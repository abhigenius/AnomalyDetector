package com.anomaly.businesslogic;

import com.anomaly.model.AnomalyDetectorModel;
import com.anomaly.model.Event;

import java.util.Map;

public interface AnomalyDetectionLogic {
    Event processEvent(Event event, Map<String, AnomalyDetectorModel> detectorModelMap);
}
