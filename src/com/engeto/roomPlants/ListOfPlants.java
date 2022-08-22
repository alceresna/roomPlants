package com.engeto.roomPlants;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ListOfPlants {

    private List<Plant> listOfPlants = new ArrayList<>();

    private Set<LocalDate> setOfWaterings = new HashSet<>();
    public static ListOfPlants importFromFile(String filename) throws PlantException {

        ListOfPlants list = new ListOfPlants();
        String line;
        Plant plant;
        String[] items;

    try(Scanner sc = new Scanner(new File(filename))){
        while(sc.hasNextLine()){
            line = sc.nextLine();
            items = line.split("\t");
            String name = items[0];
            String notes = items[1];
            int frekvencyOfWatering = Integer.parseInt(items[2]);
            LocalDate watering = LocalDate.parse(items[3]);
            LocalDate planted = LocalDate.parse(items[4]);

            plant = new Plant(name,notes,planted,watering,frekvencyOfWatering);
            list.addPlant(plant);
            list.setOfWaterings.add(watering);
        }
    }
    catch (FileNotFoundException e){
        throw new PlantException("Soubor "+filename+"nebyl nalezen."+e.getLocalizedMessage());
    }
    catch (DateTimeParseException e){
        throw new PlantException("Chybn? zapsan? datum v souboru "+filename+" "+e.getLocalizedMessage());
    }
    catch (NumberFormatException e){
        throw new PlantException("Chybn? zapsan? frekvence zal?v?n? kv?tiny v souboru "+filename+" "+
                e.getLocalizedMessage());
    }
    return list;
    }

    public void exportToFile(String filename) throws IOException {
    try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {

        for (Plant plant:listOfPlants) {
            writer.println(plant.toString());
        }
    }
      catch (IOException e) {
        throw new RuntimeException(e);
    }
    }

    public void addPlant(Plant plant){

        listOfPlants.add(plant);
    }

    public void removePlant(Plant plant){

        listOfPlants.remove(plant);
    }

    public Plant getPlantAtIndex(int index){

        return listOfPlants.get(index);
    }

   public void removePlantByName(String name) {

       for (Plant plant:listOfPlants) {
           if(plant.getName().equals(name)) removePlant(plant);
       }
   }

    public StringBuilder getWateringInfoForAllPlants(){

        StringBuilder info = new StringBuilder();

        for (Plant plant:this.listOfPlants) {
            info.append(plant.getWateringInfo());
        }
        return info;
    }

    public List<Plant> getListOfPlants() {

        return listOfPlants;
    }

    public Set<LocalDate> getSetOfWaterings() {

        return new HashSet<>(setOfWaterings);
    }

}
