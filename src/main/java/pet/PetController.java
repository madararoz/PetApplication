package pet;

import meal.MealDBService;
import meal.PetMeal;
import owner.Owner;
import toys.PetToy;
import toys.ToyDBService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PetController {

    PetDBService petDBService = new PetDBService();
    MealDBService mealDBService = new MealDBService();
    ToyDBService toyDBService = new ToyDBService();

    private String petListTitle = " id \t name \t age \t type \t owner";
    private String petTypeListTitle = " id \t name";


    public void viewPet() {
        String petId = JOptionPane.showInputDialog(null, "Enter pet Id");
        Pet pet = null;
        ArrayList<PetMeal> petMeals = new ArrayList<PetMeal>();
        ArrayList<PetToy> petToys = new ArrayList<PetToy>();
        try {
            pet = petDBService.getPet(Integer.parseInt(petId));
            petToys = toyDBService.findToysByPetType(pet.getType());
            petMeals = mealDBService.findMealsByPetType(pet.getType());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String message = createPetProfileUI(pet, petMeals, petToys);
        JOptionPane.showMessageDialog(null, message);
    }

    private String createPetProfileUI(Pet pet, ArrayList<PetMeal> petMeals, ArrayList<PetToy> petToys) {

        String petInfo = String.format("\n%s 's Profile"
                        + "\nSpecial Id: %o"
                        + "\nAge: %o"
                        + "\nType: %s",
                pet.getName(), pet.getId(), pet.getAge(), pet.getType().getValue());

        String ownerInfo = "\n\n Owner Information" + "\nname:" + pet.getOwner().getName();

        StringBuilder foodInfo = new StringBuilder(("\n\nFood Information\n"));
        for (PetMeal meal : petMeals) {
            foodInfo.append(meal.getId()).append(" \t ")
                    .append(meal.getName()).append(" \t ")
                    .append(meal.getWeight()).append("\n");
            //         foodInfo += meal.getId() + " \t " + meal.getName() + " \t " + meal.getWeight() + "\n";
        }

        StringBuilder toyInfo = new StringBuilder(("\n\nToy Information\n"));
        for (PetToy toy : petToys) {

            toyInfo.append(toy.getId() + " \t ")
                    .append(toy.getMaterial()).append(" \t ")
                    .append(toy.getPrice()).append("\n");
        }

        return petInfo + ownerInfo + foodInfo + toyInfo;
    }


    public void showAllPets() {
        ArrayList<Pet> pets = new ArrayList<>();
        try {
            pets = petDBService.getAllPets();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String message = petListTitle +
                pets.stream()
                        .map(Pet::toString)
                        .collect(Collectors.joining("\n"));
        JOptionPane.showMessageDialog(null, message);
    }

    public void removePetFromOwner() {
        try {
            int petId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter pet Id"));
            petDBService.removeOwnerFromPet(petId);
            JOptionPane.showMessageDialog(null, "Sad to see you go :(");
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error occured while removing pet");
        }
    }

    public void assignPetToOwner() {
        try {
            int petId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter pet Id"));
            int ownerId = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Owner Id"));
            petDBService.addOwnerToPet(ownerId, petId);
            JOptionPane.showMessageDialog(null, "Pet adoption complete :)");
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Error occured while removing pet");
        }

    }

    public void addPet() {
        String name = JOptionPane.showInputDialog(null, "Enter Pet name");
        int age = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter pet age"));
        String type = JOptionPane.showInputDialog(null, "Enter pet type id");
        Pet pet = new Pet(age, type, name);
        try {
            petDBService.addPetToDB(name, age, type);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error. New pet was not added");
        }

    }

 //   private Pet collectPetInfo() {
//        String name = JOptionPane.showInputDialog(null, "Enter Pet name");
//        int age = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter pet age"));
//        String type = JOptionPane.showInputDialog(null, "Enter pet type id");
//
//        return new Pet(age, type, name);
 //   }

    public void addNewPetType() {
        String petType= JOptionPane.showInputDialog(null, "Enter new pet type");
        try {
            petDBService.addNewPetType(petType);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removePetType() {
        try {
            int petTypeId = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter pet type id"));
            petDBService.delete(petTypeId);
            JOptionPane.showMessageDialog(null, " Pet type deleted successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting pet type.");
        }
    }

    public void viewAllPetTypes() {
        ArrayList<PetType> petTypes = new ArrayList<>();
        try {
            petTypes = petDBService.getAllPetTypes();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        String message = petTypeListTitle +
                petTypes.stream()
                        .map(PetType::toString)
                        .collect(Collectors.joining("\n"));
        JOptionPane.showMessageDialog(null, message);
    }
}


