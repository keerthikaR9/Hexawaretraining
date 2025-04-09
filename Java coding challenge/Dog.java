package entity;

public class Dog extends Pet {
    private String dogBreed;

    
    public Dog(int petID, String name, int age, String breed, String dogBreed) {
        super(petID, name, age, breed, "Dog"); 
        this.dogBreed = dogBreed;
    }


   
    public String getDogBreed() {
        return dogBreed;
    }

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }

    
    @Override
    public String toString() {
        return super.toString() + ", DogBreed='" + dogBreed + '\'' + '}';
    }
}
