%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% there is a problem in ThermalConductivity:
% it instead use the atom value with the predicate
% low_(insulator). Need to be changed to just insulator, conductor, etc.
% wasn't checked
% instead of fail after PossElement == [], we could write
%some kind of msg.
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%% KNOWLEDGE BASE FROM test_knowledge_base2.pl%%%%%%%%%%%%

% Properties for: Hydrogen
has_property(hydrogen, atomicNumber, 1).
has_property(hydrogen, atomicMass, 1.00).
has_property(hydrogen, color, [colorless]).
has_property(hydrogen, oxidationStates, [-1, +1]).
has_property(hydrogen, symbol, h).
has_property(hydrogen, stateOfMatter, gas).
has_property(hydrogen, density, 0.00008988).
has_property(hydrogen, meltingPoint, -259.34).
has_property(hydrogen, boilingPoint, -252.87).
has_property(hydrogen, thermalConductivity, low_(insulator)).
has_property(hydrogen, electricalConductivity, low_(insulator)).
has_property(hydrogen, crystalStructure, hcp).
has_property(hydrogen, toxicity, no).
has_property(hydrogen, reactivity, highly_reactive).
has_property(hydrogen, oxideAcidityBasicity, amphoteric).
has_property(hydrogen, reactionWithOxygen, forms_oxides).
has_property(hydrogen, reactionWithWater, no_reaction).
has_property(hydrogen, reactionWithAcids, no_reaction).
has_property(hydrogen, magnetism, diamagnetic).
has_property(hydrogen, flameTestColor, pale_blue).
has_property(hydrogen, isotopeCount, 3).
has_property(hydrogen, allotropeCount, 2).
has_property(hydrogen, corrosionResistance, n/a).
has_property(hydrogen, refractiveIndex, 1).
has_property(hydrogen, hardness, n/a).
has_property(hydrogen, radioactivity, no).
has_property(hydrogen, group, 1).
has_property(hydrogen, period, 1).
has_property(hydrogen, ionizationEnergy, 13.598).
has_property(hydrogen, electronegativity, 2.3).
has_property(hydrogen, electronConfiguration, '1s1').

% Properties for: Helium
has_property(helium, atomicNumber, 2).
has_property(helium, atomicMass, 4.00).
has_property(helium, color, [colorless]).
has_property(helium, oxidationStates, [0]).
has_property(helium, symbol, he).
has_property(helium, stateOfMatter, gas).
has_property(helium, density, 0.0001785).
has_property(helium, meltingPoint, -272.2).
has_property(helium, boilingPoint, -268.93).
has_property(helium, thermalConductivity, low_(insulator)).
has_property(helium, electricalConductivity, low_(insulator)).
has_property(helium, crystalStructure, hcp).
has_property(helium, toxicity, no).
has_property(helium, reactivity, inert).
has_property(helium, oxideAcidityBasicity, not_applicable).
has_property(helium, reactionWithOxygen, does_not_react).
has_property(helium, reactionWithWater, no_reaction).
has_property(helium, reactionWithAcids, no_reaction).
has_property(helium, magnetism, diamagnetic).
has_property(helium, flameTestColor, n/a).
has_property(helium, isotopeCount, 9).
has_property(helium, allotropeCount, 3).
has_property(helium, corrosionResistance, low_general_reactivity).
has_property(helium, refractiveIndex, 1).
has_property(helium, hardness, n/a).
has_property(helium, radioactivity, no).
has_property(helium, group, 2).
has_property(helium, period, 1).
has_property(helium, ionizationEnergy, 24.587).
has_property(helium, electronegativity, 4.16).
has_property(helium, electronConfiguration, '1s2').



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%%%%%%%%%%%%%%%%% RULE DEFINITION AND SUCH %%%%%%%%%%%%%%%%%%%%%%%


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
% check color membership of user Read colors to the Knowledge base Element Colors

check_all_color([], _):-!.
check_all_color([H|T], ElementColors):-
    member(H, ElementColors),
    check_all_color(T, ElementColors).

proceed(no, _, ElementAnswer):-
    ElementAnswer = [].

