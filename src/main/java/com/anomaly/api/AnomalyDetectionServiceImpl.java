package com.anomaly.api;

import com.anomaly.businesslogic.AnomalyDetectionLogic;
import com.anomaly.exception.AnomalyDetectionException;
import com.anomaly.exception.AnomalyDetectionValidationException;
import com.anomaly.model.AnomalyDetectorModel;
import com.anomaly.model.Event;
import com.anomaly.request.EventRequest;
import com.anomaly.response.EventResponse;

import java.util.Map;

public class AnomalyDetectionServiceImpl implements AnomalyDetectionService {

    private AnomalyDetectionLogic anomalyDetectionLogic;

    private Map<String, AnomalyDetectorModel> detectorModelMap;

    public AnomalyDetectionServiceImpl(AnomalyDetectionLogic anomalyDetectionLogic, Map<String, AnomalyDetectorModel> detectorModelMap) {
        this.anomalyDetectionLogic = anomalyDetectionLogic;
        this.detectorModelMap = detectorModelMap;
    }

    @Override
    public EventResponse processEvent(final EventRequest eventRequest) {
        try {
            validateRequest(eventRequest);
            System.out.println("Event Request " + eventRequest);
            Event event = anomalyDetectionLogic.processEvent(prepareEventFromEventRequest(eventRequest), detectorModelMap );
            EventResponse eventResponse = prepareEventResponse(event);
            System.out.println("Event Response " + eventResponse);
            return eventResponse;
        } catch (AnomalyDetectionValidationException exception){
            throw exception;
        }
        catch (Exception exception) {
            throw new AnomalyDetectionException(exception.getMessage());
        }
    }

    private void validateRequest(final EventRequest eventRequest) {
        if (null == eventRequest || null == eventRequest.getEventId() || null == eventRequest.getSensorId()
        || null == eventRequest.getTimestamp() || null == eventRequest.getValue()) {
            throw new AnomalyDetectionValidationException("Invalid Request");
        }
    }

    private EventResponse prepareEventResponse(Event event) {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setEventId(event.getEventId());
        eventResponse.setSensorId(event.getSensorId());
        eventResponse.setTimestamp(event.getTimestamp());
        eventResponse.setValue(event.getValue());
        eventResponse.setCause(event.getCause());
        eventResponse.setMessage(event.getMessage());
        eventResponse.setStatus(event.getStatus());
        return eventResponse;
    }

    private Event prepareEventFromEventRequest(EventRequest eventRequest) {
        Event event = new Event();
        event.setEventId(eventRequest.getEventId());
        event.setSensorId(eventRequest.getSensorId());
        event.setTimestamp(eventRequest.getTimestamp());
        event.setValue(eventRequest.getValue());
        return event;
    }
}
