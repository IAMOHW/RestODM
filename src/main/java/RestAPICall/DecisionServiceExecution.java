package RestAPICall;

/**
 * @Created with IntelliJ IDEA
 * @Author: czs
 * @Version 1.0
 * @Date: 2020-11-26
 * @Time: 11:44
 **/
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class DecisionServiceExecution {

    public static void main(String[] args) throws ClientProtocolException, IOException {


        String endpointURI = "http://localhost:9099/DecisionService/rest/v1/resdeployment/1.0/DeploymentD";

        //GET http://localhost:9099/DecisionService/rest/v1/api/v1/decisiontraces/{execution_id}[?parts={string}]

        String userName = "resAdmin";
        String password = "resAdmin";

        String credentials = userName + ":" + password;
        String encodedValue = Base64.encodeBase64String(credentials.getBytes());
        String authorization = "Basic " + new String(encodedValue);
        String contentType = "application/json";

        String studentInfo =
                "{\"StudentInfos\":" + " {" +
                "\"name\": \"zhan\"," +
                "\"age\": 10" +
                     "}" +
                "}";

        CloseableHttpClient client = HttpClients.createDefault();

        try {
            HttpPost httpPost = new HttpPost(endpointURI);
            httpPost.addHeader("Authorization", authorization);
            httpPost.setEntity(new StringEntity(studentInfo));
            httpPost.addHeader("Content-Type", contentType);
            CloseableHttpResponse response = client.execute(httpPost);
            try {
                if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    System.err.println("Status Code: " + response.getStatusLine().getStatusCode());
                    System.err.println("Status Line: " + response.getStatusLine());
                    String responseEntity = EntityUtils.toString(response.getEntity());
                    System.err.println("Response Entity: " + responseEntity);
                    throw new RuntimeException(
                            "An error occurred when invoking Decision Service at: "
                                    + endpointURI
                                    + "\n"
                                    + response.getStatusLine() + ": " + responseEntity);
                } else {
                    String result = EntityUtils.toString(response.getEntity());
                    System.out.println("Result: " + result);
                }
            } finally {
                if (response != null) {
                    response.close();
                }
            }
        } finally {
            client.close();
        }
    }

}