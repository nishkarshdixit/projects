package com.til.controller.v1;

import com.til.co.HeadLineCO;
import com.til.dto.HeadLineDTO;
import com.til.dto.ResponseDTO;
import com.til.services.QueryHandlerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Satya on 31-07-2018.
 */
@RestController
@RequestMapping("/rest/v1")
public class HeadLineController {
    @Autowired
    QueryHandlerService queryHandlerService;
    private static Logger log = LogManager.getLogger(HeadLineController.class);

    @RequestMapping("/headlines")
    public ResponseEntity<ResponseDTO> getHeadlines(@RequestBody HeadLineCO headLineCO) {
        log.info("Received query for headlines: "+headLineCO);
        ResponseDTO<HeadLineDTO> responseDTO = new ResponseDTO();
        try {
            HeadLineDTO headLineDTO = queryHandlerService.getHeadLines(headLineCO);
            if(headLineDTO.getStatus().equals("error")){
                responseDTO.setStatus(HttpStatus.BAD_REQUEST);
                responseDTO.setMessage(headLineDTO.getMessage());
                return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
            }else {
                responseDTO.setStatus(HttpStatus.OK);
                responseDTO.setData(headLineDTO);
                responseDTO.setMessage("success");
                return new ResponseEntity<>(responseDTO,HttpStatus.OK);
            }
        } catch (Exception e) {
            responseDTO.setStatus(HttpStatus.BAD_REQUEST);
            responseDTO.setMessage("Failed to get Headlines");
            return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
        }
    }
}
