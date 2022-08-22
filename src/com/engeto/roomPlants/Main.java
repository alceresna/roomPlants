
package com.engeto.roomPlants;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static final String FILENAMEIN = "kvetiny.txt";
    public static final String FILENAMEOUT = "nove kvetiny.txt";

    public static void main(String[] args) throws PlantException {

        ListOfPlants list;

        try {
            list = ListOfPlants.importFromFile(FILENAMEIN);
        } catch (PlantException e) {
            throw new PlantException(e.getLocalizedMessage());
        }

        System.out.println(list.getWateringInfoForAllPlants());

        try {
            Plant plant1 = new Plant("Bazalka v kuchyni", "", LocalDate.of(2021,9,4),
                    LocalDate.of(2021,9,4), 3);
            list.addPlant(plant1);
        } catch (PlantException e) {
            throw new PlantException(e.getLocalizedMessage());
        }

        list.removePlantByName("Sukulent v koupelne");

        try {
            list.exportToFile(FILENAMEOUT);
        } catch (IOException e) {
            throw new PlantException(e.getLocalizedMessage());
        }

        System.out.println("seznam rostlin:\n"+list.getListOfPlants());

        Collections.sort(list.getListOfPlants());

        System.out.println("\nseznam rostlin se?azený podle jména:\n"+list.getListOfPlants());

        Collections.sort(list.getListOfPlants(),new PlantWateringComparator());

        System.out.println("\nseznam rostlin se?azený podle data zálivky:\n"+list.getListOfPlants());

        System.out.println("\ndny,kdy probíhala poslední zálivka:\n"+list.getSetOfWaterings());

    }
    }
