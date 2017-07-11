package cordova.plugin.getApi;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ResponseData {
    // private ArrayList<String> addResponseList;
    private JSONArray jsonArray;
    // jsonArray.put(jsonObj)
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";


    public JSONArray callAPI(JSONArray arr, int count) throws IOException {
        //  addResponseList= new ArrayList<String>();
        jsonArray = new JSONArray();

        JSONArray jsonArrRes = sendGET(arr, count);
        return jsonArrRes;

    }

    private JSONArray sendGET(JSONArray arr, int count) throws IOException {
        String GET_URL = null;
        try {
            GET_URL = "https://reg.gst.gov.in/master/jursd/centrerange/" + arr.getJSONObject(count).getString("divisioncode");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("GET_URL  ----- " + GET_URL);
        URL obj = new URL(GET_URL);

        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        con.setRequestProperty("Accept-Encoding", "deflate, br");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
        con.setRequestProperty("Connection", "keep-alive");
        con.setRequestProperty("Host", "reg.gst.gov.in");
        con.setRequestProperty("Upgrade-Insecure-Requests", "1");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            if (response != null) {
                // addResponseList.add(response.toString());
                jsonArray.put(response.toString());

            }


            count = count + 1;
            if (arr.length() != count) {

                sendGET(arr, count);

            } else {

            }

        } else {


        }


        return jsonArray;
    }
}
