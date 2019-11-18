package com.anomaly.handler;

import com.anomaly.api.AnomalyDetectionService;
import com.anomaly.exception.AnomalyDetectionException;
import com.anomaly.exception.AnomalyDetectionValidationException;
import com.anomaly.request.EventRequest;
import com.anomaly.response.EventResponse;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Handler to process the anomaly detection APIs
 */
public class AnomalyDetectionHandler implements HttpHandler {

    private AnomalyDetectionService anomalyDetectionService;
    private Gson gson;

    public AnomalyDetectionHandler(final AnomalyDetectionService anomalyDetectionService, final Gson gson) {
        this.anomalyDetectionService = anomalyDetectionService;
        this.gson = gson;
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "";
        try(InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8")) {
            final BufferedReader br = new BufferedReader(isr);
            String request = "";
            String line = "";
            while ((line = br.readLine()) != null)
                request += line;
            System.out.println(request);
            EventRequest eventRequest = gson.fromJson(request, EventRequest.class);
            EventResponse eventResponse = anomalyDetectionService.processEvent(eventRequest);
            response = gson.toJson(eventResponse);
            httpExchange.sendResponseHeaders(200, response.length());
        } catch (AnomalyDetectionValidationException exception) {
            response = "A Validation Error has occurred in validating the request.";
            System.out.println(exception.getMessage());
            httpExchange.sendResponseHeaders(400, response.length());
        }
        catch (AnomalyDetectionException exception) {
            response = "An Error has occurred while processing the request.";
            System.out.println(exception.getMessage());
            httpExchange.sendResponseHeaders(500, response.length());
        }
        catch (Exception exp) {
            response = "Internal Server Error";
            System.out.println(exp.getMessage());
            httpExchange.sendResponseHeaders(500, response.length());
        }
        finally {
            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}
