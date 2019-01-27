package com.til;

import com.til.co.HeadLineCO;
import com.til.dto.HeadLineDTO;
import com.til.services.QueryHandlerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Satya on 31-07-2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryHandlerServiceTest {
    @Test
    public void test_getHeadLines() throws Exception{
        QueryHandlerService queryHandlerService = mock(QueryHandlerService.class);
        HeadLineDTO headLineDTO = new HeadLineDTO();
        headLineDTO.setStatus("ok");
        HeadLineCO headLineCO=new HeadLineCO();
        headLineCO.setCountry("us");
        when(queryHandlerService.getHeadLines(any(HeadLineCO.class))).thenReturn(headLineDTO);
        HeadLineDTO res = queryHandlerService.getHeadLines(headLineCO);
        assertEquals(res.getStatus(),"ok");
    }
}
