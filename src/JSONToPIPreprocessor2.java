import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class JSONToPIPreprocessor2 {
    public static void main(String[] args) {
        String inputFile = "workfiles/elementsandproperties2.json"; // Path to the input JSON file
        String outputFile = "workfiles/test_knowledge_base2.pl"; // Path to the output Prolog file

        try {
            // Step 1: Parse JSON, JUST EDITING SOME PARTS --EKA--
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Map<String, Object>>> elements = mapper.readValue(new File(inputFile), new TypeReference<List<Map<String, Map<String, Object>>>>() {});

            // Step 2: Open the output file
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            // Step 3: Process each element_property
            for (Map<String, Map<String, Object>> element_property : elements) {
                System.out.println(element_property.size() + "element_property Size");
                for (String element_name : element_property.keySet()) {
                    processElement(element_name, element_property.get(element_name), writer);
                }
            }
            writer.close();
            System.out.println("Prolog knowledge base created at: " + outputFile);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void processElement(String element_name, Map<String, Object> element_property ,BufferedWriter writer) throws IOException {

        writer.write("% Properties for: " + element_name + "\n");
        // Extract and process general properties

        int atomicNumber = Integer.parseInt((String) element_property.get("atomicNumber"));
        double atomicMass = Double.parseDouble((String) element_property.get("atomicMass"));

        writer.write(String.format("has_property(%s, %s, %d)\n", element_name, "atomicNumber",atomicNumber));
        writer.write(String.format("has_property(%s, %s, %.2f)\n", element_name, "atomicMass",atomicMass));

        // management of color list
        List<String> colors = (List<String>) element_property.get("color");
        StringBuilder colorListBuilder = new StringBuilder();
        colorListBuilder.append("[");
        for (String color : colors) {
            colorListBuilder.append(color);
            colorListBuilder.append(",");
        }
        colorListBuilder.deleteCharAt(colorListBuilder.length() - 1);
        colorListBuilder.append("]");
        String colorListString = colorListBuilder.toString();
        writer.write(String.format("has_property(%s, %s, %s)\n", element_name, "color",colorListString));

        // Write oxidation states
        List<String> oxidationStates = (List<String>) element_property.get("oxidationStates");
        writer.write(String.format("has_property(%s, %s, %s)\n", element_name, "oxidationStates",oxidationStates));

        // Store the whole String properties in Array
        String[] ele_property_names = {"symbol","stateOfMatter","density","meltingPoint", "boilingPoint","thermalConductivity",
                "electricalConductivity","crystalStructure","toxicity", "reactivity", "oxideAcidityBasicity","reactionWithOxygen","reactionWithWater",
                "reactionWithAcids","magnetism","flameTestColor", "isotopeCount","allotropeCount","corrosionResistance","refractiveIndex",
                "hardness","radioactivity","group", "period","ionizationEnergy","electronConfiguration","electronegativity",};

        // Write the repetitive properties from the Array above, with the help of for loop
        for(String ele_property_name: ele_property_names){
            writer.write(String.format("has_property(%s, %s, %s)\n", element_name, ele_property_name,formatAtom((String)element_property.get(ele_property_name))));
        }
        writer.write("\n");
    }

    private static String formatAtom(String value) {
        return value == null ? "n_a" : value.toLowerCase().replaceAll(" ", "_");
    }
}
