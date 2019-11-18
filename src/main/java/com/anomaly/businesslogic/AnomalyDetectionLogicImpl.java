package com.anomaly.businesslogic;

import com.anomaly.dao.AnomalyDetectionDao;
import com.anomaly.detector.AnomalyDetector;
import com.anomaly.detector.AnomalyDetectorFactory;
import com.anomaly.exception.AnomalyDetectionException;
import com.anomaly.model.AnomalyDetectorModel;
import com.anomaly.model.Event;
import com.anomaly.model.Status;

import java.util.Map;

public class AnomalyDetectionLogicImpl implements AnomalyDetectionLogic {

    private AnomalyDetectionDao anomalyDetectionDao;

    private AnomalyDetector anomalyDetector;

    public AnomalyDetectionLogicImpl(AnomalyDetectionDao anomalyDetectionDao) {
        this.anomalyDetectionDao = anomalyDetectionDao;
    }

    @Override
    public Event processEvent(Event event, Map<String, AnomalyDetectorModel> detectorModelMap) {
        anomalyDetectionDao.createEvent(event);
        if(!detectorModelMap.containsKey(event.getSensorId())) {
            // SensorId is not in the configuration in config folder
            event.setCause("");
            event.setMessage("");
            event.setStatus(Status.NO_MODEL.toString());
            return event;
        }
        AnomalyDetectorModel anomalyDetectorModel = detectorModelMap.get(event.getSensorId());
        anomalyDetector = AnomalyDetectorFactory.getDetector(anomalyDetectorModel.getModel());
        Event processedEvent = anomalyDetector.detectAnomaly(event, anomalyDetectorModel);
        return processedEvent;
    }
}
