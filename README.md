# **Expert System for Periodic Table Elements Identification**

This project combines Java and Prolog to create an expert system for identifying chemical elements based on various properties. The Java part handles input processing and file handling, while the Prolog part manages logical reasoning and querying capabilities. The system uses a knowledge base consisting of has_property(Element, PropertyLabel, PropertyValue) tripartite predicates to store element data.

---

## 📌 **Table of Contents**

- [Project Overview](#project-overview)  
- [Features](#features)  
- [Technologies Used](#technologies-used)  
- [System Architecture](#system-architecture)  
- [Setup & Installation](#setup--installation)  
- [Usage Instructions](#usage-instructions)  
- [Contribution Guidelines](#contribution-guidelines)  
- [License](#license)  

---

## 🚀 **Project Overview**

The ultimate goal of this project is to build an expert system in Prolog that can identify periodic table elements using user-provided **physical and chemical properties** (instead of solely relying on atomic numbers or mass). 

### 🛠️ **Why Java GUI?**
Directly working with Prolog's input is cumbersome and prone to errors. The Java GUI serves as an intermediary interface for the knowledge engineer:
1. Engineers input data about elements (e.g., properties like electronegativity, group, or period, etc.) into a user-friendly form.
2. The Java application converts these inputs into a **JSON file**.
3. This JSON is consumed by Prolog and loaded into its knowledge base.

A Java GUI also improves the experience for ordinary users:
1. Users input as many properties as desired or are available into a convenient form instead of Prolog's more abstruse querying system.
2. The Java application converts inputs into valid Prolog queries using the JPL library.
3. Query results are neatly outputted to the UI without the need for further interaction.

---

## ✨ **Features**


- **Graphical User Interface**:
  - Input chemical properties through a user-friendly GUI.
  - Allows for flexible property entry using text fields and combo boxes.

- **Knowledge Base**:
  - Stores information on 42 elements with predicates like `has_property/3`.
  - Easily expandable to include more elements and properties.

- **Prolog Integration**:
  - Performs logical reasoning and querying through Prolog.
  - Supports arrays and subsets of properties for more nuanced queries.

- **Data Handling**:
  - Uses JSON format to handle element property data.
  - Converts JSON data into Prolog format (`finalKB.pl` and `updatedKBAndQuery.pl`).

- **Efficient Querying**:
  - `GUIExpert` queries Prolog knowledge base to return intersections of results based on user input.
  - `finalExpert.pl` provides a pure Prolog solution for element identification.

- **Expandable Knowledge**:
  - Easily extends the knowledge base by adding new elements or properties.
  - Utilizes preprocessor classes to convert JSON into Prolog-friendly formats.

---

## 💻 **Technologies Used**

### **Java**
- Java Swing for GUI development.
- JPL for Java–Prolog integration
- Java libraries for JSON handling.

### **Prolog**
- The Prolog expert system will process the JSON data to identify elements based on logical inference.

---

## 🏗️ **System Architecture**

The system architecture of the Chemical Element Identification Expert System is structured into two main components: Java and Prolog, working together to provide a comprehensive solution for chemical element identification.

### 1. **Java Component**:
   - **ElementDataJSONer**:
     - Provides a graphical user interface (GUI) for inputting chemical properties.
     - Generates or updates `elementsandproperties.JSON`.
   - **ElementJSONReformatter2**:
     - Reformats the raw JSON data into `finalJSONforKB.json` for easier Prolog processing.
   - **JSONToPlPreprocessor2 & JSONToPlPreprocessor3**:
     - Converts `finalJSONforKB.json` into Prolog files: `finalKB.pl` and `updatedKBAndQuery.pl`.

### 2. **Prolog Component**:
   - **Prolog Knowledge Base**:
     - Contains facts in the form `has_property(Element, PropertyLabel, PropertyValue)` for 42 elements.
     - Supports complex properties such as arrays with predicates like `has_arrayed_property/3` and `has_property_array/3`.
   - **GUIExpert**:
     - Uses `updatedKBAndQuery.pl` to query the knowledge base for element identification based on user input.
     - Returns intersections of results from multiple queries to handle diverse properties.
   - **finalExpert.pl**:
     - A pure Prolog implementation for element identification, functioning similarly to `GUIExpert`.

### Overall Workflow:
1. **Java GUI**: User or knowledge engineer inputs chemical properties.
2. **Data Handling**: Data is processed, reformatted, and converted into Prolog knowledge base format.
3. **Prolog Reasoning**: Queries are executed using Prolog logic, and results are returned either through the GUI or a pure Prolog interface.

---

### **Clone the Repository**

```bash
git clone https://github.com/Leul-Alemayehu/META_IA_ExpertSys
cd META_IA_ExpertSys
```

---

### **Build & Run**

1. Open the project in your IDE.
2. Compile the application (or let your IDE handle this).
3. Run the Java GUI application.

---

## 🖥️ **Usage Instructions**

### Usage Instructions for Both Knowledge Engineer and Ordinary User

#### **For the Knowledge Engineer**:

**Goal**: Input and manage chemical element properties to expand or refine the knowledge base.

##### Steps:

1. **Prepare Element Data**:
   - **Run `ElementDataJSONer`**:  
     This class provides a GUI interface to input chemical properties of elements. It stores data in `elementsandproperties.JSON`.
     ```bash
     java ElementDataJSONer
     ```

2. **Reformat JSON**:
   - **Run `ElementJSONReformatter2`**:  
     Converts the raw JSON data in `elementsandproperties.JSON` into a more structured form suitable for Prolog processing (`finalJSONforKB.json`).
     ```bash
     java ElementJSONReformatter2
     ```

3. **Convert JSON to Prolog Knowledge Base**:
   - **Run `JSONToPlPreprocessor2`**:  
     Converts `finalJSONforKB.json` into a basic Prolog knowledge base (`finalKB.pl`). This can be pasted into `finalExpert.pl` by users interested in querying in "pure Prolog" without Java as an intermediary.
     ```bash
     java JSONToPlPreprocessor2
     ```

4. **Convert JSON to Prolog with Logic**:
   - **Run `JSONToPlPreprocessor3`**:  
     Converts `finalJSONforKB.json` into a knowledge base, adds logic to it and outputs `updatedKBAndQuery.pl` for use in both the GUI and pure Prolog environments.
     ```bash
     java JSONToPlPreprocessor3
     ```

---

#### **For the Ordinary User**:

**Goal**: Use the expert system to identify elements based on given properties through the GUI or pure Prolog interface.

##### Steps:

1. **Using GUIExpert**:
   - **Run `GUIExpert`**:  
     This class provides a graphical interface where the user can input chemical properties (e.g., atomic number, electronegativity) into text fields and combo boxes.
     ```bash
     java GUIExpert
     ```
   
2. **Input Properties**:
   - Use the GUI to enter as many property values as desired. Click the "Submit" button to generate results.
   
3. **Query Processing**:
   - `GUIExpert` queries `updatedKBAndQuery.pl` for each user input and returns the intersection of results from all queries.
   
---

**Optional Pure Prolog Usage**:
- **Run `finalExpert.pl`**:  
  For users preferring a text-based Prolog interface, `finalExpert.pl` functions similarly to `GUIExpert`, but without a graphical interface.
  ```bash
  swipl -f finalExpert.pl
  ```

---

### Summary:
- **Knowledge Engineer**: Focuses on managing and expanding the knowledge base using `ElementDataJSONer`, `JSONToPlPreprocessor2`, and `JSONToPlPreprocessor3`.
- **Ordinary User**: Uses the graphical interface (`GUIExpert`) or `finalExpert.pl` to query elements based on properties.

---

## 📜 **License**

This project is licensed under the [MIT License](LICENSE).  
Feel free to use, modify, and distribute!

---

## 🛠️ **Next Steps**

The current focus of the Java GUI is:
- Expanding the knowledge base to encompass all elements.
- Incorporating uncertainty handling mechanisms to deal with imprecise data.
- Enhancing the user interface to provide a more interactive and informative experience.

Once ready, the JSON data will seamlessly interface with the Prolog knowledge base for intelligent element identification based on physical/chemical properties.

---

## 🧠 **Final Thought**

The Prolog expert system represents the final goal — this Java intermediary GUI reduces complexity by enabling intuitive user interactions while ensuring data compatibility with logical inference mechanisms in Prolog.

Happy coding! 🚀

