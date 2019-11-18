package com.anomaly.api;

import com.anomaly.request.EventRequest;
import com.anomaly.response.EventResponse;

public interface AnomalyDetectionService {
     EventResponse processEvent(EventRequest request);
}
