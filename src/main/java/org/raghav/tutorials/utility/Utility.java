package org.raghav.tutorials.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.ParametersAreNonnullByDefault;
import javax.naming.ConfigurationException;
import java.io.File;
import java.io.IOException;

@ParametersAreNonnullByDefault
public class Utility {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utility.class);

    /**
     * [ Method to get the current configuration properties from file ]
     *
     * @param mapper
     *
     * @return [ current configuraiton properties as a string ]
     * @throws ConfigurationException
     * @throws IOException
     */
    public static String fetchCurrentConfiguration(String jsonDataLocation, ObjectMapper mapper)
            throws ConfigurationException, IOException {
        Preconditions.checkNotNull(jsonDataLocation, "jsonDataLocation must not be null");

        File file = new File(jsonDataLocation);
        JsonNode rootNode = mapper.readTree(file);
        return getJsonNodeAsString(rootNode, mapper);
    }

    /**
     * [ Method to get json node as string ]
     *
     * @param jsonNode
     * @return
     * @throws JsonProcessingException
     */
    public static String getJsonNodeAsString(JsonNode jsonNode, ObjectMapper mapper) throws JsonProcessingException {
        Preconditions.checkNotNull(jsonNode, "jsonNode must not be null");

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
    }

    /**
     * [ Method to mask the value ]
     *
     * @param data
     *            [ Data to be masked ]
     * @return [ Masked data as a string ]
     */
    public static String getMasked(String data) {
        Preconditions.checkNotNull(data, "data must not be null");

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.length(); i++)
            stringBuilder.append('*');
        return stringBuilder.toString();
    }
}
