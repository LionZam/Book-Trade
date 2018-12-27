package ru.itis.GoodReads;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.ResponseEntity;
import ru.itis.DTOs.BookFromSearch;
import ru.itis.models.Book;
import org.springframework.web.client.RestTemplate;


import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class GoodReadsRequest {

    private ObjectMapper objectMapper = new ObjectMapper();
    private URL SEARCH_URL;
    private URL GET_BOOK_BY_ID_URL;
    {
        try {
            SEARCH_URL = new URL("https://www.goodreads.com/search/index.xml?");
            GET_BOOK_BY_ID_URL = new URL("https://www.goodreads.com/book/show/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    protected String key = "VWFKgYW1DOphdMHFBQQt4Q";


    public List<BookFromSearch> searchBooks(HashMap<String, String> parameters) {
        try {
            parameters.put("key", key);
            JSONObject jsonObject = sendRequest(SEARCH_URL, parameters);
            JSONArray test = jsonObject.getJSONObject("search").getJSONObject("results").getJSONArray("work");
            return objectMapper.readValue(test.toString(), new TypeReference<List<BookFromSearch>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Book getBookById(Long id, HashMap<String, String> parameters) {
        JSONObject jsonObject;
        try {
            parameters.put("key", key);
            jsonObject = sendRequest(new URL(GET_BOOK_BY_ID_URL.toString() + id + ".xml?"), parameters);
            return objectMapper.readValue(jsonObject.toString(), Book.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JSONObject XMLToJSON(String string) {
        JSONObject xmlJSONObj = null;
        try {
            xmlJSONObj = XML.toJSONObject(string).getJSONObject("GoodreadsResponse");
        } catch (JSONException je) {
            System.out.println(je.toString());
        }
        return xmlJSONObj;
    }

    private JSONObject sendRequest(URL url, HashMap<String, String> parameters) throws IOException {
        String endURl = url.toString()+ParameterStringBuilder.getParamsString(parameters);

/*        URLConnection con = endURl.openConnection();
        con.setDoOutput(true);
        con.setRequestProperty("charset", "UTF-8");
        con.setRequestProperty("Accept-Charset", "UTF-8");

        //Get Response
        InputStream is = con.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();*/

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(endURl, String.class);

        return XMLToJSON(responseEntity.getBody());
    }


    private static class ParameterStringBuilder {
        public static String getParamsString(Map<String, String> params)
                throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();

            for (Map.Entry<String, String> entry : params.entrySet()) {
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                result.append("&");
            }

            String resultString = result.toString();
            return resultString.length() > 0
                    ? resultString.substring(0, resultString.length() - 1)
                    : resultString;
        }
    }
}
