package com.anomaly.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Base Handler to check if the server is up and running.
 */
public class BaseHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "<h1>Server started successfully. </h1>";
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
