import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.json.JSONArray;

public class ElementDataJSONer extends JFrame {

    // Fields for properties
    private JTextField nameField, symbolField, atomicNumberField, atomicMassField, stateOfMatterField, colorField,
            densityField, meltingPointField, boilingPointField,
            crystalStructureField, refractiveIndexField, oxidationStatesField, electronegativityField, electronConfigurationField,
            ionizationEnergyField, allotropicFormsField, isotopeCountField, flameTestColorField, hardnessField;

    private JComboBox<String> groupComboBox, periodComboBox, magnetismComboBox, reactivityComboBox, acidityBasicityComboBox, reactionWaterComboBox,
            reactionAcidsComboBox, electricalConductivityComboBox, thermalConductivityComboBox, reactionOxygenComboBox, radioactivityComboBox, corrosionResistanceComboBox, toxicityComboBox;

    private JButton saveButton, resetButton;

    public ElementDataJSONer() {
        setTitle("Element Properties Data Collector");
        setSize(800, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Disclaimer at the top
        JLabel disclaimerLabel = new JLabel("<html><i>Units: Ionization energy (eV), Temperatures (°C), Electronegativity (Allen scale), Density (g/cm³). Use \"N/A\" for any \"inapplicable\" text values.</i></html>");
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

        // Combine group panels into a single scrollable area
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(metaPropertiesPanel);
        inputPanel.add(physicalPropertiesPanel);
        inputPanel.add(chemicalPropertiesPanel);
        inputPanel.add(specialPropertiesPanel);

        JScrollPane scrollPane = new JScrollPane(inputPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Save Button
        saveButton = new JButton("Save to JSON");
        saveButton.addActionListener(e -> saveToJSON());

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
        nameField.setText("");
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
    }


    private JPanel createMetaPropertiesPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Meta Properties"));
        panel.setLayout(new GridLayout(4, 2, 5, 5)); // Adjusted rows for additional properties

        nameField = new JTextField();
        symbolField = new JTextField();

        // Adding combo boxes for Group and Period
        groupComboBox = new JComboBox<>();
        periodComboBox = new JComboBox<>();

        // Populate Group combo box (standard periodic table groups)
        for (int i = 1; i <= 18; i++) {
            groupComboBox.addItem(String.valueOf(i));
        }

        // Populate Period combo box (periods from 1 to 7)
        for (int i = 1; i <= 7; i++) {
            periodComboBox.addItem(String.valueOf(i));
        }

        panel.add(new JLabel("Name"));
        panel.add(nameField);

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
        electricalConductivityComboBox = new JComboBox<>(new String[]{"High", "Medium (semiconductor)", "Low (insulator)"});
        thermalConductivityComboBox = new JComboBox<>(new String[]{"High", "Low (insulator)"});
        crystalStructureField = new JTextField();
        refractiveIndexField = new JTextField();
        magnetismComboBox = new JComboBox<>(new String[]{"Paramagnetic", "Diamagnetic", "Ferromagnetic", "Not Applicable"});

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

        reactivityComboBox = new JComboBox<>(new String[]{"Highly Reactive", "Moderately Reactive", "Inert", "Not Applicable"});
        acidityBasicityComboBox = new JComboBox<>(new String[]{"Acidic", "Basic", "Amphoteric", "Not Applicable"});
        reactionWaterComboBox = new JComboBox<>(new String[]{"Vigorous", "Mild", "No Reaction", "Not Applicable"});
        reactionAcidsComboBox = new JComboBox<>(new String[]{"Produces Hydrogen", "No Reaction", "Not Applicable"});
        reactionOxygenComboBox = new JComboBox<>(new String[]{"Forms Oxides", "Does Not React", "Not Applicable"});
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

        panel.add(new JLabel("Oxidation States"));
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

        radioactivityComboBox = new JComboBox<>(new String[]{"Yes", "No"});
        isotopeCountField = new JTextField();
        flameTestColorField = new JTextField();
        toxicityComboBox = new JComboBox<>(new String[]{"Yes", "No"});
        corrosionResistanceComboBox = new JComboBox<>(new String[]{
                "Low General Reactivity",
                "Protective Oxide Layer",
                "Highly Resistant",
                "Moderately Resistant",
                "Low Resistance",
                "N/A"
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

    private void saveToJSON() {
        try {
            JSONObject newElement = new JSONObject();

            // Meta Properties
            newElement.put("name", nameField.getText());
            newElement.put("symbol", symbolField.getText());
            newElement.put("group", groupComboBox.getSelectedItem().toString());
            newElement.put("period", periodComboBox.getSelectedItem().toString());

            // Physical Properties
            newElement.put("atomicNumber", atomicNumberField.getText());
            newElement.put("atomicMass", atomicMassField.getText());
            newElement.put("stateOfMatter", stateOfMatterField.getText());
            newElement.put("color", convertToJSONArray(colorField.getText()));
            newElement.put("density", densityField.getText());
            newElement.put("meltingPoint", meltingPointField.getText());
            newElement.put("boilingPoint", boilingPointField.getText());
            newElement.put("electricalConductivity", electricalConductivityComboBox.getSelectedItem().toString());
            newElement.put("thermalConductivity", thermalConductivityComboBox.getSelectedItem().toString());
            newElement.put("crystalStructure", crystalStructureField.getText());
            newElement.put("refractiveIndex", refractiveIndexField.getText());
            newElement.put("magnetism", magnetismComboBox.getSelectedItem().toString());

            // Chemical Properties
            newElement.put("reactivity", reactivityComboBox.getSelectedItem().toString());
            newElement.put("oxideAcidityBasicity", acidityBasicityComboBox.getSelectedItem().toString());
            newElement.put("reactionWithWater", reactionWaterComboBox.getSelectedItem().toString());
            newElement.put("reactionWithAcids", reactionAcidsComboBox.getSelectedItem().toString());
            newElement.put("reactionWithOxygen", reactionOxygenComboBox.getSelectedItem().toString());
            newElement.put("oxidationStates", convertToJSONArray(oxidationStatesField.getText()));
            newElement.put("electronegativity", electronegativityField.getText());
            newElement.put("electronConfiguration", electronConfigurationField.getText());
            newElement.put("ionizationEnergy", ionizationEnergyField.getText());
            newElement.put("allotropeCount", allotropicFormsField.getText());

            // Special Properties
            newElement.put("radioactivity", radioactivityComboBox.getSelectedItem().toString());
            newElement.put("isotopeCount", isotopeCountField.getText());
            newElement.put("flameTestColor", flameTestColorField.getText());
            newElement.put("toxicity", toxicityComboBox.getSelectedItem().toString());
            newElement.put("corrosionResistance", corrosionResistanceComboBox.getSelectedItem().toString());
            newElement.put("hardness", hardnessField.getText());

            // Read existing file and append new data
            JSONArray elementsArray;
            try {
                String filePath = "workfiles/elementsandproperties.json";
                File file = new File(filePath);
                if (file.exists()) {
                    String content = new String(Files.readAllBytes(Paths.get(filePath)));
                    elementsArray = new JSONArray(content);
                } else {
                    elementsArray = new JSONArray();
                }
            } catch (IOException ex) {
                elementsArray = new JSONArray(); // If file read fails, create a new array
            }

            elementsArray.put(newElement);

            // Write updated data back to the file
            try (FileWriter file = new FileWriter("workfiles/elementsandproperties.json")) {
                file.write(elementsArray.toString(4)); // Pretty-print with 4 spaces
                file.flush();
            }

            JOptionPane.showMessageDialog(this, "Data saved successfully!");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // Helper to convert comma-separated values to JSONArray
    JSONArray convertToJSONArray(String input) {
        JSONArray jsonArray = new JSONArray();
        for (String value : input.split(",")) {
            jsonArray.put(value.trim());
        }
        return jsonArray;
    }

    public static void main(String[] args) {
        new ElementDataJSONer();
    }
}