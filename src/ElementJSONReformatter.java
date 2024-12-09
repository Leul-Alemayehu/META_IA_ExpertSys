import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementJSONReformatter {
    public static void main(String[] args) {
        // Input and output file paths
        String inputFilePath = "workfiles/elementsandproperties.json";
        String outputFilePath = "workfiles/reformattedelementdata.json";

        try {
            // Create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON array from file into a List of Maps
            List<Map<String, Object>> elementsList = objectMapper.readValue(
                    new File(inputFilePath), new TypeReference<List<Map<String, Object>>>() {});

            // Transform the JSON structure
            Map<String, Map<String, Object>> reformattedData = new HashMap<>();
            for (Map<String, Object> element : elementsList) {
                String name = (String) element.get("name");
                element.remove("name"); // Remove "name" as it becomes the key
                reformattedData.put(name, element);
            }

            // Write the reformatted data to a new JSON file
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFilePath), reformattedData);

            System.out.println("Data reformatted successfully. Output saved to " + outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("An error occurred while processing the JSON file.");
        }
    }
}
