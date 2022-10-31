package com.bjit.salon.search.api.dataFetchers;


import com.bjit.salon.search.api.dto.Catalog;
import com.bjit.salon.search.api.dto.Salon;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Component
public class CatalogsBySalon implements DataFetcher<List<Catalog>> {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public List<Catalog> get(DataFetchingEnvironment environment) throws Exception {

        Salon salon = environment.getSource();

        String url = "http://localhost:9090/api/v1/salons/{id}/catalogs";

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("id", String.valueOf(salon.getId()));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        ResponseEntity<Catalog[]> catalogs = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET,entity, Catalog[].class);
        return List.of(Objects.requireNonNull(catalogs.getBody()));
    }
}
