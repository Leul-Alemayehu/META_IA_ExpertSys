% Now, the color and oxidation states can be determined
% without listing all possible oxidation or color elements,
% as the tester may not find all values during testing. Therefore, inputting one value can suffice for the process.

%%%%%%%%%% KNOWLEDGE BASE FROM test_knowledge_base2.pl GOES HERE %%%%%%%%%%%%


% Properties for: Manganese
has_property(manganese, atomicNumber, 25).
has_property(manganese, atomicMass, 54.94).
has_property(manganese, color, [silvery_gray]).
has_property(manganese, oxidationStates, [+7, +4, +3, +2]).
has_property(manganese, symbol, mn).
has_property(manganese, stateOfMatter, solid).
has_property(manganese, density, 7.3).
has_property(manganese, meltingPoint, 1246).
has_property(manganese, boilingPoint, 2061).
has_property(manganese, thermalConductivity, high).
has_property(manganese, electricalConductivity, high).
has_property(manganese, crystalStructure, bcc).
has_property(manganese, toxicity, no).
has_property(manganese, reactivity, highly_reactive).
has_property(manganese, oxideAcidityBasicity, basic).
has_property(manganese, reactionWithOxygen, forms_oxides).
has_property(manganese, reactionWithWater, mild).
has_property(manganese, reactionWithAcids, produces_hydrogen).
has_property(manganese, magnetism, paramagnetic).
has_property(manganese, flameTestColor, yellow_green).
has_property(manganese, isotopeCount, 28).
has_property(manganese, allotropeCount, 4).
has_property(manganese, corrosionResistance, protective_oxide_layer).
has_property(manganese, refractiveIndex, n/a).
has_property(manganese, hardness, 6).
has_property(manganese, radioactivity, no).
has_property(manganese, group, 7).
has_property(manganese, period, 4).
has_property(manganese, ionizationEnergy, 7.434).
has_property(manganese, electronegativity, 1.75).
has_property(manganese, electronConfiguration, '1s22s22p63s23p64s23d5').


%%%%%%%%%%%%%%%%% RULE DEFINITION AND SUCH %%%%%%%%%%%%%%%%%%%%%%%
%%%%%%
the_query_begins:-
    ask_meta_properties(MetaElements),
    ask_chemical_properties(ChemicalElements),
    ask_physical_properties(PhysicalElements),
    ask_special_properties(SpecialElements),
    intersection(MetaElements, ChemicalElements, MetaChemicalElements),
    intersection(PhysicalElements, SpecialElements, PhySpecialElements),
    intersection(MetaChemicalElements, PhySpecialElements, FinalAnswer),
    write("All Intersection: "), write(FinalAnswer).

%%%%%%

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

check_all_membership([], _):-!.
check_all_membership([H|T], ListOfElements):-
    member(H, ListOfElements),
    check_all_membership(T, ListOfElements).

intersection(NonEmpty,[],NonEmpty ):-NonEmpty \=[], !.
intersection([], NonEmpty,NonEmpty ):-NonEmpty \=[], !.
intersection([], [], BothEmpty):- BothEmpty = [].
intersection([H|T], L2, R):-
    member(H, L2), !,
    intersection(T, L2, R).
intersection([_|T], L2, R):-
    intersection(T, L2, R).


proceed(no, _, ElementAnswer):-
    ElementAnswer = [].

proceed(yes, ask_general_questions, PossElement):-
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

proceed(yes, ask_physical_properties, PossElement):-

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
                          check_all_membership(Colors, ElementColors),
                          has_property(Element, density, Density),
                          has_property(Element, meltingPoint, MeltingPoint),
                          has_property(Element, boilingPoint, BoilingPoint),
                          has_property(Element, thermalConductivity, ThermalConductivity),
                          has_property(Element, electricalConductivity, ElectricalConductivity),
                          has_property(Element, crystalStructure,CrystalStructure)), PossElement),
    (PossElement == [] -> !,fail;
                write('The Element is '), write(PossElement), nl).



proceed(yes, ask_chemical_properties, PossElement) :-
    write('What is the reactivity of the element? (highly_reactive, moderately_reactive, inert, not_applicable) '), read(Reactivity),
    write('Is the oxide of the element acidic, basic, amphoteric, or not applicable? '), read(OxideAcidityBasicity),
    write('How does the element react with water? (vigorous, mild, no_reaction, not_applicable) '), read(ReactionWithWater),
    write('How does the element react with acids? (produces_hydrogen, no_reaction, not_applicable) '), read(ReactionWithAcids),
    write('How does the element react with oxygen? (forms_oxides, does_not_react, not_applicable) '), read(ReactionWithOxygen),
    write('What are the oxidation states of the element? (e.g., [+1,-1], [+2] etc) '), read(OxidationStates),
    write('What is the electronegativity of the element? '), read(Electronegativity),
    write('What is the electron configuration of the element? (e.g. \'1s22s22p6\' under single quote) '), read(ElectronConfiguration),
    write('What is the ionization energy of the element? '), read(IonizationEnergy),
    write('How many allotropes does the element have? '), read(AllotropeCount),
    % Computation would start here taking the Previous ElementAnswer in consideration
    findall(Element, (has_property(Element,reactivity,Reactivity ),
    has_property(Element, oxideAcidityBasicity ,OxideAcidityBasicity),
    has_property(Element, reactionWithWater,ReactionWithWater),
    has_property(Element, reactionWithAcids,ReactionWithAcids),
    has_property(Element, reactionWithOxygen,ReactionWithOxygen),
    has_property(Element, oxidationStates,AllOxidationStates),
    check_all_membership(OxidationStates, AllOxidationStates),
    has_property(Element, electronegativity ,Electronegativity),
    has_property(Element, ionizationEnergy,IonizationEnergy),
    has_property(Element,allotropeCount ,AllotropeCount)), PossElement),
    (PossElement == [] -> !,fail;
                write('The Element is '), write(PossElement), nl).

proceed(yes, ask_special_properties, PossElement) :-
    write('Is the element radioactive? (yes/no) '), read(Radioactivity),
    write('How many stable isotopes does the element have? '), read(IsotopeCount),
    write('What is the flame test color of the element? '), read(FlameTestColor),
    write('Is the element toxic? (yes/no) '), read(Toxicity),
    write('How resistant is the element to corrosion? (low_resistance, low_general_activity, moderately_resistance, protective_oxide_layer, n\a) '), read(CorrosionResistance),
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
