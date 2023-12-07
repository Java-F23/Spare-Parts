package CarSparePartsSystem;

public class SparePart {
    private String id;
    private String name;
    private String type;
    private String category;
    private String specifications;
    private int stockLevel;
    private double price;
    private String warrantyInfo;
    private String maintenanceHistory;
    

    public SparePart(String id, String name, String type, String category, String specifications, int stockLevel, double price, String warrantyInfo, String maintenanceHistory) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.category = category; // Initialize category
        this.specifications = specifications;
        this.stockLevel = stockLevel;
        this.price = price;
        this.warrantyInfo = warrantyInfo;
        this.maintenanceHistory = maintenanceHistory;
    }
    
    public String toString() {
        return "SparePart{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", type='" + type + '\'' +
               ", category='" + category + '\'' +
               ", specifications='" + specifications + '\'' +
               ", stockLevel=" + stockLevel +
               ", price=" + price +
               ", warrantyInfo='" + warrantyInfo + '\'' +
               ", maintenanceHistory='" + maintenanceHistory + '\'' +
               '}';
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getCategory() { return category; }
    public String getSpecifications() { return specifications; }
    public int getStockLevel() { return stockLevel; }
    public double getPrice() { return price; }
    public String getWarrantyInfo() { return warrantyInfo; }
    public String getMaintenanceHistory() { return maintenanceHistory; }
    public String getDetailedInformation() {
        return "Name: " + name + "\nType: " + type + "\nSpecifications: " + specifications;
    }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setCategory(String category) { this.category = category; }
    public void setSpecifications(String specifications) { this.specifications = specifications; }
    public void setStockLevel(int stockLevel) { this.stockLevel = stockLevel; }
    public void setPrice(double price) { this.price = price; }
    public void setWarrantyInfo(String warrantyInfo) { this.warrantyInfo = warrantyInfo; }
    public void setMaintenanceHistory(String maintenanceHistory) { this.maintenanceHistory = maintenanceHistory; }
    public void decreaseStockLevel(int quantity) {
        this.stockLevel -= quantity;
        if (this.stockLevel < 0) {
            this.stockLevel = 0; // Prevent stock level from going negative
        }
    }
    public String toFileString() {
        return id + "," + name + "," + type + "," + category + "," + specifications + "," +
               stockLevel + "," + price + "," + warrantyInfo + "," + maintenanceHistory;
    }

    // Create a SparePart object from a string (line from file)
    public static SparePart fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        if (parts.length != 9) {
            return null; // Or handle error
        }
        return new SparePart(parts[0], parts[1], parts[2], parts[3], parts[4],
                             Integer.parseInt(parts[5]), Double.parseDouble(parts[6]),
                             parts[7], parts[8]);
    }
    // Additional methods for business logic can be added here
}

