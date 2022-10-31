package com.bjit.salon.search.api.dataFetchers;

import com.bjit.salon.search.api.dto.Salon;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AllSalonDataFetcher implements DataFetcher<List<Salon>> {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Salon> get(DataFetchingEnvironment environment) throws Exception {
        String url = "http://localhost:9090/api/v1/salons";
        Salon[] salon = restTemplate.getForObject(url,Salon[].class);
        return Arrays.asList(salon);
    }
}
