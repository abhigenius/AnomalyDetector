package com.anomaly.businesslogic;

import com.anomaly.dao.AnomalyDetectionDao;
import com.anomaly.detector.AnomalyDetector;
import com.anomaly.detector.AnomalyDetectorFactory;
import com.anomaly.exception.AnomalyDetectionException;
import com.anomaly.model.AnomalyDetectorModel;
import com.anomaly.model.Event;
import com.anomaly.model.Status;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        //Fetches the model from the sensorId
        AnomalyDetectorModel anomalyDetectorModel = detectorModelMap.get(event.getSensorId());
        //System.out.println("AnomalyDetectorModel " + anomalyDetectorModel);
        anomalyDetector = AnomalyDetectorFactory.getDetector(anomalyDetectorModel.getModel());
        //Fetches all the existing events from the Database and filters event for the given sensor Id
        List<Event> eventList = anomalyDetectionDao.getAllEvents().stream()
                .filter(e -> (event.getSensorId().equalsIgnoreCase(e.getSensorId())))
                .collect(Collectors.toList());
        Event processedEvent = anomalyDetector.detectAnomaly(event, anomalyDetectorModel, eventList);
        return processedEvent;
    }
}
