package entity;

public class Cat extends Pet {
    private String catColor;

    
    public Cat(int petID, String name, int age, String breed, String catColor) {
        super(petID, name, age, breed, "Cat"); // "Cat" as the type
        this.catColor = catColor;
    }

    
    public String getCatColor() {
        return catColor;
    }

    
    public void setCatColor(String catColor) {
        this.catColor = catColor;
    }

    
    @Override
    public String toString() {
        return super.toString() + ", CatColor='" + catColor + '\'' + '}';
    }
}
