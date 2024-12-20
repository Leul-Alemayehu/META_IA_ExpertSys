import javax.swing.*;
import java.awt.*;
import java.util.*;

import org.jpl7.*;

public class GUIExpert extends JFrame {

    private Map<String, String> propertyFieldMap;

    // Fields for properties
    private JTextField symbolField, atomicNumberField, atomicMassField, stateOfMatterField, colorField,
            densityField, meltingPointField, boilingPointField,
            crystalStructureField, refractiveIndexField, oxidationStatesField, electronegativityField, electronConfigurationField,
            ionizationEnergyField, allotropicFormsField, isotopeCountField, flameTestColorField, hardnessField, responseField;

    private JComboBox<String> groupComboBox, periodComboBox, magnetismComboBox, reactivityComboBox, acidityBasicityComboBox, reactionWaterComboBox,
            reactionAcidsComboBox, electricalConductivityComboBox, thermalConductivityComboBox, reactionOxygenComboBox, radioactivityComboBox, corrosionResistanceComboBox, toxicityComboBox;

    private JButton saveButton, resetButton;

    public GUIExpert() {
        setTitle("META: Element Querying Expert System");
        setSize(800, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Disclaimer at the top
        JLabel disclaimerLabel = new JLabel("<html><i>Units: Ionization energy (eV), Temperatures (°C), Electronegativity (Allen scale), Density (g/cm³). Put electron configuration in single quotes. Use \"N/A\" or leave field blank for unknown values, or values not considered.</i></html>");
        disclaimerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        disclaimerLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Group Panels
        JPanel metaPropertiesPanel = createMetaPropertiesPanel();
        JPanel physicalPropertiesPanel = createPhysicalPropertiesPanel();
        JPanel chemicalPropertiesPanel = createChemicalPropertiesPanel();
        JPanel specialPropertiesPanel = createSpecialPropertiesPanel();
        JPanel responsePanel = createResponsePanel();

        // Combine group panels into a single scrollable area
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(metaPropertiesPanel);
        inputPanel.add(physicalPropertiesPanel);
        inputPanel.add(chemicalPropertiesPanel);
        inputPanel.add(specialPropertiesPanel);
        inputPanel.add(responsePanel);

        JScrollPane scrollPane = new JScrollPane(inputPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Save Button
        saveButton = new JButton("Search");
        saveButton.addActionListener(e -> search());

        // Reset Button
        resetButton = new JButton("Reset Input Fields");
        resetButton.addActionListener(e -> resetFields());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(resetButton);

        add(disclaimerLabel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void resetFields() {
        // Reset all text fields
        symbolField.setText("");
        atomicNumberField.setText("");
        atomicMassField.setText("");
        stateOfMatterField.setText("");
        colorField.setText("");
        densityField.setText("");
        meltingPointField.setText("");
        boilingPointField.setText("");
        crystalStructureField.setText("");
        refractiveIndexField.setText("");
        oxidationStatesField.setText("");
        electronegativityField.setText("");
        electronConfigurationField.setText("");
        ionizationEnergyField.setText("");
        allotropicFormsField.setText("");
        isotopeCountField.setText("");
        flameTestColorField.setText("");
        hardnessField.setText("");

        // Reset combo boxes
        groupComboBox.setSelectedIndex(0);
        periodComboBox.setSelectedIndex(0);
        magnetismComboBox.setSelectedIndex(0);
        reactivityComboBox.setSelectedIndex(0);
        acidityBasicityComboBox.setSelectedIndex(0);
        reactionWaterComboBox.setSelectedIndex(0);
        reactionAcidsComboBox.setSelectedIndex(0);
        electricalConductivityComboBox.setSelectedIndex(0);
        thermalConductivityComboBox.setSelectedIndex(0);
        reactionOxygenComboBox.setSelectedIndex(0);
        radioactivityComboBox.setSelectedIndex(0);
        corrosionResistanceComboBox.setSelectedIndex(0);
        toxicityComboBox.setSelectedIndex(0);

        // Reset response area
        responseField.setText("");
    }


    private JPanel createMetaPropertiesPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Meta Properties"));
        panel.setLayout(new GridLayout(3, 2, 5, 5)); // Adjusted rows for additional properties

        symbolField = new JTextField();

        // Adding combo boxes for Group and Period
        groupComboBox = new JComboBox<>();
        periodComboBox = new JComboBox<>();

        // Populate Group combo box (standard periodic table groups)
        for (int i = 0; i <= 18; i++) {
            if (i == 0) groupComboBox.addItem("N/A");
            else groupComboBox.addItem(String.valueOf(i));
        }

        // Populate Period combo box (periods from 1 to 7)
        for (int i = 0; i <= 7; i++) {
            if (i == 0) periodComboBox.addItem("N/A");
            else periodComboBox.addItem(String.valueOf(i));
        }

        panel.add(new JLabel("Atomic Symbol"));
        panel.add(symbolField);

        panel.add(new JLabel("Group"));
        panel.add(groupComboBox);

        panel.add(new JLabel("Period"));
        panel.add(periodComboBox);

        return panel;
    }


    private JPanel createPhysicalPropertiesPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Physical Properties"));
        panel.setLayout(new GridLayout(12, 2, 5, 5));

        atomicNumberField = new JTextField();
        atomicMassField = new JTextField();
        stateOfMatterField = new JTextField();
        colorField = new JTextField();
        densityField = new JTextField();
        meltingPointField = new JTextField();
        boilingPointField = new JTextField();
        electricalConductivityComboBox = new JComboBox<>(new String[]{"N/A", "High", "Medium_(semiconductor)", "Low_(insulator)"});
        thermalConductivityComboBox = new JComboBox<>(new String[]{"N/A", "High", "Low_(insulator)"});
        crystalStructureField = new JTextField();
        refractiveIndexField = new JTextField();
        magnetismComboBox = new JComboBox<>(new String[]{"N/A", "Paramagnetic", "Diamagnetic", "Ferromagnetic"});

        panel.add(new JLabel("Atomic Number"));
        panel.add(atomicNumberField);

        panel.add(new JLabel("Atomic Mass"));
        panel.add(atomicMassField);

        panel.add(new JLabel("State of Matter"));
        panel.add(stateOfMatterField);

        panel.add(new JLabel("Color"));
        panel.add(colorField);

        panel.add(new JLabel("Density"));
        panel.add(densityField);

        panel.add(new JLabel("Melting Point"));
        panel.add(meltingPointField);

        panel.add(new JLabel("Boiling Point"));
        panel.add(boilingPointField);

        panel.add(new JLabel("Electrical Conductivity"));
        panel.add(electricalConductivityComboBox);

        panel.add(new JLabel("Thermal Conductivity"));
        panel.add(thermalConductivityComboBox);

        panel.add(new JLabel("Crystal Structure"));
        panel.add(crystalStructureField);

        panel.add(new JLabel("Refractive Index"));
        panel.add(refractiveIndexField);

        panel.add(new JLabel("Magnetism"));
        panel.add(magnetismComboBox);

        return panel;
    }

    private JPanel createChemicalPropertiesPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Chemical Properties"));
        panel.setLayout(new GridLayout(10, 2, 5, 5));

        reactivityComboBox = new JComboBox<>(new String[]{"N/A", "Highly_Reactive", "Moderately_Reactive", "Inert"});
        acidityBasicityComboBox = new JComboBox<>(new String[]{"N/A", "Acidic", "Basic", "Amphoteric"});
        reactionWaterComboBox = new JComboBox<>(new String[]{"N/A", "Vigorous", "Mild", "No_Reaction"});
        reactionAcidsComboBox = new JComboBox<>(new String[]{"N/A", "Produces_Hydrogen", "No_Reaction"});
        reactionOxygenComboBox = new JComboBox<>(new String[]{"N/A", "Forms_Oxides", "Does_Not_React"});
        oxidationStatesField = new JTextField();
        electronegativityField = new JTextField();
        electronConfigurationField = new JTextField();
        ionizationEnergyField = new JTextField();
        allotropicFormsField = new JTextField();

        panel.add(new JLabel("Reactivity"));
        panel.add(reactivityComboBox);

        panel.add(new JLabel("Acidity/Basicity of Oxide(s)"));
        panel.add(acidityBasicityComboBox);

        panel.add(new JLabel("Reaction with Water"));
        panel.add(reactionWaterComboBox);

        panel.add(new JLabel("Reaction with Acids"));
        panel.add(reactionAcidsComboBox);

        panel.add(new JLabel("Reaction with Oxygen"));
        panel.add(reactionOxygenComboBox);

        panel.add(new JLabel("Oxidation State(s)"));
        panel.add(oxidationStatesField);

        panel.add(new JLabel("Electronegativity"));
        panel.add(electronegativityField);

        panel.add(new JLabel("Electron Configuration"));
        panel.add(electronConfigurationField);

        panel.add(new JLabel("Ionization Energy"));
        panel.add(ionizationEnergyField);

        panel.add(new JLabel("Number of Allotropes"));
        panel.add(allotropicFormsField);

        return panel;
    }

    private JPanel createSpecialPropertiesPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Special Properties"));
        panel.setLayout(new GridLayout(6, 2, 5, 5));

        radioactivityComboBox = new JComboBox<>(new String[]{"N/A", "Yes", "No"});
        isotopeCountField = new JTextField();
        flameTestColorField = new JTextField();
        toxicityComboBox = new JComboBox<>(new String[]{"N/A", "Yes", "No"});
        corrosionResistanceComboBox = new JComboBox<>(new String[]{
                "N/A",
                "Low_General_Reactivity",
                "Protective_Oxide_Layer",
                "Highly_Resistant",
                "Moderately_Resistant",
                "Low_Resistance",
        });
        hardnessField = new JTextField();

        panel.add(new JLabel("Radioactivity"));
        panel.add(radioactivityComboBox);

        panel.add(new JLabel("Number of Isotopes"));
        panel.add(isotopeCountField);

        panel.add(new JLabel("Flame Test Color"));
        panel.add(flameTestColorField);

        panel.add(new JLabel("Toxicity"));
        panel.add(toxicityComboBox);

        panel.add(new JLabel("Corrosion Resistance"));
        panel.add(corrosionResistanceComboBox);

        panel.add(new JLabel("Hardness"));
        panel.add(hardnessField);

        return panel;
    }

