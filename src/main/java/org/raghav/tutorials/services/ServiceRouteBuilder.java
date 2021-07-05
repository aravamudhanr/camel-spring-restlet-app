package org.raghav.tutorials.services;

import org.apache.camel.LoggingLevel;
import org.apache.camel.spring.SpringRouteBuilder;
import org.raghav.tutorials.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceRouteBuilder extends SpringRouteBuilder {

    @Autowired
    private ServiceConfiguration serviceConfiguration;

    @Override
    public void configure() throws Exception {

        /**
         * [ Route to fetch the configuration ]
         */
        from("restlet:/config?restletMethod=GET")
                .routeId(Constants.FETCH_CONFIGURATION_ROUTE)
                .log(LoggingLevel.INFO, Constants.FETCH_CONFIGURATION_ROUTE, "Invoking method to get the configuration")
                .bean(serviceConfiguration, "fetchMaskedConfiguration")
                .log(LoggingLevel.INFO, Constants.FETCH_CONFIGURATION_ROUTE, "Configuration retrieved successfully");

    }
}
