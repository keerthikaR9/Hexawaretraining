package entity;

import java.util.ArrayList;
import java.util.List;

import exception.AdoptionException;
import util.LoggerUtil;

public class PetShelter implements IAdoptable {

    private List<Pet> availablePets;

    
    public PetShelter() {
        availablePets = new ArrayList<>();
    }

    
    public void addPet(Pet pet) {
        availablePets.add(pet);
        System.out.println("Pet added successfully: " + pet.getName());
    }

    
    public void removePet(Pet pet) {
        if (availablePets.remove(pet)) {
            System.out.println("Pet removed: " + pet.getName());
        } else {
            System.out.println("Pet not found in shelter.");
        }
    }

    
    public void listAvailablePets() {
        if (availablePets.isEmpty()) {
            System.out.println("No pets available for adoption right now.");
        } else {
            System.out.println("Available Pets for Adoption:");
            for (Pet pet : availablePets) {
                try {
                    System.out.println(pet.toString());
                } catch (NullPointerException e) {
                    System.out.println("Pet info missing!");
                }
            }
        }
    }

    public List<Pet> getAvailablePets() {
        return availablePets;
    }
   

    @Override
    public void adopt() {
        try {
            if (availablePets.isEmpty()) {
                throw new AdoptionException("❌ No pets available to adopt at the moment.");
            }

            Pet adoptedPet = availablePets.remove(0);

            
            if (adoptedPet.getName() == null || adoptedPet.getName().isEmpty() ||
                adoptedPet.getBreed() == null || adoptedPet.getBreed().isEmpty()) {
                throw new AdoptionException(" Pet has incomplete information. Cannot proceed with adoption.");
            }

            System.out.println("✅ " + adoptedPet.getName() + " has been adopted!");

            
            LoggerUtil.logAdoption(
                adoptedPet.getName(),
                adoptedPet.getBreed(),
                java.time.LocalDate.now().toString()
            );

        } catch (AdoptionException e) {
            System.out.println(e.getMessage());
        }
    }


    
}
