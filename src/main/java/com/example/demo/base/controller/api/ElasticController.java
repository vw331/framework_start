package com.example.demo.base.controller.api;


import com.example.demo.base.service.ElasticService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.params.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/es")
public class ElasticController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String baseUrl = "http://hz3.starwrap.net:9200";
    private final String username = "elastic";
    private final String password = "02#c1417";

    private final String encodedCredentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    ElasticService esService;

    @PostMapping("/{indexs}")
    public ResponseEntity<Object> postQuery(@PathVariable("indexs") String indexs, @RequestBody Map<String, Object> queryMap)  {
        try {
            String queryJson = objectMapper.writeValueAsString(queryMap);
            Object res = esService.search(indexs, queryJson);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(res);
        }catch (Exception e){
            e.printStackTrace();
            // 处理异常
            return ResponseEntity.status(500).body("Error occurred");
        }
    }

}
