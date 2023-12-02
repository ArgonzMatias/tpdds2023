package ar.edu.utn.frba.dds.app.rankeador.client;

import ar.edu.utn.frba.dds.app.exceptions.APIRestException;
import ar.edu.utn.frba.dds.app.exceptions.DeserealizacionException;
import ar.edu.utn.frba.dds.app.rankeador.client.dto.RequestMayorImpacto;
import ar.edu.utn.frba.dds.app.rankeador.client.dto.ResponseMayorImpacto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

public class RankingMayorImpactoClient {
    private final String apiUrl;

    public RankingMayorImpactoClient(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public ResponseMayorImpacto enviarSolicitud(RequestMayorImpacto request) {
        try {
            HttpURLConnection connection = createConnection();
            String requestBody = buildRequestBody(request);
            sendRequest(connection, requestBody);
            return processResponse(connection);
        } catch (Exception e) {
            throw new RuntimeException("Error en APIRest", e);
        }
    }

    private HttpURLConnection createConnection() throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        return connection;
    }

    public static String buildRequestBody(RequestMayorImpacto request) {
        JSONObject requestBody = new JSONObject(request);
        return requestBody.toString();
    }

    private void sendRequest(HttpURLConnection connection, String requestBody) throws IOException {
        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            outputStream.writeBytes(requestBody);
        }
    }
    private ResponseMayorImpacto processResponse(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        if (responseCode != HttpStatus.SC_OK) {
            throw new APIRestException("La solicitud no fue exitosa. Código de respuesta: " + responseCode);
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }
        connection.disconnect();
        System.out.println("Response Body: " + response);

        return parseApiResponse(response.toString());
    }

    private ResponseMayorImpacto parseApiResponse(String jsonResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonResponse, ResponseMayorImpacto.class);
        } catch (IOException e) {
            throw new DeserealizacionException("Error al deserializar la respuesta JSON", e);
        }
    }
}
