package org.kamil.measuresensorapi.client;

import org.kamil.measuresensorapi.models.Measurement;
import org.kamil.measuresensorapi.models.Sensor;
import org.springframework.http.*;
import org.springframework.validation.ObjectError;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientTesting {
    public static void main(String[] args) {
        doPostSensor("sensor");
        doPostMeasurement("sensor");
        System.out.println(doGet("http://localhost:8080/measurements").getMeasurements());
    }

    public static MeasurementResponse doGet(String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, MeasurementResponse.class);
    }

    public static void doPostMeasurement(String sensorName) {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 1000; i++) {
            Map<String, Object> jsonToPost = new HashMap<>();

            jsonToPost.put("value", 24.7);
            jsonToPost.put("raining", true);
            jsonToPost.put("sensor", Map.of("name", sensorName));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(jsonToPost, headers);

            restTemplate.postForLocation("http://localhost:8080/measurements/add",request);
        }
    }
    public static void doPostSensor(String name){
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> jsonToPost = new HashMap<>();

        jsonToPost.put("name",name);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String,String>> request = new HttpEntity<>(jsonToPost,headers);
        restTemplate.postForLocation("http://localhost:8080/sensors/registration",request);

    }
}