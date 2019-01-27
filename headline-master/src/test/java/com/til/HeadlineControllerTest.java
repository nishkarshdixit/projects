package com.til;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.til.co.HeadLineCO;
import com.til.controller.v1.HeadLineController;
import com.til.dto.HeadLineDTO;
import com.til.services.QueryHandlerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Satya on 31-07-2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HeadlineControllerTest {

    @InjectMocks
    HeadLineController headLineController;
    @Mock
    QueryHandlerService queryHandlerService;

    MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(headLineController)
                .build();
    }

    @Test
    public void test_getHeadlines() throws Exception {
        HeadLineDTO headLineDTO = new HeadLineDTO();
        headLineDTO.setStatus("ok");
        HeadLineCO headLineCO = new HeadLineCO();
        headLineCO.setCountry("us");
        when(queryHandlerService.getHeadLines(headLineCO)).thenReturn(headLineDTO);
        mockMvc.perform(post("/rest/v1/headlines").contentType(MediaType.APPLICATION_JSON).header("authorization", "Basic dXNlcjpwYXNz").header("cookie", "_xsrf=2|796f5b87|bf8899efe932fae0d6e16bcf427a430c|1531393564; username-localhost-8888=\"2|1:0|10:1531483986|23:username-localhost-8888|44:OGRmOTZhZjg3ZTdjNDdlNTgwOTUyNDgyMTE4ZTRiMDI=|ff357fac02b5b74b7b9277598588102f691c4efafe42d445f1a62fea4daa5541\"; username-localhost-8889=\"2|1:0|10:1531721726|23:username-localhost-8889|44:NGQyZjk1ZGU0ZDMzNGY1ZGFiZDI2NDVhYWI0ZTdmYzM=|5db2021a08e3ea38601e92ca66739ed2ca494cb25212468d303c8bf01d79c48d\"; _xsrf=2|796f5b87|bf8899efe932fae0d6e16bcf427a430c|1531393564; username-localhost-8888=\"2|1:0|10:1531483986|23:username-localhost-8888|44:OGRmOTZhZjg3ZTdjNDdlNTgwOTUyNDgyMTE4ZTRiMDI=|ff357fac02b5b74b7b9277598588102f691c4efafe42d445f1a62fea4daa5541\"; username-localhost-8889=\"2|1:0|10:1531721726|23:username-localhost-8889|44:NGQyZjk1ZGU0ZDMzNGY1ZGFiZDI2NDVhYWI0ZTdmYzM=|5db2021a08e3ea38601e92ca66739ed2ca494cb25212468d303c8bf01d79c48d\"; JSESSIONID=944E9462DBFE2C68FEDADA6BFE535058; _xsrf=2|796f5b87|bf8899efe932fae0d6e16bcf427a430c|1531393564; username-localhost-8888=\"2|1:0|10:1531483986|23:username-localhost-8888|44:OGRmOTZhZjg3ZTdjNDdlNTgwOTUyNDgyMTE4ZTRiMDI=|ff357fac02b5b74b7b9277598588102f691c4efafe42d445f1a62fea4daa5541\"; username-localhost-8889=\"2|1:0|10:1531721726|23:username-localhost-8889|44:NGQyZjk1ZGU0ZDMzNGY1ZGFiZDI2NDVhYWI0ZTdmYzM=|5db2021a08e3ea38601e92ca66739ed2ca494cb25212468d303c8bf01d79c48d\"; JSESSIONID=944E9462DBFE2C68FEDADA6BFE535058")
                .content(asJsonString(headLineCO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        verify(queryHandlerService, times(1)).getHeadLines(headLineCO);
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
