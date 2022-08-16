package com.engeto.roomPlants;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListOfPlants {

    public static ListOfPlants importFromFile(String filename) throws PlantException {

        ListOfPlants list = new ListOfPlants();
        String line;

    try(Scanner sc = new Scanner(new File(filename))){
        while(sc.hasNextLine()){
            line = sc.nextLine();
            String[] string = line.split("\t");
            String name = string[0];
            if(string.length == 5){
                String notes = string[1];
                int frekvencyOfWatering = Integer.parseInt(string[2]);
                LocalDate watering = LocalDate.parse(string[3]);
                LocalDate planted = LocalDate.parse(string[4]);

                Plant plant = new Plant(name,notes,planted,watering,frekvencyOfWatering);
                list.addPlant(plant);
        }
            else if(string.length == 4) {
                int frekvencyOfWatering = Integer.parseInt(string[1]);
                LocalDate watering = LocalDate.parse(string[2]);
                LocalDate planted = LocalDate.parse(string[3]);

                Plant plant = new Plant(name,planted,frekvencyOfWatering);
                plant.setWatering(watering);
                list.addPlant(plant);
            }
            }
    }
    catch (FileNotFoundException e){
        throw new PlantException("Soubor "+filename+"nebyl nalezen."+e.getLocalizedMessage());
    }
    return list;
    }

    private ArrayList<Plant> listOfPlants = new ArrayList<>();

    public void addPlant(Plant plant){

        listOfPlants.add(plant);
    }

    public void removePlant(Plant plant){

        listOfPlants.remove(plant);
    }

    public Plant getPlantAtIndex(int index){

        return listOfPlants.get(index);
    }

    public void printWateringInfoForAllPlants(){

        for (Plant plant:this.listOfPlants) {
            System.out.println(plant.getWateringInfo());
        }
    }
}
