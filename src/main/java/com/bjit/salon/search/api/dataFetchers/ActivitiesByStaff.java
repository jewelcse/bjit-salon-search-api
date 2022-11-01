package com.bjit.salon.search.api.dataFetchers;

import com.bjit.salon.search.api.dto.Salon;
import com.bjit.salon.search.api.dto.Staff;
import com.bjit.salon.search.api.dto.StaffActivity;
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
public class ActivitiesByStaff implements DataFetcher<List<StaffActivity>> {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<StaffActivity> get(DataFetchingEnvironment environment) throws Exception {

        Staff staff = environment.getSource();

        String url = "http://localhost:9190/api/v1/staffs/{id}/activities";

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("id", String.valueOf(staff.getId()));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

        ResponseEntity<StaffActivity[]> catalogs = restTemplate.exchange(builder.buildAndExpand(urlParams).toUri(), HttpMethod.GET,entity, StaffActivity[].class);
        return List.of(Objects.requireNonNull(catalogs.getBody()));
    }
}
