package com.bjit.salon.search.api.dataFetchers;


import com.bjit.salon.search.api.dto.Catalog;
import com.bjit.salon.search.api.dto.Staff;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class StaffDataFetcher implements DataFetcher<List<Staff>> {
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public List<Staff> get(DataFetchingEnvironment environment) throws Exception {

        String salonId = environment.getArgument("salonId");

        String url = "http://localhost:9190/api/v1/salons/{id}/staffs";

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("id", salonId);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        ResponseEntity<Staff[]> catalogs = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET,entity, Staff[].class);
        return List.of(Objects.requireNonNull(catalogs.getBody()));

    }
}
