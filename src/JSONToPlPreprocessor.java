import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class JSONToPlPreprocessor {

    public static void main(String[] args) {
        String inputFile = "workfiles/elementsandproperties.json"; // Path to the input JSON file
        String outputFile = "workfiles/test_knowledge_base.pl"; // Path to the output Prolog file

        try {
            // Step 1: Parse JSON
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> elements = mapper.readValue(new File(inputFile),
                    new TypeReference<List<Map<String, Object>>>() {});

            // Step 2: Open the output file
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            // Step 3: Process each element
            for (Map<String, Object> element : elements) {
                processElement(element, writer);
            }

            writer.close();
            System.out.println("Prolog knowledge base created at: " + outputFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processElement(Map<String, Object> element, BufferedWriter writer) throws IOException {
        // Extract and process general properties
        String name = (String) element.get("name");
        String symbol = (String) element.get("symbol");
        int atomicNumber = Integer.parseInt((String) element.get("atomicNumber"));
        double atomicMass = Double.parseDouble((String) element.get("atomicMass"));
        String stateOfMatter = (String) element.get("stateOfMatter");

        // management of color list
        List<String> colors = (List<String>) element.get("color");
        StringBuilder colorListBuilder = new StringBuilder();
        colorListBuilder.append("[");
        for (String color : colors) {
            colorListBuilder.append(color);
            colorListBuilder.append(",");
        }
        colorListBuilder.deleteCharAt(colorListBuilder.length() - 1);
        colorListBuilder.append("]");
        String colorListString = colorListBuilder.toString();

        // Write metadata
        writer.write("% Properties for: " + name + "\n");

        // Write atomic properties
        writer.write(String.format("atomic_properties(%s, atomic_number(%d), atomic_symbol(%s), atomic_mass(%.2f)).\n",
                formatAtom(name), atomicNumber, formatAtom(symbol), atomicMass));

        // Write oxidation states
        List<String> oxidationStates = (List<String>) element.get("oxidationStates");
        writer.write(String.format("oxidation_states(%s, %s).\n",
                formatAtom(name), oxidationStates.toString().replace("[", "[").replace("]", "]")));

        // Write physical properties
        writer.write(String.format("physical_properties(%s, [state(%s), color(%s), density(%s), melting_point(%s), boiling_point(%s), ",
                formatAtom(name), formatAtom(stateOfMatter), colorListString, element.get("density"), element.get("meltingPoint"), element.get("boilingPoint")));
        writer.write(String.format("thermal_conductivity(%s), electrical_conductivity(%s), crystal_structure(%s)]).\n",
                formatAtom((String) element.get("thermalConductivity")),
                formatAtom((String) element.get("electricalConductivity")),
                formatAtom((String) element.get("crystalStructure"))));

        // Write chemical properties
        writer.write(String.format("chemical_properties(%s, [toxicity(%s), reactivity(%s), oxide_acidity_basicity(%s), ",
                formatAtom(name), formatAtom((String) element.get("toxicity")),
                formatAtom((String) element.get("reactivity")),
                formatAtom((String) element.get("oxideAcidityBasicity"))));
        writer.write(String.format("reaction_with_oxygen(%s), reaction_with_water(%s), reaction_with_acids(%s), magnetism(%s)]).\n",
                formatAtom((String) element.get("reactionWithOxygen")),
                formatAtom((String) element.get("reactionWithWater")),
                formatAtom((String) element.get("reactionWithAcids")),
                formatAtom((String) element.get("magnetism"))));

        // Write additional properties
        writer.write(String.format("miscellaneous_properties(%s, [flame_test_color(%s), isotope_count(%s), ",
                formatAtom(name), formatAtom((String) element.get("flameTestColor")),
                element.get("isotopeCount")));
        writer.write(String.format("allotrope_count(%s), corrosion_resistance(%s), refractive_index(%s), hardness(%s), ",
                element.get("allotropeCount"),
                formatAtom((String) element.get("corrosionResistance")),
                element.get("refractiveIndex"),
                formatAtom((String) element.get("hardness"))));

        // Write other general chemical properties
        writer.write(String.format("radioactivity(%s), group(%s), period(%s), ionization_energy(%s), electron_configuration(\'%s\'), electronegativity(%s)]).\n",
                formatAtom((String) element.get("radioactivity")),
                formatAtom((String) element.get("group")),
                formatAtom((String) element.get("period")),
                element.get("ionizationEnergy"),
                formatAtom((String) element.get("electronConfiguration")), element.get("electronegativity")));

        writer.write("\n");
    }

    private static String formatAtom(String value) {
        return value == null ? "n_a" : value.toLowerCase().replaceAll(" ", "_");
    }
}
