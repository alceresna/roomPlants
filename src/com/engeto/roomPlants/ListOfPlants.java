package com.engeto.roomPlants;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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

                Plant plant = new Plant(name,planted,watering,frekvencyOfWatering);
                list.addPlant(plant);
            }
            }
    }
    catch (FileNotFoundException e){
        throw new PlantException("Soubor "+filename+"nebyl nalezen."+e.getLocalizedMessage());
    }
    catch (DateTimeParseException e){
        throw new PlantException("Chybnì zapsané datum v souboru "+filename+" "+e.getLocalizedMessage());
    }
    catch (NumberFormatException e){
        throw new PlantException("Chybnì zapsaná frekvence zalévání kvìtiny v souboru "+filename+" "+
                e.getLocalizedMessage());
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

    public StringBuilder getWateringInfoForAllPlants(){

        StringBuilder info = new StringBuilder();

        for (Plant plant:this.listOfPlants) {
            info.append(plant.getWateringInfo());
        }
        return info;
    }
}
