import com.anomaly.api.AnomalyDetectionService;
import com.anomaly.api.AnomalyDetectionServiceImpl;
import com.anomaly.businesslogic.AnomalyDetectionLogic;
import com.anomaly.businesslogic.AnomalyDetectionLogicImpl;
import com.anomaly.dao.AnomalyDetectionDao;
import com.anomaly.dao.AnomalyDetectionDaoImpl;
import com.anomaly.exception.AnomalyDetectionException;
import com.anomaly.handler.AnomalyDetectionHandler;
import com.anomaly.handler.BaseHandler;
import com.anomaly.model.AnomalyDetectorModel;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Main Application class for Anomaly Detector.
 * This is where the application starts.
 */
public class Application {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        AnomalyDetectionDao anomalyDetectionDao = new AnomalyDetectionDaoImpl();
        AnomalyDetectionLogic anomalyDetectionLogic = new AnomalyDetectionLogicImpl(anomalyDetectionDao);
        // Map of sensorId and the Detector Model loaded at startup from config
        Map<String, AnomalyDetectorModel> detectorModelMap = loadAnomalyDetectorsFromConfig();
        AnomalyDetectionService anomalyDetectionService = new AnomalyDetectionServiceImpl(anomalyDetectionLogic, detectorModelMap);
        int serverPort = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
        System.out.println("Server started successfully " + serverPort);
        server.createContext("/", new BaseHandler());
        server.createContext("/api/event", new AnomalyDetectionHandler(anomalyDetectionService, gson));
        server.setExecutor(null);
        server.start();
    }

    private static Map<String, AnomalyDetectorModel> loadAnomalyDetectorsFromConfig() throws IOException {
        Map<String, AnomalyDetectorModel> detectorModelMap = new HashMap<>();
        try (Stream<Path> paths = Files.walk(Paths.get("config"))) {
            List<File> fileList = paths.filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            detectorModelMap = fileList.stream().map(f -> prepareDetectorModel(f))
                    .collect(Collectors.toMap(AnomalyDetectorModel::getSensorId, Function.identity()));
        }
        return  detectorModelMap;
    }

    private static AnomalyDetectorModel prepareDetectorModel(File f) {
        try {
            JsonReader reader = new JsonReader(new FileReader(f));
            Gson gson = new Gson();
            AnomalyDetectorModel model = gson.fromJson(reader, AnomalyDetectorModel.class);
            return model;
        } catch (FileNotFoundException e) {
            throw new AnomalyDetectionException(e.getMessage());
        }
    }
}
