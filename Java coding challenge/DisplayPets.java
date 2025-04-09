package test;

import dao.PetDAO;
import dao.impl.PetDAOImpl;
import entity.Pet;

import java.util.List;

public class DisplayPets {
    public static void main(String[] args) {
        PetDAO petDAO = new PetDAOImpl();
        List<Pet> pets = petDAO.getAvailablePets();

        if (pets.isEmpty()) {
            System.out.println("🚫 No pets available for adoption at the moment.");
        } else {
            System.out.println("🐾 Available Pets for Adoption:");
            for (Pet pet : pets) {
                System.out.println(pet);
            }
        }
    }
}