proceed(yes, ask_general_questions, ElementAnswer):-
    write('What is the Symbol of the Element? '), read(Symbol),
    write('What is the Atomic Number of the Element? '), read(AtomicNumber),
    write('What is the Group Number of the Element? '), read(GroupNumber),
    write('What is the Period Number of the Element? '), read(PeriodNumber),
    % Computation would start here taking the Previous ElementAnswer in consideration

   findall(Element, (has_property(Element, group,GroupNumber),
         has_property(Element, period, PeriodNumber),has_property(Element, symbol, Symbol),
       has_property(Element, atomicNumber, AtomicNumber)), PossElement),
       (PossElement == [] -> !,fail;
            write('The Element is '), write(PossElement), nl).

proceed(yes, ask_physical_properties, ElementAnswer):-

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
    findall(Element, (    has_property(Element, atomicMass, MassNumber),
                          has_property(Element, stateOfMatter, StateOfMatter),
                          has_property(Element, color, ElementColors),
                          check_all_color(Colors, ElementColors),
                          has_property(Element, density, Density),
                          has_property(Element, meltingPoint, MeltingPoint),
                          has_property(Element, boilingPoint, BoilingPoint),
                          has_property(Element, thermalConductivity, ThermalConductivity),
                          has_property(Element, electricalConductivity, ElectricalConductivity),
                          has_property(Element, crystalStructure,CrystalStructure)), PossElement),
    (PossElement == [] -> !,fail;
                write('The Element is '), write(PossElement), nl).



proceed(yes, ask_chemical_properties, ElementAnswer) :-
    write('What is the reactivity of the element? (highly_reactive, moderately_reactive, inert, not_applicable) '), read(Reactivity),
    write('Is the oxide of the element acidic, basic, amphoteric, or not applicable? '), read(OxideAcidityBasicity),
    write('How does the element react with water? (vigorous, mild, no_reaction, not_applicable) '), read(ReactionWithWater),
    write('How does the element react with acids? (produces_hydrogen, no_reaction, not_applicable) '), read(ReactionWithAcids),
    write('How does the element react with oxygen? (forms_oxides, does_not_react, not_applicable) '), read(ReactionWithOxygen),
    write('What are the oxidation states of the element? (e.g., [+1,-1], [+2] etc) '), read(OxidationStates),
    write('What is the electronegativity of the element? '), read(Electronegativity),
    write('What is the electron configuration of the element? (e.g., [1s2, 2s2, 2p6]) '), read(ElectronConfiguration),
    write('What is the ionization energy of the element? '), read(IonizationEnergy),
    write('How many allotropes does the element have? '), read(AllotropeCount),
    % Computation would start here taking the Previous ElementAnswer in consideration
    findall(Element, (has_property(Element,reactivity,Reactivity ),
    has_property(Element, oxideAcidityBasicity ,OxideAcidityBasicity),
    has_property(Element, reactionWithWater,ReactionWithWater),
    has_property(Element, reactionWithAcids,ReactionWithAcids),
    has_property(Element, reactionWithOxygen,ReactionWithOxygen),
    has_property(Element, oxidationStates,OxidationStates),
    has_property(Element, electronegativity ,Electronegativity),
    has_property(Element, ionizationEnergy,IonizationEnergy),
    has_property(Element,allotropeCount ,AllotropeCount)), PossElement),
    (PossElement == [] -> !,fail;
                write('The Element is '), write(PossElement), nl).

proceed(yes, ask_special_properties, ElementAnswer) :-
    write('Is the element radioactive? (yes/no) '), read(Radioactivity),
    write('How many stable isotopes does the element have? '), read(IsotopeCount),
    write('What is the flame test color of the element? '), read(FlameTestColor),
    write('Is the element toxic? (yes/no) '), read(Toxicity),
    write('How resistant is the element to corrosion? (high, medium, low) '), read(CorrosionResistance),
    write('What is the hardness of the element on the Mohs scale? '), read(Hardness),
    % Computation would start here taking the Previous ElementAnswer in consideration

    findall(Element, (has_property(Element, radioactivity, Radioactivity),
    has_property(Element, isotopeCount, IsotopeCount),
    has_property(Element, flameTestColor, FlameTestColor),
    has_property(Element, toxicity,Toxicity),
    has_property(Element, corrosionResistance, CorrosionResistance),
    has_property(Element, hardness, Hardness)), PossElement),
    (PossElement == [] -> !,fail;
                    write('The Element is '), write(PossElement), nl).
