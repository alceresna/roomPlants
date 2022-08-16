
package com.engeto.roomPlants;

public class Main {
    public static void main(String[] args) {

        ListOfPlants list = new ListOfPlants();

        try {
            list = ListOfPlants.importFromFile("kvetiny.txt");
        } catch (PlantException e) {
            throw new RuntimeException(e);
        }

        System.out.println(list.getWateringInfoForAllPlants());

        }
    }
