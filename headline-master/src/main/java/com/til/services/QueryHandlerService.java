package com.til.services;

import com.til.co.HeadLineCO;
import com.til.co.HttpCO;
import com.til.constants.HeadLineAPIConstants;
import com.til.dto.HeadLineDTO;
import com.til.exception.HeadLineException;
import com.til.utils.APIConfiguration;
import com.til.utils.HttpUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Satya on 31-07-2018.
 */
@Service
public class QueryHandlerService {
    private static Logger log = LogManager.getLogger(QueryHandlerService.class);
    @Autowired
    APIConfiguration apiConfiguration;
    public HeadLineDTO getHeadLines(HeadLineCO headLineCO) throws Exception{
        Instant startInstance = Instant.now();
        Map<String, String> requestMap = convertToRequestMap(headLineCO);
        HttpCO httpCO=new HttpCO();
        httpCO.setUrl(apiConfiguration.getApiURL());
        httpCO.setParameters(requestMap);
        HeadLineDTO headLineDTO= null;
        try {
            headLineDTO = HttpUtils.get(httpCO,HeadLineDTO.class);
        } catch (IOException e) {
            log.error("Exception in getting data from API.",e);
            throw new HeadLineException(e.getMessage());
        } catch (URISyntaxException e) {
            log.error("Exception in URL Parsing.",e);
            throw new HeadLineException(e.getMessage());
        }
        Instant endInstance = Instant.now();
        log.info("headline API took seconds: "+ Duration.between(startInstance, endInstance).getSeconds());
        return headLineDTO;
    }

    /**
     *
     * @param headLineCO request object
     * @return A map so that it can be available to HTTPUtils
     */
    Map<String,String> convertToRequestMap(HeadLineCO headLineCO) {
        Map<String, String> requestMap = new HashMap<>();
        if (!StringUtils.isEmpty(headLineCO.getCategory())) {
            requestMap.put(HeadLineAPIConstants.CATEGORY, headLineCO.getCategory());
        }
        if (!StringUtils.isEmpty(headLineCO.getCountry())) {
            requestMap.put(HeadLineAPIConstants.COUNTRY, headLineCO.getCountry());
        }
        if (!StringUtils.isEmpty(headLineCO.getQ())) {
            requestMap.put(HeadLineAPIConstants.Q, headLineCO.getQ());
        }
        if (headLineCO.getSources() != null && !headLineCO.getSources().isEmpty()) {
            requestMap.put(HeadLineAPIConstants.SOURCES, headLineCO.getSources().stream().collect(Collectors.joining(",")));
        }
        requestMap.put(HeadLineAPIConstants.PAGE_SIZE, String.valueOf(headLineCO.getPageSize()));
        requestMap.put(HeadLineAPIConstants.PAGE, String.valueOf(headLineCO.getPage()));
        requestMap.put(HeadLineAPIConstants.API_KEY, apiConfiguration.getApiKey());
        return requestMap;
    }
}
