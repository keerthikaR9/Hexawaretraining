package dao;

import entity.Pet;
import java.util.List;

public interface PetDAO {
    List<Pet> getAvailablePets();
}
