
package com.engeto.roomPlants;

public class Main {

    public static final String FILENAME = "kvetiny.txt";
    public static void main(String[] args) {

        ListOfPlants list;

        try {
            list = ListOfPlants.importFromFile(FILENAME);
        } catch (PlantException e) {
            throw new RuntimeException(e);
        }

        System.out.println(list.getWateringInfoForAllPlants());

        }
    }
