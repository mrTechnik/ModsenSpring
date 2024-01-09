package library.app.requests;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import library.app.domain.BookOrderID;

public class HttpPostJSONRequest {
    private static String link = "http://localhost:8081/orderservice/add";

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    public ResponseEntity<String> sendHttpPostJSONReques(BookOrderID bookOrderID) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = objectMapper.writeValueAsString(bookOrderID);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);
        System.out.println("!!!!!!!!!!!!!!!!!!" + requestJson);
        System.out.println("!!!!!!!!!!!!!!!!!!" + requestEntity);

        return restTemplate.exchange(link, HttpMethod.POST, requestEntity, String.class);
    } 
}
