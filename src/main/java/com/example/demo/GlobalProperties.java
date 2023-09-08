package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class GlobalProperties {

    @Value("${global.datasource}")
    private String datasource;

    public List<Map<String, String>> getDatasource() {
        System.out.println(datasource);
        return Arrays.stream(datasource.split(","))
                .map(varPair -> {
                    String[] keyValue = varPair.trim().split("=");
                    if (keyValue.length == 2) {
                        return new HashMap<String, String>(){{
                            put("key", keyValue[0].trim());
                            put("value", keyValue[1].trim());
                        }};
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }
}
