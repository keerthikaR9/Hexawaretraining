// entity/Pet.java
package entity;

public class Pet {
    private int petID;
    private String name;
    private int age;
    private String breed;
    private String type;
	private boolean availableForAdoption;

    public Pet(int petID, String name, int age, String breed, String type) {
        this.petID = petID;
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.type = type;
    }

    public String toString1() {
        return "🐾 ID: " + petID + " | Name: " + name + " | Age: " + age + 
               " | Breed: " + breed + " | Type: " + type;
    }



    public Pet(int id, String name, int age, String breed, String type, boolean available) {
        this.petID = id;
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.type = type;
        this.availableForAdoption = available;
    }

  


    
    public Pet() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

   
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    
    @Override
    public String toString() {
        return "Pet{" +
                "Name='" + name + '\'' +
                ", Age=" + age +
                ", Breed='" + breed + '\'' +
                '}';
    }
}
