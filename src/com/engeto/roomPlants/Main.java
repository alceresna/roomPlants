
package com.engeto.roomPlants;

import java.io.IOException;

public class Main {

    public static final String FILENAMEIN = "kvetiny.txt";
    public static final String FILENAMEOUT = "nove kvetiny.txt";
    public static void main(String[] args) {

        ListOfPlants list;

        try {
            list = ListOfPlants.importFromFile(FILENAMEIN);
        } catch (PlantException e) {
            throw new RuntimeException(e);
        }

        System.out.println(list.getWateringInfoForAllPlants());

        try {
            list.exportToFile(FILENAMEOUT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    }
