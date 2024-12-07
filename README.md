# **Expert System for Periodic Table Elements Identification**

This project serves as an intermediary system that facilitates the creation of a Prolog-based expert system. The Prolog system identifies elements based on user-provided physical and chemical properties. To streamline the user experience, a Java-based GUI is implemented as an intermediary step. This GUI allows users to input element properties conveniently and exports the data as a JSON file, which is then ingested into the Prolog knowledge base.

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
Directly working with Prolog's input is cumbersome and prone to errors. The Java GUI serves as an intermediary interface:
1. Users input data about elements (e.g., properties like electronegativity, group, or period, etc.) into a user-friendly form.
2. The Java application converts these inputs into a **JSON file**.
3. This JSON is consumed by Prolog and loaded into its knowledge base.

---

## ✨ **Features**

The Java GUI application provides the following features:
- **User Input Form**: Input various physical and chemical properties related to periodic table elements.
- **Combo-box Selection**: Choose element properties (e.g., Group and Period values) from dropdown menus.
- **Data Export**: Save user inputs into a JSON format compatible with Prolog knowledge base ingestion.
- **Reset Input Fields**: Clear the fields to input new data easily without restarting the application.
- **JSON Generation**: Data exported is in a structured JSON format, ready for use in the Prolog expert system.

---

## 💻 **Technologies Used**

### **Java**
- Java Swing for GUI development.
- Java libraries for JSON handling.

### **Prolog**
- The Prolog expert system will process the JSON data to identify elements based on logical inference.

---

## 🏗️ **System Architecture**

The system consists of:
1. **Java GUI Application**:
   - Provides a user-friendly interface to input element properties.
   - Includes fields with drop-down lists (combo boxes) for common properties such as **Group** and **Period** values.
   - A "reset" button clears inputs for reuse.

2. **JSON Serialization**:
   - Input data is exported as a JSON file.
   - This JSON file serves as the bridge between Java and the Prolog knowledge base.

3. **Prolog Knowledge Base**:
   - The final system component, which will read this JSON file and act as the expert system logic engine.

---

## ⚙️ **Setup & Installation**

### Prerequisites
1. **Java Development Environment**: Ensure Java is installed on your system.
   - Download Java from [Oracle's official site](https://www.oracle.com/java/technologies/javase-downloads.html).
2. **IDE**: Use an IDE like IntelliJ IDEA, NetBeans, or Eclipse for a smooth development experience.

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

1. **Launch the Application**:
   - Open the GUI through your IDE or compiled Java binary.

2. **Fill in the Fields**:
   - Input the element's properties like electronegativity, chemical symbols, etc., through the GUI's form fields.

3. **Select Options**:
   - Use dropdown combo boxes for "Group" and "Period" values.

4. **Export JSON**:
   - Once input is complete, click the **Save/Export** button. This will generate a `data.json` file.

5. **Use with Prolog**:
   - Load the generated JSON file into the Prolog knowledge base to run inference.

---

## 📜 **License**

This project is licensed under the [MIT License](LICENSE).  
Feel free to use, modify, and distribute!

---

## 🛠️ **Next Steps**

The current focus of the Java GUI is:
- Complete the input form functionality.
- Implement proper serialization into JSON.
- Enable integration with Prolog by transforming user-provided JSON into a Prolog-compatible format.

Once ready, the JSON data will seamlessly interface with the Prolog knowledge base for intelligent element identification based on physical/chemical properties.

---

## 🧠 **Final Thought**

The Prolog expert system represents the final goal — this Java intermediary GUI reduces complexity by enabling intuitive user interactions while ensuring data compatibility with logical inference mechanisms in Prolog.

Happy coding! 🚀

