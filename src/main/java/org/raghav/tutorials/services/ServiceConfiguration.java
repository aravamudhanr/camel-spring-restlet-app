package org.raghav.tutorials.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import org.raghav.tutorials.model.Config;
import org.raghav.tutorials.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.naming.ConfigurationException;
import java.io.IOException;
import java.net.URL;

@ParametersAreNonnullByDefault
public class ServiceConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceConfiguration.class);

    private ObjectMapper mapper;
    private Config configuration;
    private URL schemaFileLocation;
    private String jsonDataLocation;
    private URL updateConfigSchemaFilelocation;

    /**
     * [ Default Constructor ]
     *
     * @throws ConfigurationException
     * @throws IOException
     */
    public ServiceConfiguration() throws ConfigurationException, IOException {
        this(ServiceConfiguration.class.getClassLoader().getResource("ConfigSchema.json"),
                ServiceConfiguration.class.getClassLoader().getResource("ConfigUpdateSchema.json"),
                ServiceConfiguration.class.getClassLoader().getResource("Config.json").getPath());

        configuration = fetchConfigObject();
    }

    /**
     * [ Argument Constructor ]
     *
     * @param schemaFileLocation
     * @param updateConfigSchemaFilelocation
     * @param jsonDataLocation
     */
    public ServiceConfiguration(URL schemaFileLocation, URL updateConfigSchemaFilelocation, String jsonDataLocation) {
        Preconditions.checkNotNull(schemaFileLocation, "schemaFileLocation must not be null");
        Preconditions.checkNotNull(jsonDataLocation, "jsonDataLocation must not be null");
        Preconditions.checkNotNull(updateConfigSchemaFilelocation, "updateConfigSchemaFilelocation must not be null");

        this.jsonDataLocation = jsonDataLocation;
        this.schemaFileLocation = schemaFileLocation;
        this.updateConfigSchemaFilelocation = updateConfigSchemaFilelocation;
        mapper = new ObjectMapper();
    }

    /**
     * [ Method to fetch current config object ]
     *
     * @return
     * @throws ConfigurationException
     * @throws IOException
     */
    public Config fetchConfigObject() throws ConfigurationException, IOException {
        return mapper.treeToValue(
                marshallToJsonNode(Utility.fetchCurrentConfiguration(jsonDataLocation, mapper), mapper), Config.class);
    }

    /**
     * [ Method to convert String to jsonNode object ]
     *
     * @param jsonData
     *            [ Json data in String format ]
     * @return - JsonNode object
     * @throws IOException
     */
    protected JsonNode marshallToJsonNode(String jsonData, ObjectMapper mapper) throws IOException {
        Preconditions.checkNotNull(jsonData, "jsonData must not be null");
        return mapper.readTree(jsonData);
    }

    /**
     * [ Method to fetch masked configuration ]
     *
     * @return masked Configuration
     * @throws ConfigurationException
     * @throws IOException
     */
    public String fetchMaskedConfiguration() throws ConfigurationException, IOException {
        String jsonString = Utility.fetchCurrentConfiguration(jsonDataLocation, mapper);
        JsonNode rootNode = mapper.readTree(jsonString);

        Config config = mapper.treeToValue(rootNode, Config.class);
        config.setServerUserName(Utility.getMasked(config.getServerUserName()));
        config.setServerPassword(Utility.getMasked(config.getServerPassword()));
        rootNode = mapper.convertValue(config, JsonNode.class);
        jsonString = Utility.getJsonNodeAsString(rootNode, mapper);
        LOGGER.info("configuration :- " + jsonString);
        return jsonString;
    }

}