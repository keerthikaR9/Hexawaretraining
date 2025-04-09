package entity;

public class ItemDonation extends Donation {
    private String itemType;

    
    public ItemDonation(String donorName, double amount, String itemType) {
        super(donorName, amount); 
        this.itemType = itemType;
    }

    
    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    // Implementation of abstract method
    @Override
    public void recordDonation() {
        System.out.println("Item Donation Recorded:");
        System.out.println("Donor Name: " + donorName);
        System.out.println("Amount (estimated value): $" + amount);
        System.out.println("Item Type: " + itemType);
    }

   
    @Override
    public String toString() {
        return "ItemDonation [Donor: " + donorName + ", Amount: $" + amount + ", Item: " + itemType + "]";
    }
}
