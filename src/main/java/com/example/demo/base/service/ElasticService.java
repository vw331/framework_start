package com.example.demo.base.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class ElasticService {
    @Resource
    RestTemplate restTemplate;

    public Object search(String indexs, String queryJson){
        String path = "/" + indexs + "/_search";
        HttpEntity<String> requestEntity = new HttpEntity<>(queryJson);
        ResponseEntity<Object> responseEntity = restTemplate.exchange(
                path,
                HttpMethod.POST,
                requestEntity,
                Object.class
        );
        return responseEntity.getBody();
    }

    // 获取一条数据
    public Object getDoc(String index, String id){
        String path = "/" + index + "/_doc/" + id;
        HttpEntity<Object> requestEntity = restTemplate.getForEntity(path, Object.class);
        return requestEntity.getBody();
    }

    // 获取mapping信息
    public Object getMapping(String index){
        return null;
    }

}
