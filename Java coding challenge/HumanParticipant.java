package entity;

public class HumanParticipant implements IAdoptable {
    private String name;
    private String type; // e.g., Visitor, Volunteer

    public HumanParticipant(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() { return name; }
    public String getType() { return type; }

    @Override
    public void adopt() {
        System.out.println(name + " (" + type + ") is participating in the adoption event.");
    }
}
