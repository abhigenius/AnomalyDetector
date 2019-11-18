package com.anomaly.model;

/**
 * Model for Event
 */
public class Event {

    private String eventId;

    private String sensorId;

    private String timestamp;

    private String value;

    private String status;

    private String cause;

    private String message;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", sensorId='" + sensorId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", value='" + value + '\'' +
                ", status='" + status + '\'' +
                ", cause='" + cause + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
