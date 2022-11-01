package com.bjit.salon.search.api.service;


import com.bjit.salon.search.api.dataFetchers.*;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {

    @Value("classpath:salon.graphql")
    Resource resource;

    private GraphQL graphQL;

    @Autowired
    private AllSalonDataFetcher allSalonDataFetcher;
    @Autowired
    private SalonDataFetcher salonDataFetcher;
    @Autowired
    private CatalogDataFetcher catalogDataFetcher;

    @Autowired
    private CatalogsBySalon catalogsBySalon;
    @Autowired
    private StaffsBySalon staffsBySalon;

    @Autowired
    private ActivitiesByStaff staffActivities;

    @Autowired
    private StaffDataFetcher staffDataFetcher;

    @PostConstruct
    private void loadSchema() throws IOException {

        // get the schema
        File schemaFile = resource.getFile();
        // parse schema
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring wiring = buildRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {

        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allSalon", allSalonDataFetcher)
                        .dataFetcher("salon", salonDataFetcher)
                        .dataFetcher("salonCatalogs", catalogDataFetcher)
                        .dataFetcher("salonStaffs", staffDataFetcher))
                .type("Salon", typeWiring -> typeWiring
                        .dataFetcher("services", catalogsBySalon))
                .type("Salon", typeWiring -> typeWiring
                        .dataFetcher("staffs", staffsBySalon))
                .type("Staff", typeWiring -> typeWiring
                        .dataFetcher("activities", staffActivities))
                .build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }


}
