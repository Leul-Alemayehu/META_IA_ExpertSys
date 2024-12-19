%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
ask_meta_properties(ElementAnswer):-
    write('Do you want to answer Meta Properties of the Element? (yes/no)'), read(Respond),
    proceed(Respond, ask_general_questions, ElementAnswer).

ask_physical_properties(ElementAnswer):-
    write('Do you want to answer Physical Properties of the Element? (yes/no)'), read(Respond),
    proceed(Respond, ask_physical_properties, ElementAnswer).

ask_chemical_properties(ElementAnswer):-
    write('Do you want to answer Chemical Properties of the Element? (yes/no)'), read(Respond),
    proceed(Respond, ask_chemical_properties, ElementAnswer).

ask_special_properties(ElementAnswer):-
    write('Do you want to answer Special Properties of the Element? (yes/no)'), read(Respond),
    proceed(Respond, ask_special_properties, ElementAnswer).

proceed(no, _, ElementAnswer):-
    ElementAnswer = [].

proceed(yes, ask_general_questions, ElementAnswer):-
    write('What is the Symbol of the Element? '), read(Symbol),
    write('What is the Group Number of the Element? '), read(GroupNumber),
    write('What is the Period Number of the Element? '), read(PeriodNumber),
    % Computation would start here taking the Previous ElementAnswer in consideration
    ElementAnswer = [symbol(Symbol), group_number(GroupNumber), period_number(PeriodNumber)].

proceed(yes, ask_physical_properties, ElementAnswer):-
    write('What is the Atomic Number of the Element? '), read(AtomicNumber),
    write('What is the Mass Number of the Element? '), read(MassNumber),
    write('In which state does the Element Occur at STP? '), read(StateOfMatter),
    write('Which colors can the Element exhibit (store them in an Array)? '), read(Colors),
    write('What is the Density of the Element? '), read(Density),
    write('What is the Melting Point of the Element? '), read(MeltingPoint),
    write('What is the Boiling Point of the Element? '), read(BoilingPoint),
    write('What is the Thermal Conductivity of the Element? '), read(ThermalConductivity),
    write('What is the Electrical Conductivity of the Element? '), read(ElectricalConductivity),
    write('What is the Crystal Structure of the Element? '), read(CrystalStructure),
    % Computation would start here taking the Previous ElementAnswer in consideration
    ElementAnswer = [atomic_number(AtomicNumber), mass_number(MassNumber), state_of_matter(StateOfMatter), colors(Colors), density(Density), melting_point(MeltingPoint), boiling_point(BoilingPoint), thermal_conductivity(ThermalConductivity), electrical_conductivity(ElectricalConductivity), crystal_structure(CrystalStructure)].

proceed(yes, ask_chemical_properties, ElementAnswer) :-
    write('What is the reactivity of the element? (highly_reactive, moderately_reactive, inert, not_applicable) '), read(Reactivity),
    write('Is the oxide of the element acidic, basic, amphoteric, or not applicable? '), read(OxideAcidityBasicity),
    write('How does the element react with water? (vigorous, mild, no_reaction, not_applicable) '), read(ReactionWithWater),
    write('How does the element react with acids? (produces_hydrogen, no_reaction, not_applicable) '), read(ReactionWithAcids),
    write('How does the element react with oxygen? (forms_oxides, does_not_react, not_applicable) '), read(ReactionWithOxygen),
    write('What are the oxidation states of the element? (e.g., +1, -1 etc) '), read(OxidationStates),
    write('What is the electronegativity of the element? '), read(Electronegativity),
    write('What is the electron configuration of the element? (e.g., [1s2, 2s2, 2p6]) '), read(ElectronConfiguration),
    write('What is the ionization energy of the element? '), read(IonizationEnergy),
    write('How many allotropes does the element have? '), read(AllotropeCount),
    % Computation would start here taking the Previous ElementAnswer in consideration
    ElementAnswer = [reactivity(Reactivity), oxide_acidity_basicity(OxideAcidityBasicity), reaction_with_water(ReactionWithWater), reaction_with_acids(ReactionWithAcids), reaction_with_oxygen(ReactionWithOxygen), oxidation_states(OxidationStates), electronegativity(Electronegativity), electron_configuration(ElectronConfiguration), ionization_energy(IonizationEnergy), allotrope_count(AllotropeCount)].

proceed(yes, ask_special_properties, ElementAnswer) :-
    write('Is the element radioactive? (yes/no) '), read(Radioactivity),
    write('How many stable isotopes does the element have? '), read(IsotopeCount),
    write('What is the flame test color of the element? '), read(FlameTestColor),
    write('Is the element toxic? (yes/no) '), read(Toxicity),
    write('How resistant is the element to corrosion? (high, medium, low) '), read(CorrosionResistance),
    write('What is the hardness of the element on the Mohs scale? '), read(Hardness),
    % Computation would start here taking the Previous ElementAnswer in consideration
    ElementAnswer = [radioactive(Radioactivity), isotope_count(IsotopeCount), flame_test_color(FlameTestColor), toxicity(Toxicity), corrosion_resistance(CorrosionResistance), hardness(Hardness)].
