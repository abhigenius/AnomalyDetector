package com.anomaly.dao;

import com.anomaly.model.Event;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AnomalyDetectionDaoImpl implements AnomalyDetectionDao {

    private final HashMap<String,Event> eventMap;

    public AnomalyDetectionDaoImpl() {
        this.eventMap = new HashMap<>();
    }

    @Override
    public void createEvent(Event event) {
        eventMap.put(event.getEventId(), event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventMap.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Event getEvent(String eventId) {
        return eventMap.get(eventId);
    }

}
