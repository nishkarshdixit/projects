package com.til.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.til.co.HttpCO;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

/**
 * Created by DB on 08-06-2017.
 */
public class HttpUtils {
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     *
     * @param httpCO
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T post(HttpCO httpCO, Class<T> clazz) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(httpCO.getUrl());
        Map<String, String> headerMap = httpCO.getHeaders();
        Set<Map.Entry<String, String>> headerMapEntrySet = headerMap.entrySet();
        for (Map.Entry<String, String> headerMapEntry : headerMapEntrySet)
            post.setHeader(headerMapEntry.getKey(), headerMapEntry.getValue());
        post.setEntity(new StringEntity(gson.toJson(httpCO.getBody()), Charset.forName("UTF-8")));
        HttpResponse response = client.execute(post);
        T data = gson.fromJson(new InputStreamReader(response.getEntity().getContent()), clazz);
        return data;
    }


    /**
     *
     * @param httpCO
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    public static <T> T get(HttpCO httpCO,Class<T> clazz) throws IOException, URISyntaxException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder(httpCO.getUrl());
        Map<String, String> parameterMap = httpCO.getParameters();
        Set<Map.Entry<String, String>> parameterMapEntrySet = parameterMap.entrySet();
        for (Map.Entry<String, String> parameterMapEntry : parameterMapEntrySet)
            uriBuilder.addParameter(parameterMapEntry.getKey(), parameterMapEntry.getValue());
        HttpGet get = new HttpGet(uriBuilder.build());
        Map<String, String> headerMap = httpCO.getHeaders();
        Set<Map.Entry<String, String>> headerMapEntrySet = headerMap.entrySet();
        for (Map.Entry<String, String> headerMapEntry : headerMapEntrySet)
            get.setHeader(headerMapEntry.getKey(), headerMapEntry.getValue());
        HttpResponse response = client.execute(get);
        T data = gson.fromJson(new InputStreamReader(response.getEntity().getContent()), clazz);
        return data;
    }
}
