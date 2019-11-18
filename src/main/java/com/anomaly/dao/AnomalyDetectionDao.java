package com.anomaly.dao;

import com.anomaly.model.Event;

import java.util.List;

/**
 * In Memory Data Store for Events
 */
public interface AnomalyDetectionDao {
    void createEvent(Event event);
    List<Event> getAllEvents();
    Event getEvent(String eventId);
}