    private JPanel createResponsePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Response"));
        panel.setLayout(new BorderLayout());

        // Create a non-editable text field
        responseField = new JTextField();
        responseField.setEditable(false); // Make it non-editable
        responseField.setBorder(BorderFactory.createEmptyBorder()); // Remove border
        responseField.setHorizontalAlignment(JTextField.CENTER);

        panel.add(responseField, BorderLayout.CENTER);

        return panel;
    }


    private void search() {
        try {
            // Consult the Prolog knowledge base
            String knowledgeBasePath = "C:/Users/leula/IdeaProjects/IA_Stuff/workfiles/updatedKBAndQuery.pl"; // REPLACE WITH YOUR updatedKBAndQuery.pl FILE LOCATION!
            Query consultQuery = new Query("consult('" + knowledgeBasePath + "')");

            // Check if knowledge base is loaded successfully
            if (consultQuery.hasSolution()) {
                System.out.println("Knowledge base loaded successfully.");
            } else {
                System.out.println("Failed to load knowledge base.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        propertyFieldMap = new HashMap<>();
        propertyFieldMap.put("symbol", symbolField.getText().toLowerCase());
        propertyFieldMap.put("group", groupComboBox.getSelectedItem().toString().toLowerCase());
        propertyFieldMap.put("period", periodComboBox.getSelectedItem().toString().toLowerCase());
        propertyFieldMap.put("atomicNumber", atomicNumberField.getText().toLowerCase());
        propertyFieldMap.put("atomicMass", atomicMassField.getText().toLowerCase());
        propertyFieldMap.put("stateOfMatter", stateOfMatterField.getText().toLowerCase());
        propertyFieldMap.put("color", colorField.getText().toLowerCase());
        propertyFieldMap.put("density", densityField.getText().toLowerCase());
        propertyFieldMap.put("meltingPoint", meltingPointField.getText().toLowerCase());
        propertyFieldMap.put("boilingPoint", boilingPointField.getText().toLowerCase());
        propertyFieldMap.put("crystalStructure", crystalStructureField.getText().toLowerCase());
        propertyFieldMap.put("refractiveIndex", refractiveIndexField.getText().toLowerCase());
        propertyFieldMap.put("electricalConductivity", electricalConductivityComboBox.getSelectedItem().toString().toLowerCase());
        propertyFieldMap.put("thermalConductivity", thermalConductivityComboBox.getSelectedItem().toString().toLowerCase());
        propertyFieldMap.put("magnetism", magnetismComboBox.getSelectedItem().toString().toLowerCase());
        propertyFieldMap.put("reactivity", reactivityComboBox.getSelectedItem().toString().toLowerCase());
        propertyFieldMap.put("oxideAcidityBasicity", acidityBasicityComboBox.getSelectedItem().toString().toLowerCase());
        propertyFieldMap.put("reactionWithWater", reactionWaterComboBox.getSelectedItem().toString().toLowerCase());
        propertyFieldMap.put("reactionWithAcids", reactionAcidsComboBox.getSelectedItem().toString().toLowerCase());
        propertyFieldMap.put("reactionWithOxygen", reactionOxygenComboBox.getSelectedItem().toString().toLowerCase());
        propertyFieldMap.put("oxidationStates", convertToJSONArray(oxidationStatesField.getText().toLowerCase()));
        propertyFieldMap.put("electronegativity", electronegativityField.getText().toLowerCase());
        propertyFieldMap.put("electronConfiguration", electronConfigurationField.getText().toLowerCase());
        propertyFieldMap.put("ionizationEnergy", ionizationEnergyField.getText().toLowerCase());
        propertyFieldMap.put("allotropicForms", allotropicFormsField.getText().toLowerCase());
        propertyFieldMap.put("radioactivity", radioactivityComboBox.getSelectedItem().toString().toLowerCase());
        propertyFieldMap.put("isotopeCount", isotopeCountField.getText().toLowerCase());
        propertyFieldMap.put("flameTestColor", flameTestColorField.getText().toLowerCase());
        propertyFieldMap.put("toxicity", toxicityComboBox.getSelectedItem().toString().toLowerCase());
        propertyFieldMap.put("corrosionResistance", corrosionResistanceComboBox.getSelectedItem().toString().toLowerCase());
        propertyFieldMap.put("hardness", hardnessField.getText().toLowerCase());

        Set<String> combinedResults = null;
        for (Map.Entry<String, String> entry : propertyFieldMap.entrySet()) {
            String propertyLabel = entry.getKey();
            String propertyValue = entry.getValue();

            if (propertyValue != null && !propertyValue.equals("N/A".toLowerCase()) && !propertyValue.trim().isEmpty() && !propertyValue.trim().equals("['']")){
                Set<String> queryResult = queryKB(propertyLabel, propertyValue);

                if (combinedResults == null) {
                    combinedResults = new HashSet<>(queryResult);
                } else {
                    combinedResults.retainAll(queryResult);  // Intersection
                }
            }
        }

        // Display results in the response field
        if (combinedResults.toString() != null && combinedResults.toString() != "[]") {
            responseField.setText(String.join(", ", combinedResults));
            System.out.println(combinedResults);
        } else {
            responseField.setText("No elements found matching selection.");  // No results
        }
    }

    private Set<String> queryKB(String propertyLabel, String propertyValue) {
        Set<String> results = new HashSet<>();

        if (propertyValue != null && !propertyValue.equals("N/A".toLowerCase()) && !propertyValue.trim().isEmpty() && !propertyValue.trim().equals("['']")) {
            try {
                if(Objects.equals(propertyLabel, "oxidationStates")){
                    String queryStr = String.format("has_property_array(X, %s, %s)", propertyLabel, propertyValue);
                    Query query = new Query(queryStr);

                    while (query.hasMoreSolutions()) {
                        Term solution = query.nextSolution().get("X");
                        results.add(solution.toString());
                    }
                } else if(Objects.equals(propertyLabel, "color")){
                    String queryStr = String.format("has_arrayed_property(X, %s, %s)", propertyLabel, propertyValue);
                    Query query = new Query(queryStr);

                    while (query.hasMoreSolutions()) {
                        Term solution = query.nextSolution().get("X");
                        results.add(solution.toString());
                    }
                } else{
                    String queryStr = String.format("has_property(X, %s, %s)", propertyLabel, propertyValue);
                    Query query = new Query(queryStr);

                    while (query.hasMoreSolutions()) {
                        Term solution = query.nextSolution().get("X");
                        results.add(solution.toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return results;
    }

    // Helper to convert comma-separated values to JSONArray
    String convertToJSONArray(String input) {
        StringBuilder jsonArrayString = new StringBuilder("[");
        for (String value : input.split(",")) {
            jsonArrayString.append("'").append(value.trim()).append("',");
        }
        if (jsonArrayString.length() > 1) {
            jsonArrayString.deleteCharAt(jsonArrayString.length() - 1);  // Remove the trailing comma
        }
        jsonArrayString.append("]");
        return jsonArrayString.toString();
    }

    public static void main(String[] args) {
        new GUIExpert();
    }
}